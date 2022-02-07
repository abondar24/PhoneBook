package org.abondar.experimental.model.web.response.graphql;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record IdData(

        @JsonProperty("_id")
        String id) {
}
