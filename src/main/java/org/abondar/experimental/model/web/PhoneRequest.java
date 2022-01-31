package org.abondar.experimental.model.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PhoneRequest(
        @NotNull
                @NotBlank
        String name,

        @NotNull
                @NotBlank
        String phoneNumber) {
}
