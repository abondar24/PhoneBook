package org.abondar.experimental.service.impl;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.client.FaunaGraphqlClient;
import org.abondar.experimental.exception.FaunaGraphqlException;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneGraphqlRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.model.web.response.PhoneGraphqlResponse;
import org.abondar.experimental.model.web.response.graphql.ContactData;
import org.abondar.experimental.model.web.response.graphql.IdData;
import org.abondar.experimental.service.PhoneFaunaService;

import java.util.Optional;

@Singleton
@Requires(property ="fauna.graphql.enabled", value = "true")
public class PhoneFaunaGraphqlServiceImpl  implements PhoneFaunaService {

  @Inject private FaunaGraphqlClient client;

    @Override
    public PhoneRecord save(PhoneCreateRequest request) {
    var req = String.format(PhoneGraphqlRequest.saveRequest, request.name(), request.phoneNumber());

    HttpResponse<PhoneGraphqlResponse> resp = client.performRequest(req);
    if (resp.code() != 200) {
      throw new FaunaGraphqlException(resp.reason());
    }

    PhoneGraphqlResponse<IdData> respBody = resp.body();
    var id = respBody.data().data().id();

    return new PhoneRecord(Long.parseLong(id), request.name(), request.phoneNumber());
    }

    @Override
    public PhoneRecord update(PhoneUpdateRequest request) {
    var req =
        String.format(
            PhoneGraphqlRequest.updateRequest, request.id(), request.name(), request.phoneNumber());

    HttpResponse<PhoneGraphqlResponse> resp = client.performRequest(req);
    if (resp.code() != 200) {
      throw new FaunaGraphqlException(resp.reason());
    }

    PhoneGraphqlResponse<ContactData> respBody = resp.body();
    var name = respBody.data().data().name();
    var phoneNumber = respBody.data().data().phoneNumber();

    return new PhoneRecord(request.id(), name, phoneNumber);
    }

    @Override
    public Optional<PhoneRecord> find(long id) {

    var req = String.format(PhoneGraphqlRequest.findRequest, id);

    HttpResponse<PhoneGraphqlResponse> resp = client.performRequest(req);
    if (resp.code() != 200) {
      throw new FaunaGraphqlException(resp.reason());
    }

    PhoneGraphqlResponse<ContactData> respBody = resp.body();
    if (respBody.data().data() == null) {
      return Optional.empty();
    }

    var name = respBody.data().data().name();
    var phoneNumber = respBody.data().data().phoneNumber();

    var rec = new PhoneRecord(id, name, phoneNumber);
    return Optional.of(rec);
  }

    @Override
    public void remove(long id) {
    var req = String.format(PhoneGraphqlRequest.deleteRequest, id);

    HttpResponse<PhoneGraphqlResponse> resp = client.performRequest(req);
    if (resp.code() != 200) {
      throw new FaunaGraphqlException(resp.reason());
    }
    }
}
