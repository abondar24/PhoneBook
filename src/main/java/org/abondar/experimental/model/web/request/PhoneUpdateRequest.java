package org.abondar.experimental.model.web.request;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
public record PhoneUpdateRequest(

        @NotNull
        @NotBlank
        long id,


        String name,


        String phoneNumber
){}
