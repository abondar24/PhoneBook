package org.abondar.experimental.service;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.simple.SimpleHttpResponseFactory;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.client.FaunaGraphqlClient;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.model.web.response.PhoneGraphqlResponse;
import org.abondar.experimental.model.web.response.graphql.ContactData;
import org.abondar.experimental.model.web.response.graphql.GraphqlData;
import org.abondar.experimental.model.web.response.graphql.IdData;
import org.abondar.experimental.service.impl.PhoneFaunaGraphqlServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
@Property(name = "fauna.graphql.enabled", value = "true")
public class PhoneFaunaGraphqlServiceTest {

  @Inject private PhoneFaunaGraphqlServiceImpl service;

  @Inject private FaunaGraphqlClient client;

  @MockBean(FaunaGraphqlClient.class)
  public FaunaGraphqlClient config() {
    return mock(FaunaGraphqlClient.class);
  }

  @Test
  public void saveTest() {
    var data = new IdData("1");
    var glData = new GraphqlData(null, data);
    var resp = new PhoneGraphqlResponse(glData);

    HttpResponse<PhoneGraphqlResponse> httpResponse =
        new SimpleHttpResponseFactory().status(HttpStatus.OK).body(resp);

    when(client.performRequest(anyString())).thenReturn(httpResponse);

    var req = new PhoneCreateRequest("res", "1111");
    var res = service.save(req);

    assertEquals(1L, res.id());
    assertEquals(req.name(), res.name());
    assertEquals(req.phoneNumber(), req.phoneNumber());
  }

  @Test
  public void updateTest() {
    var data = new ContactData("res", "1111");
    var glData = new GraphqlData(data, null);
    var resp = new PhoneGraphqlResponse(glData);

    HttpResponse<PhoneGraphqlResponse> httpResponse =
        new SimpleHttpResponseFactory().status(HttpStatus.OK).body(resp);

    when(client.performRequest(anyString())).thenReturn(httpResponse);

    var req = new PhoneUpdateRequest(1L, "res", "1111");
    var res = service.update(req);

    assertEquals(1L, res.id());
    assertEquals(req.name(), res.name());
    assertEquals(req.phoneNumber(), req.phoneNumber());
  }

  @Test
  public void findTest() {
    var data = new ContactData("res", "1111");
    var glData = new GraphqlData(data, null);
    var resp = new PhoneGraphqlResponse(glData);

    HttpResponse<PhoneGraphqlResponse> httpResponse =
        new SimpleHttpResponseFactory().status(HttpStatus.OK).body(resp);

    when(client.performRequest(anyString())).thenReturn(httpResponse);

    var res = service.find(1L);

    assertTrue(res.isPresent());
    assertEquals(1L, res.get().id());
    assertEquals(data.name(), res.get().name());
    assertEquals(data.phoneNumber(), res.get().phoneNumber());
  }

  @Test
  public void notFoundTest() {
    var glData = new GraphqlData(null, null);
    var resp = new PhoneGraphqlResponse(glData);

    HttpResponse<PhoneGraphqlResponse> httpResponse =
        new SimpleHttpResponseFactory().status(HttpStatus.OK).body(resp);

    when(client.performRequest(anyString())).thenReturn(httpResponse);

    var res = service.find(1L);

    assertTrue(res.isEmpty());
  }

  @Test
  public void removeTest() {
    var data = new IdData("1");
    var glData = new GraphqlData(null, data);
    var resp = new PhoneGraphqlResponse(glData);

    HttpResponse<PhoneGraphqlResponse> httpResponse =
        new SimpleHttpResponseFactory().status(HttpStatus.OK).body(resp);

    when(client.performRequest(anyString())).thenReturn(httpResponse);

    service.remove(1L);

    verify(client, times(1)).performRequest(anyString());
  }
}
