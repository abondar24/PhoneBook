package org.abondar.experimental.exception.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.abondar.experimental.exception.FaunaGraphqlDisabledException;
import org.abondar.experimental.exception.Messages;

@Produces
@Singleton
@Requires(classes = {FaunaGraphqlDisabledException.class, ExceptionHandler.class})
public class FaunaGraphqlDisabledExceptionHandler
    implements ExceptionHandler<FaunaGraphqlDisabledException, HttpResponse<?>> {
  @Override
  public HttpResponse<?> handle(HttpRequest request, FaunaGraphqlDisabledException exception) {
    return HttpResponse.status(HttpStatus.FORBIDDEN).body(Messages.GRAPHQL_DISABLED);
  }
}
