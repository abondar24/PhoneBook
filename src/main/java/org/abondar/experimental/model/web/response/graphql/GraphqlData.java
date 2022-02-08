package org.abondar.experimental.model.web.response.graphql;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

@Introspected
@JsonClassDescription
public record GraphqlData(
        @JsonAlias({"updateContact","findContactByID"})
                @Nullable
        ContactData contactData,

        @JsonAlias({"createContact","deleteContact"})
                @Nullable
        IdData idData
) {
}
