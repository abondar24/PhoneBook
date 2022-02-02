package org.abondar.experimental.dao;

import com.faunadb.client.FaunaClient;
import com.faunadb.client.query.Expr;
import com.faunadb.client.types.Value;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.config.FaunaClientConfig;
import org.abondar.experimental.model.db.PhoneRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.faunadb.client.query.Language.Collection;
import static com.faunadb.client.query.Language.Delete;
import static com.faunadb.client.query.Language.Ref;
import static com.faunadb.client.query.Language.Select;
import static org.abondar.experimental.dao.DaoUtil.COLLECTION_NAME;
import static org.abondar.experimental.dao.DaoUtil.DATA_VAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
public class PhoneFaunaRepositoryTest {

  private static FaunaClient client;
  @Inject private PhoneFaunaRepository repository;
  @Inject private FaunaClientConfig config;

  @BeforeAll
  public static void init() {
    client = mock(FaunaClient.class);
  }

  @MockBean(FaunaClientConfig.class)
  public FaunaClientConfig config() {
    return mock(FaunaClientConfig.class);
  }

  @Test
  public void nextIdTest() {
    var idRes = Value.from(0L);
    CompletableFuture<Value> cf = CompletableFuture.completedFuture(idRes.get());

    when(config.getClient()).thenReturn(client);
    when(client.query(any(Expr.class))).thenReturn(cf);

    var res = repository.nextId().join();
    assertEquals(0L, res);
  }

  @Test
  public void findTest() {
    var rec = new PhoneRecord(1L, "test", "test");
    var recVal = Value.from(rec);
    CompletableFuture<Value> cf = CompletableFuture.completedFuture(recVal.get());

    when(config.getClient()).thenReturn(client);
    when(client.query(any(Expr.class))).thenReturn(cf);

    var res = repository.find(rec.id());
    assertNotNull(res);
  }


}
