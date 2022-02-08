package org.abondar.experimental.model.web.request;

public class PhoneGraphqlRequest {

  public static final String saveRequest =
      "{\"query\":\"mutation { createContact(data:{name:\\\"%s\\\" phoneNumber:\\\"%s\\\" }){_id}}\",\"variables\":{}}";

  public static final String updateRequest =
      "{\"query\":\"mutation { updateContact(id:\\\"%s\\\",data:{name:\\\"%s\\\"  phoneNumber:\\\"%s\\\"}){name phoneNumber}}\",\"variables\":{}}";

  public static final String findRequest = "{\"query\":\"query {findContactByID(id: \\\"%s\\\") {_id}}\",\"variables\":{}}";
  public static final String deleteRequest =
      "{\"query\":\"mutation { deleteContact(id:\\\"%s\\\"){_id}}\",\"variables\":{}}";

  private PhoneGraphqlRequest() {}
}
