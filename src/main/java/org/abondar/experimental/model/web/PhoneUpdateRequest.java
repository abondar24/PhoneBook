package org.abondar.experimental.model.web;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
public record PhoneUpdateRequest(

        @NotNull
        @NotBlank
        long id,

        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String phoneNumber
){}
