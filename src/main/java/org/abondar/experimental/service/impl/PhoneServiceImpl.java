package org.abondar.experimental.service.impl;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.exception.PhoneBookException;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.model.web.response.PhoneCreateResponse;
import org.abondar.experimental.model.web.response.PhoneResponse;
import org.abondar.experimental.model.web.response.PhoneUpdateResponse;
import org.abondar.experimental.service.PhoneCacheService;
import org.abondar.experimental.service.PhoneFaunaService;
import org.abondar.experimental.service.PhoneService;

import java.time.Instant;

@Singleton
public class PhoneServiceImpl implements PhoneService {

  @Inject private PhoneCacheService cacheService;

  @Inject private PhoneFaunaService faunaService;

  @Override
  public PhoneCreateResponse save(PhoneCreateRequest request) {
    var rec = faunaService.save(request);
    cacheService.save(rec);

    return new PhoneCreateResponse(rec.id(), Instant.now());
  }

  @Override
  public PhoneUpdateResponse update(PhoneUpdateRequest request) {
    var rec = faunaService.update(request);
    cacheService.save(rec);

    return new PhoneUpdateResponse(Instant.now());
  }

  @Override
  public PhoneResponse find(long id) {
    var phoneRecord = cacheService.find(id);

    var res =
        phoneRecord.orElseGet(
            () ->
                faunaService
                    .find(id)
                    .orElseThrow(
                        () -> {
                          throw new PhoneBookException("Phone Record not found by provided id");
                        }));

    return new PhoneResponse(res.name(), res.phoneNumber());
  }

  @Override
  public void remove(long id) {
    faunaService.remove(id);
    cacheService.remove(id);
  }
}
