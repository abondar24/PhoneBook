package org.abondar.experimental.dao;

import java.util.concurrent.CompletableFuture;

public interface FaunaRepository<T> {

  CompletableFuture<Long> nextId();

  CompletableFuture<Void> saveOrUpdateRecord(T record);

  CompletableFuture<T> find(long id);

  CompletableFuture<Void> remove(long id);
}
