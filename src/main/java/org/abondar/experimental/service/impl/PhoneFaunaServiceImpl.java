package org.abondar.experimental.service.impl;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.dao.PhoneFaunaRepository;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.PhoneCreateRequest;
import org.abondar.experimental.model.web.PhoneUpdateRequest;
import org.abondar.experimental.service.PhoneFaunaService;

@Singleton
public class PhoneFaunaServiceImpl implements PhoneFaunaService {

    @Inject
    private PhoneFaunaRepository repository;


    @Override
    public PhoneRecord save(PhoneCreateRequest request) {
        var id = repository.nextId().join();
        var rec = new PhoneRecord(id,request.name(),request.phoneNumber());
        repository.saveOrUpdateRecord(rec);
        return rec;
    }

    @Override
    public PhoneRecord update(PhoneUpdateRequest request) {
        var rec = new PhoneRecord(request.id(),request.name(),request.phoneNumber());
        repository.saveOrUpdateRecord(rec);
        return rec;
    }

    @Override
    public PhoneRecord find(long id) {
           return repository.find(id).join();

    }

    @Override
    public void remove(long id) {
        repository.remove(id);

    }
}
