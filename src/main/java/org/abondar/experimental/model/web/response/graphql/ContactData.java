package org.abondar.experimental.model.web.response.graphql;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record ContactData(String name, String phoneNumber) {
}
