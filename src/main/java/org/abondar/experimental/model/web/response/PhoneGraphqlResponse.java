package org.abondar.experimental.model.web.response;

import io.micronaut.core.annotation.Introspected;
import org.abondar.experimental.model.web.response.graphql.GraphqlData;

// sample graphql responses
//{
//        "data": {
//        "createContact": {
//        "_id": "322890747066974407"
//        }
//        }
//        }


//{
//        "data": {
//        "updateContact": {
//        "name": "GLTEST",
//        "phoneNumber": "1122"
//        }
//        }
//        }

//{
//        "data": {
//        "findContactByID": {
//        "name": "asdsad",
//        "phoneNumber": "1312"
//        }
//        }
//        }

//{
//        "data": {
//        "deleteContact": {
//        "_id": "322890747066974407"
//        }
//        }
//        }
@Introspected
public record PhoneGraphqlResponse<T>(

        GraphqlData<T> data
){
}
