package org.abondar.experimental.model.web.request;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
public record PhoneUpdateRequest(

        @NotNull
        @NotBlank
        @Min(1)
        long id,

        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String phoneNumber
){}
