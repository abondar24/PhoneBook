package org.abondar.experimental.model.web.request;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected

public record PhoneCreateRequest(
        @NotNull
                @NotBlank
        String name,

        @NotNull
                @NotBlank
        String phoneNumber) {
}
