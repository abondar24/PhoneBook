package org.abondar.experimental.client;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.abondar.experimental.model.web.response.PhoneGraphqlResponse;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.AUTHORIZATION;

@Singleton
@Requires(property = "fauna.graphql.enabled" , value = "true")
public class FaunaGraphqlClient {

  @Value("${fauna.secret}")
  protected String faunaSecret;

  @Value("${fauna.graphql.endpoint}")
  protected String graphqlEndpoint;

  @Inject
  @Client
  protected HttpClient client;

  public  HttpResponse<PhoneGraphqlResponse> performRequest(String body) {
    var uri = UriBuilder.of(graphqlEndpoint).build();
    HttpRequest<String> req =
        HttpRequest.POST(uri, body)
            .header(AUTHORIZATION, "Bearer " + faunaSecret)
            .header(ACCEPT, "application/vnd.github.v3+json, application/json");
    return client.toBlocking().exchange(req,PhoneGraphqlResponse.class);
  }
}
