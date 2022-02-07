package org.abondar.experimental.model.web.response.graphql;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record GraphqlData<T>(
        @JsonAlias({ "createContact", "updateContact","findContactByID","deleteContact" })
        T data
) {
}
