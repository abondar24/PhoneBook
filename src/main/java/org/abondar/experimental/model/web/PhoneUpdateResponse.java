package org.abondar.experimental.model.web;

import io.micronaut.core.annotation.Introspected;

import java.time.Instant;

@Introspected
public record PhoneUpdateResponse(Instant updatedAt) {
}
