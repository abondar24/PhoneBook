package org.abondar.experimental.config;

import com.faunadb.client.FaunaClient;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import org.abondar.experimental.model.web.PhoneRequest;

@Singleton
public class FaunaClientConfig {

  @Value("${fauna.secret}")
  private String faunaKey;

  public FaunaClient faunaClient() {

    return FaunaClient.builder().withSecret(faunaKey).build();
  }
}
