package org.abondar.experimental.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.exception.PhoneBookException;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.service.impl.PhoneFaunaServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
public class PhoneServiceTest {

  @Inject private PhoneCacheService cacheService;

  @Inject private PhoneFaunaService phoneFaunaService;

  @Inject private PhoneService phoneService;

  @MockBean(PhoneFaunaServiceImpl.class)
  PhoneFaunaService phoneFaunaService() {
    return mock(PhoneFaunaService.class);
  }

  @BeforeEach
  public void clearCache(){
    cacheService.clearCache();
  }

  @Test
  public void saveTest() {
    var req = new PhoneCreateRequest("test", "4444");
    var rec = new PhoneRecord(1L, req.name(), req.phoneNumber());

    when(phoneFaunaService.save(req)).thenReturn(rec);

    var resp = phoneService.save(req);
    assertEquals(rec.id(), resp.id());
    assertNotNull(resp.createdAt());

    var cached = cacheService.find(rec.id());
    assertTrue(cached.isPresent());
    assertEquals(req.name(), cached.get().name());
  }

  @Test
  public void updateTest() {
    var req = new PhoneUpdateRequest(1L, "test", "");
    var rec = new PhoneRecord(req.id(), req.name(), "111");

    when(phoneFaunaService.update(req)).thenReturn(rec);

    var resp = phoneService.update(req);
    assertNotNull(resp.updatedAt());

    var cached = cacheService.find(rec.id());
    assertTrue(cached.isPresent());
    assertEquals(req.name(), cached.get().name());
  }

  @Test
  public void findTest() {
    var rec = new PhoneRecord(1L, "test", "111");
    cacheService.save(rec);

    var resp = phoneService.find(rec.id());
    assertEquals(rec.name(), resp.name());
  }

  @Test
  public void findEmptyCacheTest() {
    var rec = new PhoneRecord(1L, "test", "111");

    when(phoneFaunaService.find(rec.id())).thenReturn(Optional.of(rec));
    var resp = phoneService.find(rec.id());
    assertEquals(rec.name(), resp.name());
  }

  @Test
  public void notFoundTest() {
    when(phoneFaunaService.find(1L)).thenReturn(Optional.empty());
    assertThrows(PhoneBookException.class, () -> phoneService.find(1L));
  }

  @Test
  public void removeTest() {
    var rec = new PhoneRecord(1L, "test", "111");
    cacheService.save(rec);

    doNothing().when(phoneFaunaService).remove(any(Long.class));
    phoneService.remove(rec.id());

    var res = cacheService.find(rec.id());
    assertTrue(res.isEmpty());

    verify(phoneFaunaService, times(1)).remove(any(Long.class));
  }
}
