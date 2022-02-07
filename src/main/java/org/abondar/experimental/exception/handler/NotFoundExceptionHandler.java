package org.abondar.experimental.exception.handler;

import com.faunadb.client.errors.NotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.abondar.experimental.exception.Messages;

@Produces
@Singleton
@Requires(classes = {NotFoundException.class, ExceptionHandler.class})
public class NotFoundExceptionHandler
    implements ExceptionHandler<NotFoundException, HttpResponse<?>> {
  @Override
  public HttpResponse<?> handle(HttpRequest request, NotFoundException exception) {
    return HttpResponse.notFound(Messages.NOT_FOUND);
  }
}
