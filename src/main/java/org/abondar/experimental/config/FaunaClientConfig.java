package org.abondar.experimental.config;

import com.faunadb.client.FaunaClient;
import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.net.MalformedURLException;

@Singleton
public class FaunaClientConfig {

  @Value("${fauna.secret}")
  protected String faunaSecret;

  @Value("${fauna.endpoint}")
  protected String faunaEndpoint;

  private FaunaClient client;

  @PostConstruct
  public void initClient() throws MalformedURLException {

    this.client = FaunaClient.builder().withEndpoint(faunaEndpoint).withSecret(faunaSecret).build();
  }

  public FaunaClient getClient() {
    return client;
  }
}
