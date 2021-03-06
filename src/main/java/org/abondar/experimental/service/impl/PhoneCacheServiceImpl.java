package org.abondar.experimental.service.impl;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CacheInvalidate;
import io.micronaut.cache.annotation.CachePut;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.service.PhoneCacheService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
@CacheConfig("phonebook")
public class PhoneCacheServiceImpl implements PhoneCacheService {

  Map<Long, PhoneRecord> phonebook = new HashMap<>();

  @Override
  @CachePut
  public void save(PhoneRecord record) {
    phonebook.put(record.id(), record);
  }

  @Override
  @Cacheable(parameters = {"id"})
  public Optional<PhoneRecord> find(long id) {
    return Optional.ofNullable(phonebook.get(id));
  }

  @Override
  @Cacheable(parameters = {"id"})
  public void remove(long id) {
    phonebook.remove(id);
  }

  @Override
  @CacheInvalidate(all = true)
  public void clearCache() {
    phonebook.clear();
  }
}
