package org.abondar.experimental.service;

import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;

import java.util.Optional;

public interface PhoneFaunaService {

    PhoneRecord save(PhoneCreateRequest request);

    PhoneRecord update(PhoneUpdateRequest request);

    Optional<PhoneRecord> find(long id);

    void  remove(long id);


}
