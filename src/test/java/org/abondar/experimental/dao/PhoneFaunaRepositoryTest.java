package org.abondar.experimental.dao;

import com.faunadb.client.query.Expr;
import com.faunadb.client.types.Value;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.client.FaunaDatabaseClient;
import org.abondar.experimental.model.db.PhoneRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class PhoneFaunaRepositoryTest {

  private static com.faunadb.client.FaunaClient client;
  @Inject private PhoneFaunaRepository repository;
  @Inject private FaunaDatabaseClient dbClient;

  @BeforeAll
  public static void init() {
    client = mock(com.faunadb.client.FaunaClient.class);
  }

  @MockBean(FaunaDatabaseClient.class)
  public FaunaDatabaseClient config() {
    return mock(FaunaDatabaseClient.class);
  }

  @Test
  public void nextIdTest() {
    var idRes = Value.from("0");
    CompletableFuture<Value> cf = CompletableFuture.completedFuture(idRes.get());

    when(dbClient.getClient()).thenReturn(client);
    when(client.query(any(Expr.class))).thenReturn(cf);

    var res = repository.nextId().join();
    assertEquals(0L, Long.parseLong(res));
  }

  @Test
  public void findTest() {
    var rec = new PhoneRecord(1L, "test", "test");
    var recVal = Value.from(rec);
    CompletableFuture<Value> cf = CompletableFuture.completedFuture(recVal.get());

    when(dbClient.getClient()).thenReturn(client);
    when(client.query(any(Expr.class))).thenReturn(cf);

    var res = repository.find(rec.id());
    assertNotNull(res);
  }
}
