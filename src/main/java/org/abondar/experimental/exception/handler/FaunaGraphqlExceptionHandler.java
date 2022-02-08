package org.abondar.experimental.exception.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.abondar.experimental.exception.FaunaGraphqlException;

@Produces
@Singleton
@Requires(classes = {FaunaGraphqlException.class, ExceptionHandler.class})
public class FaunaGraphqlExceptionHandler
    implements ExceptionHandler<FaunaGraphqlException, HttpResponse<?>> {
  @Override
  public HttpResponse<?> handle(HttpRequest request, FaunaGraphqlException exception) {
    return HttpResponse.status(HttpStatus.BAD_GATEWAY).body(exception.getMessage());
  }
}
