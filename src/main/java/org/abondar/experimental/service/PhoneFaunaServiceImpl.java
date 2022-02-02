package org.abondar.experimental.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.dao.PhoneFaunaRepository;
import org.abondar.experimental.exception.PhoneFaunaException;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.PhoneRequest;

import java.util.concurrent.ExecutionException;

@Singleton
public class PhoneFaunaServiceImpl implements PhoneFaunaService{

    @Inject
    private PhoneFaunaRepository repository;


    @Override
    public PhoneRecord saveOrUpdateRecord(PhoneRequest request) {
            var id = repository.nextId().join();
            var rec = new PhoneRecord(id,request.name(),request.phoneNumber());
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
