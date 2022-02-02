package org.abondar.experimental.dao;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.config.FaunaClientConfig;
import org.abondar.experimental.model.db.PhoneRecord;

import java.util.concurrent.CompletableFuture;

import static com.faunadb.client.query.Language.Collection;
import static com.faunadb.client.query.Language.Create;
import static com.faunadb.client.query.Language.Delete;
import static com.faunadb.client.query.Language.Exists;
import static com.faunadb.client.query.Language.Get;
import static com.faunadb.client.query.Language.If;
import static com.faunadb.client.query.Language.NewId;
import static com.faunadb.client.query.Language.Obj;
import static com.faunadb.client.query.Language.Ref;
import static com.faunadb.client.query.Language.Select;
import static com.faunadb.client.query.Language.Update;
import static com.faunadb.client.query.Language.Value;
import static org.abondar.experimental.dao.DaoUtil.COLLECTION_NAME;
import static org.abondar.experimental.dao.DaoUtil.DATA_VAL;

@Singleton
public class PhoneFaunaRepository implements FaunaRepository<PhoneRecord> {

  @Inject private FaunaClientConfig client;

  @Value("${fauna.secret}")
  private String faunaKey;

  @Override
  public CompletableFuture<Long> nextId() {

    return client.getClient().query(NewId()).thenApply(value -> value.to(Long.class).get());
  }

  @Override
  public void saveOrUpdateRecord(PhoneRecord record) {

    var idVal = Value(record.id());
    var recordVal = Value(record);
    var saveQuery =
        Select(
            Value(DATA_VAL),
            If(
                Exists(Ref(Collection(COLLECTION_NAME), idVal)),
                Update(Ref(Collection(COLLECTION_NAME), idVal), Obj(DATA_VAL, recordVal)),
                Create(Ref(Collection(COLLECTION_NAME), idVal), Obj(DATA_VAL, recordVal))));

    client.getClient().query(saveQuery).thenApply(value -> value.to(Void.class).get());
  }

  @Override
  public CompletableFuture<PhoneRecord> find(long id) {
    var idVal = Value(id);

    var findQuery = Select(Value(DATA_VAL), Get(Ref(Collection(COLLECTION_NAME), idVal)));

    return client
        .getClient()
        .query(findQuery)
        .thenApply(value -> value.to(PhoneRecord.class).get());
  }

  @Override
  public void remove(long id) {
    var idVal = Value(id);

    var deleteQuery = Select(Value(DATA_VAL), Delete(Ref(Collection(COLLECTION_NAME), idVal)));

    client.getClient().query(deleteQuery).thenApply(value -> value.to(Void.class).get());
  }
}
