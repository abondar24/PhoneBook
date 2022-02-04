package org.abondar.experimental.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.model.db.PhoneRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@MicronautTest
public class PhoneCacheServiceTest {

    @Inject
    private PhoneCacheService cacheService;

    @Test
    @Timeout(2)
    public void saveAndFindTest(){
        var rec = new PhoneRecord(1L,"test","test");

        cacheService.save(rec);

        var res = cacheService.find(rec.id());
        assertEquals(rec.id(),res.get().id());
    }


    @Test
    @Timeout(3)
    public void removeTest(){
        var rec = new PhoneRecord(1L,"test","test");

        cacheService.save(rec);

        cacheService.remove(rec.id());

        var res = cacheService.find(rec.id());
        assertNull(res);
    }
}
