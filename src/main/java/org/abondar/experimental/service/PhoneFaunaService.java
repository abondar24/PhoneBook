package org.abondar.experimental.service;

import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.PhoneRequest;

public interface PhoneFaunaService {

    PhoneRecord saveOrUpdateRecord(PhoneRequest request);

    PhoneRecord find(long id);

    void  remove(long id);


}
