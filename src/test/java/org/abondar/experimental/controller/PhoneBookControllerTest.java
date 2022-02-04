package org.abondar.experimental.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.abondar.experimental.model.db.PhoneRecord;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.model.web.response.PhoneCreateResponse;
import org.abondar.experimental.model.web.response.PhoneResponse;
import org.abondar.experimental.model.web.response.PhoneUpdateResponse;
import org.abondar.experimental.service.PhoneFaunaService;
import org.abondar.experimental.service.impl.PhoneFaunaServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class PhoneBookControllerTest {

  @Inject private EmbeddedServer server;

  @Inject
  @Client("/")
  private HttpClient client;

  @Inject private PhoneFaunaService phoneFaunaService;

  @MockBean(PhoneFaunaServiceImpl.class)
  PhoneFaunaService phoneFaunaService() {
    return mock(PhoneFaunaService.class);
  }

  @Test
  public void saveTest() {
    var req = new PhoneCreateRequest("test", "111");
    var rec = new PhoneRecord(1L, req.name(), req.phoneNumber());

    when(phoneFaunaService.save(req)).thenReturn(rec);

    HttpResponse<PhoneCreateResponse> resp =
        client
            .toBlocking()
            .exchange(HttpRequest.POST("/v1/phonebook", req), PhoneCreateResponse.class);

    assertEquals(201, resp.code());

    var body = resp.body();

    assertNotNull(body);
    assertEquals(rec.id(), body.id());
    assertNotNull(body.createdAt());
  }

  @Test
  public void updateTest() {
    var req = new PhoneUpdateRequest(1L, "test", "111");
    var rec = new PhoneRecord(1L, req.name(), req.phoneNumber());

    when(phoneFaunaService.update(req)).thenReturn(rec);

    HttpResponse<PhoneUpdateResponse> resp =
        client
            .toBlocking()
            .exchange(HttpRequest.PUT("/v1/phonebook", req), PhoneUpdateResponse.class);

    assertEquals(200, resp.code());

    var body = resp.body();

    assertNotNull(body);
    assertNotNull(body.updatedAt());
  }

  @Test
  public void updatePartialTest() {
    var req = new PhoneUpdateRequest(1L, null, "111");
    var rec = new PhoneRecord(1L, req.name(), req.phoneNumber());

    when(phoneFaunaService.update(req)).thenReturn(rec);

    HttpResponse<PhoneUpdateResponse> resp =
        client
            .toBlocking()
            .exchange(HttpRequest.PUT("/v1/phonebook", req), PhoneUpdateResponse.class);

    assertEquals(200, resp.code());

    var body = resp.body();

    assertNotNull(body);
    assertNotNull(body.updatedAt());
  }

  @Test
  public void findTest() {
    var rec = new PhoneRecord(1L, "test", "1111");

    when(phoneFaunaService.find(1L)).thenReturn(Optional.of(rec));

    HttpResponse<PhoneResponse> resp =
        client.toBlocking().exchange(HttpRequest.GET("/v1/phonebook/1"), PhoneResponse.class);

    assertEquals(200, resp.code());

    var body = resp.body();

    assertNotNull(body);
    assertEquals(rec.name(), body.name());
  }

  @Test
  public void notFoundTest() {

    assertThrows(
        HttpClientResponseException.class,
        () -> {
          client.toBlocking().exchange(HttpRequest.GET("/v1/phonebook/1"), PhoneResponse.class);
        });
  }

  @Test
  public void removeTest() {
    var req = new PhoneCreateRequest("test", "111");
    var rec = new PhoneRecord(1L, req.name(), req.phoneNumber());

    when(phoneFaunaService.save(req)).thenReturn(rec);

    HttpResponse<PhoneCreateResponse> resp =
            client
                    .toBlocking()
                    .exchange(HttpRequest.POST("/v1/phonebook", req), PhoneCreateResponse.class);

    assertEquals(201, resp.code());

    HttpResponse<?> delResp =
            client.toBlocking().exchange(HttpRequest.DELETE("/v1/phonebook/1"));
    assertEquals(200, delResp.code());
  }

}
