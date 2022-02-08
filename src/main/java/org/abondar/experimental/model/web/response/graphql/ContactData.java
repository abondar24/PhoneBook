package org.abondar.experimental.model.web.response.graphql;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

@Introspected
@JsonClassDescription
public record ContactData(
        @Nullable
        String name,

        @Nullable
        String phoneNumber) {
}
