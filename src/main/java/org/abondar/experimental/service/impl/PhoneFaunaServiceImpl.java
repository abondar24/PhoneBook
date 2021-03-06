package org.abondar.experimental.service.impl;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.dao.PhoneFaunaRepository;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.service.PhoneFaunaService;

import java.util.Optional;

@Singleton
@Requires(property = "fauna.graphql.enabled" , value = "false")
public class PhoneFaunaServiceImpl implements PhoneFaunaService {

  @Inject private PhoneFaunaRepository repository;

  @Override
  public PhoneRecord save(PhoneCreateRequest request) {
    var id = repository.nextId().join();
    var rec = new PhoneRecord(Long.parseLong(id), request.name(), request.phoneNumber());
    repository.saveOrUpdateRecord(rec);
    return rec;
  }

  @Override
  public PhoneRecord update(PhoneUpdateRequest request) {
    var rec = new PhoneRecord(request.id(), request.name(), request.phoneNumber());
    repository.saveOrUpdateRecord(rec);
    return rec;
  }

  @Override
  public Optional<PhoneRecord> find(long id) {
    return Optional.ofNullable(repository.find(id).join());
  }

  @Override
  public void remove(long id) {
    repository.remove(id);
  }
}
