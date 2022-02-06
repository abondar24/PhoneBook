package org.abondar.experimental.model.db;

import com.faunadb.client.types.FaunaConstructor;
import com.faunadb.client.types.FaunaField;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record PhoneRecord(
        long id,

        @FaunaField
        String name,

        @FaunaField
        String phoneNumber) {

    @FaunaConstructor
    public PhoneRecord(@FaunaField("id") long id, @FaunaField("name")
            String name, @FaunaField("phoneNumber")
                               String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
