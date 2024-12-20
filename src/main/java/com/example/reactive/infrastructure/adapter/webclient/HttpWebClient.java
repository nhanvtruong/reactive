package com.example.reactive.infrastructure.adapter.webclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class HttpWebClient extends HttpRequestBuilder<HttpWebClient> {

  private final WebClient reactorNettyClient;

  public HttpWebClient(WebClient reactorNettyClient) {
    this.reactorNettyClient = reactorNettyClient;
    uriBuilder = UriComponentsBuilder.newInstance();
  }

  public <T> Mono<T> doGet(Class<T> responseType, MediaType mediaType) {
    return reactorNettyClient.get()
        .uri(uri)
        .accept(mediaType)
        .retrieve()
        .bodyToMono(responseType);
  }
}
