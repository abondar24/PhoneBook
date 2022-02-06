package org.abondar.experimental.config;

import com.faunadb.client.FaunaClient;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.net.MalformedURLException;

@Singleton
@Context
public class FaunaClientConfig {

  @Value("${fauna.secret}")
  private String faunaSecret;

  @Value("${fauna.endpoint}")
  private String faunaEndpoint;

  private FaunaClient client;

  @PostConstruct
  public void initClient() throws MalformedURLException {

    this.client = FaunaClient.builder().withEndpoint(faunaEndpoint).withSecret(faunaSecret).build();
  }

  public FaunaClient getClient() {
    return client;
  }
}
