package org.abondar.experimental.service.impl;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.service.PhoneFaunaService;

import java.util.Optional;

@Singleton
@Requires(property ="fauna.graphql.enabled", value = "true")
public class PhoneFaunaGraphqlServiceImpl  implements PhoneFaunaService {
    @Override
    public PhoneRecord save(PhoneCreateRequest request) {
        return null;
    }

    @Override
    public PhoneRecord update(PhoneUpdateRequest request) {
        return null;
    }

    @Override
    public Optional<PhoneRecord> find(long id) {
        return Optional.empty();
    }

    @Override
    public void remove(long id) {

    }
}
