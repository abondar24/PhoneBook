package org.abondar.experimental.service;

import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.PhoneCreateRequest;
import org.abondar.experimental.model.web.PhoneUpdateRequest;

public interface PhoneFaunaService {

    PhoneRecord save(PhoneCreateRequest request);

    PhoneRecord update(PhoneUpdateRequest request);

    PhoneRecord find(long id);

    void  remove(long id);


}
