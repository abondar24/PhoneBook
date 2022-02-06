package org.abondar.experimental.exception.handler;

import com.faunadb.client.errors.UnauthorizedException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.abondar.experimental.exception.PhoneBookException;

@Produces
@Singleton
@Requires(classes = {UnauthorizedException.class, ExceptionHandler.class})
public class UnauthorizedExceptionHandler  implements ExceptionHandler<UnauthorizedException, HttpResponse<?>>{
    @Override
    public HttpResponse<?> handle(HttpRequest request, UnauthorizedException exception) {
        return HttpResponse.status(HttpStatus.BAD_GATEWAY).body("Fauna server unauthorized");
    }
}