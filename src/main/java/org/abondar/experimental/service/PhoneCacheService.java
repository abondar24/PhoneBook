package org.abondar.experimental.service;

import org.abondar.experimental.model.db.PhoneRecord;

public interface PhoneCacheService {


    void save(PhoneRecord record);

    PhoneRecord find(long id);

    void remove(long id);
}
