package org.abondar.experimental.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.dao.PhoneFaunaRepository;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.PhoneRequest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
public class PhoneFaunaServiceTest {

  @Inject private PhoneFaunaService phoneFaunaService;

  @Inject private PhoneFaunaRepository repository;

  @MockBean(PhoneFaunaRepository.class)
  PhoneFaunaRepository repository() {
    return mock(PhoneFaunaRepository.class);
  }

  @Test
  public void saveOrUpdateRecordTest() {
    var req = new PhoneRequest("test", "9888");

    var idCf = CompletableFuture.completedFuture(1L);
    CompletableFuture<Void> saveCf = CompletableFuture.completedFuture(null);
    when(repository.nextId()).thenReturn(idCf);
    doNothing().when(repository).saveOrUpdateRecord(any(PhoneRecord.class));

    var res = phoneFaunaService.saveOrUpdateRecord(req);
    assertEquals(1L, res.id());
    assertEquals(req.name(), res.name());
    assertEquals(req.phoneNumber(), res.phoneNumber());
  }

  @Test
  public void findTest() {
    var rec = new PhoneRecord(1L, "test", "test");

    CompletableFuture<PhoneRecord> resCF = CompletableFuture.completedFuture(rec);
    when(repository.find(rec.id())).thenReturn(resCF);

    var res = phoneFaunaService.find(rec.id());
    assertNotNull(res);
  }

  @Test
  public void removeTest() {

    doNothing().when(repository).remove(1L);
    phoneFaunaService.remove(1L);
    verify(repository, times(1)).remove(1L);
  }
}
