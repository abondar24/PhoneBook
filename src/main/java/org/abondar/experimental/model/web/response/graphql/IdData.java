package org.abondar.experimental.model.web.response.graphql;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

@Introspected
@JsonClassDescription
public record IdData(

        @JsonProperty("_id")
                @Nullable
        String id) {
}
