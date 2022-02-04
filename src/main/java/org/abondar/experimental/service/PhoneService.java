package org.abondar.experimental.service;

import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.model.web.response.PhoneCreateResponse;
import org.abondar.experimental.model.web.response.PhoneResponse;
import org.abondar.experimental.model.web.response.PhoneUpdateResponse;

public interface PhoneService {

  PhoneCreateResponse save(PhoneCreateRequest request);

  PhoneUpdateResponse update(PhoneUpdateRequest request);

  PhoneResponse find(long id);

  void remove(long id);
}
