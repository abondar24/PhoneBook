package org.abondar.experimental.model.web.response;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record PhoneResponse(String name,String phone) {
}
