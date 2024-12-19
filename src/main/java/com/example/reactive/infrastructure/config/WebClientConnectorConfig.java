package com.example.reactive.infrastructure.config;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConnectorConfig {

  @Bean
  public HttpClient httpClient() {
    ConnectionProvider provider = ConnectionProvider.builder("connectionProviderConfig")
        .maxConnections(100) // max open concurrent connection at a time
        .maxIdleTime(Duration.ofSeconds(20)) // maximum amount of time a connection can remain idle in the pool before being closed
        .maxLifeTime(Duration.ofMinutes(5)) // time to live
        .pendingAcquireMaxCount(200)  // maximum number of requests that can be pending while waiting for a connection from the pool
        .build();

    return HttpClient.create(provider)
        .doOnConnected(connection -> connection
            .addHandlerLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS))
            .addHandlerLast(new WriteTimeoutHandler(10, TimeUnit.SECONDS))
        );
  }

  @Bean
  public WebClient httpReactorClient(HttpClient httpClient) {
    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
    return WebClient.builder().clientConnector(connector).build();
  }
}
