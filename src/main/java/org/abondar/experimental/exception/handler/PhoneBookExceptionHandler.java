package org.abondar.experimental.exception.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.abondar.experimental.exception.PhoneBookException;

@Produces
@Singleton
@Requires(classes = {PhoneBookException.class, ExceptionHandler.class})
public class PhoneBookExceptionHandler
    implements ExceptionHandler<PhoneBookException, HttpResponse<?>> {

  @Override
  public HttpResponse<?> handle(HttpRequest request, PhoneBookException exception) {
    return HttpResponse.notFound(exception.getMessage());
  }
}
