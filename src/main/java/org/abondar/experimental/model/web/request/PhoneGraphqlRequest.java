package org.abondar.experimental.model.web.request;

public class PhoneGraphqlRequest {

  public static final String saveRequest =
      "{\"query\":\"mutation saveContact { createContact(data:{name:\"%s\" phoneNumber:\"%s\" }){_id}}\",\"variables\":{}}";

  public static final String updateRequest =
      "{\"query\":\"mutation update { updateContact(id:\"%s\",data:{name:\"%s\"  phoneNumber:\"%s\"}){name phoneNumber}}\",\"variables\":{}}";

  public static final String findRequest =
      "{\"query\":\"query find { findContactByID(id:\"%s\"){name, phoneNumber}}\",\"variables\":{}}";

  public static final String deleteRequest =
      "{\"query\":\"mutation remove { deleteContact(id:\"%s\"){_id}}\",\"variables\":{}}";

  private PhoneGraphqlRequest() {}
}
