package org.abondar.experimental.config;

import com.faunadb.client.FaunaClient;
import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class FaunaClientConfig {

  @Value("${fauna.secret}")
  private String faunaKey;

  private FaunaClient client;

  @PostConstruct
  public void initClient() {

   this.client= FaunaClient.builder()
            .withSecret(faunaKey).build();
  }

  public FaunaClient getClient() {
    return client;
  }
}
