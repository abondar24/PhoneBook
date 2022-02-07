package org.abondar.experimental.client;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.net.MalformedURLException;

@Singleton
@Requires(property = "fauna.graphql.enabled" , value = "false")
public class FaunaDatabaseClient {

  @Value("${fauna.secret}")
  protected String faunaSecret;

  @Value("${fauna.endpoint}")
  protected String faunaEndpoint;

  private com.faunadb.client.FaunaClient client;

  @PostConstruct
  public void initClient() throws MalformedURLException {

    this.client = com.faunadb.client.FaunaClient.builder().withEndpoint(faunaEndpoint).withSecret(faunaSecret).build();
  }

  public com.faunadb.client.FaunaClient getClient() {
    return client;
  }
}
