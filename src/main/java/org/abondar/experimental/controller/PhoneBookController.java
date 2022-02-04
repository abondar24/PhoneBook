package org.abondar.experimental.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Error;
import jakarta.inject.Inject;
import org.abondar.experimental.exception.PhoneBookException;
import org.abondar.experimental.model.web.request.PhoneCreateRequest;
import org.abondar.experimental.model.web.request.PhoneUpdateRequest;
import org.abondar.experimental.model.web.response.PhoneCreateResponse;
import org.abondar.experimental.model.web.response.PhoneResponse;
import org.abondar.experimental.model.web.response.PhoneUpdateResponse;
import org.abondar.experimental.service.PhoneService;

@Controller("/v1/phonebook")
public class PhoneBookController {

  @Inject private PhoneService phoneService;

  @Post(processes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
  public HttpResponse<PhoneCreateResponse> create(@Body PhoneCreateRequest request) {
    var response = phoneService.save(request);

    return HttpResponse.created(response);
  }

  @Put(processes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
  public HttpResponse<PhoneUpdateResponse> update(@Body PhoneUpdateRequest request) {
    var response = phoneService.update(request);

    return HttpResponse.ok(response);
  }

  @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
  public HttpResponse<PhoneResponse> find(@PathVariable(name = "id") long id) {
    var response = phoneService.find(id);

    return HttpResponse.ok(response);
  }

  @Delete(value = "/{id}", produces = MediaType.APPLICATION_JSON)
  public HttpResponse<?> remove(@PathVariable(name = "id") long id) {
    phoneService.remove(id);

    return HttpResponse.ok();
  }

}
