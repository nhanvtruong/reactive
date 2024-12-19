package com.example.reactive.infrastructure.adapter.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class HttpWebClient extends HttpRequestBuilder<HttpWebClient> {

  private final WebClient reactorNettyClient;

  public <T> Mono<T> doGet(Class<T> responseType, MediaType mediaType) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(super.uri.toString());
    super.queryParams.forEach(uriBuilder::queryParam);
    return reactorNettyClient.get()
        .uri(uriBuilder.toUriString())
        .accept(mediaType)
        .retrieve()
        .bodyToMono(responseType);
  }
}
