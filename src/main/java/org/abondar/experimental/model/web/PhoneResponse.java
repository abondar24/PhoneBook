package org.abondar.experimental.model.web;


import io.micronaut.core.annotation.Introspected;

import java.util.Date;

@Introspected
public record PhoneResponse(long id, Date createdAt) {
}
