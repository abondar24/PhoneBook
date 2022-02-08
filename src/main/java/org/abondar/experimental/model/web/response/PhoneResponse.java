package org.abondar.experimental.model.web.response;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonClassDescription
public record PhoneResponse(String name,String phoneNumber) {
}
