package com.example.reactive.infrastructure.adapter.webclient;

import com.example.reactive.application.port.UserDataAdapter;
import com.example.reactive.infrastructure.config.properties.AuthServiceProperties;
import com.example.reactive.infrastructure.config.properties.ServiceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDataAdapterImpl implements UserDataAdapter {

  private final HttpWebClient httpReactorClient;

  private final ServiceProperties authServiceProperties;

  public Mono<String> getUserServiceHeartbeat() {
    return httpReactorClient
        .hostProperties(authServiceProperties)
        .resource(AuthServiceProperties.HEARTBEAT)
        .build()
        .doGet(String.class, MediaType.TEXT_PLAIN);
  }
}
