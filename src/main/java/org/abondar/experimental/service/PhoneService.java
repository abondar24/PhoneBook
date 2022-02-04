package org.abondar.experimental.service;

import org.abondar.experimental.model.web.PhoneCreateRequest;
import org.abondar.experimental.model.web.PhoneCreateResponse;
import org.abondar.experimental.model.web.PhoneUpdateRequest;
import org.abondar.experimental.model.web.PhoneUpdateResponse;

public interface PhoneService {

    PhoneCreateResponse save(PhoneCreateRequest request);

    PhoneUpdateResponse update(PhoneUpdateRequest response);

    PhoneCreateResponse find(long id);

    void remove(long id);
}
