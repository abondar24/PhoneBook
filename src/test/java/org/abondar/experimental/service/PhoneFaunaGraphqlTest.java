package org.abondar.experimental.service;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.service.impl.PhoneFaunaGraphqlServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

@MicronautTest
@Property(name = "fauna.graphql.enabled", value = "true")
public class PhoneFaunaGraphqlTest {

    @Inject
    private PhoneFaunaGraphqlServiceImpl service;

    @Test
    public void saveTest(){
        var res = service.save(new PhoneCreateRequest("res","1111"));
        assertNull(res);
    }
}
