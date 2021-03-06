package org.abondar.experimental.dao;

import java.util.concurrent.CompletableFuture;

public interface FaunaRepository<T> {

  CompletableFuture<String> nextId();

  void saveOrUpdateRecord(T record);

  CompletableFuture<T> find(long id);

  void remove(long id);
}
