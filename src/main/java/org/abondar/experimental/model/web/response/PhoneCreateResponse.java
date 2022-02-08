package org.abondar.experimental.model.web.response;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import io.micronaut.core.annotation.Introspected;

import java.time.Instant;


@Introspected
@JsonClassDescription
public record PhoneCreateResponse(long id, Instant createdAt) {
}
