package org.abondar.experimental.service;

import org.abondar.experimental.model.db.PhoneRecord;

import java.util.Optional;

public interface PhoneCacheService {


    void save(PhoneRecord record);

    Optional<PhoneRecord> find(long id);

    void remove(long id);

    void clearCache();
}
