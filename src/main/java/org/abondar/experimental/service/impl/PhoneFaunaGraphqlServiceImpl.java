package org.abondar.experimental.service.impl;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.client.FaunaGraphqlClient;
import org.abondar.experimental.exception.FaunaGraphqlException;
import org.abondar.experimental.exception.Messages;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneGraphqlRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.model.web.response.PhoneGraphqlResponse;
import org.abondar.experimental.service.PhoneFaunaService;

import java.util.Optional;

@Singleton
@Requires(property = "fauna.graphql.enabled", value = "true")
public class PhoneFaunaGraphqlServiceImpl implements PhoneFaunaService {

  @Inject private FaunaGraphqlClient client;

  @Override
  public PhoneRecord save(PhoneCreateRequest request) {
    var req = String.format(PhoneGraphqlRequest.saveRequest, request.name(), request.phoneNumber());
    var respBody = performRequest(req);
    var id = respBody.data().idData().id();

    return new PhoneRecord(Long.parseLong(id), request.name(), request.phoneNumber());
  }

  @Override
  public PhoneRecord update(PhoneUpdateRequest request) {
    var req =
        String.format(
            PhoneGraphqlRequest.updateRequest, request.id(), request.name(), request.phoneNumber());

    var respBody = performRequest(req);
    var respData = respBody.data();
    var name = respData.contactData().name();
    var phoneNumber = respData.contactData().phoneNumber();

    return new PhoneRecord(request.id(), name, phoneNumber);
  }

  @Override
  public Optional<PhoneRecord> find(long id) {

    var req = String.format(PhoneGraphqlRequest.findRequest, id);

    var respBody = performRequest(req);
    var respData = respBody.data();
    if (respData.contactData() == null) {
      return Optional.empty();
    }

    var name = respData.contactData().name();
    var phoneNumber = respData.contactData().phoneNumber();

    var rec = new PhoneRecord(id, name, phoneNumber);
    return Optional.of(rec);
  }

  @Override
  public void remove(long id) {
    var req = String.format(PhoneGraphqlRequest.deleteRequest, id);
    performRequest(req);
  }

  private PhoneGraphqlResponse performRequest(String req) {
    HttpResponse<PhoneGraphqlResponse> resp = client.performRequest(req);

    var respBody = resp.getBody();
    if (resp.code() != 200 || respBody.isEmpty()) {
      var err = resp.getBody(String.class);
      throw new FaunaGraphqlException(err.orElse(Messages.UNKNOWN_ERROR));
    }

    return respBody.get();
  }
}
