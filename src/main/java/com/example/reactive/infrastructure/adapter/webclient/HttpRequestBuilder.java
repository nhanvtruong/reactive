package com.example.reactive.infrastructure.adapter.webclient;

import com.example.reactive.infrastructure.config.properties.ServiceProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

abstract class HttpRequestBuilder<T extends HttpRequestBuilder<T>> {

  protected String uri;
  protected UriComponentsBuilder uriBuilder;
  protected ServiceProperties serviceProperties;

  @SuppressWarnings("unchecked")
  protected T self() {
    return (T) this;
  }

  public T hostProperties(ServiceProperties serviceProperties) {
    this.serviceProperties = serviceProperties;
    uriBuilder.scheme("http")
        .host(serviceProperties.getHostname())
        .port(serviceProperties.getPort());
    return self();
  }

  public T resource(@NotNull String resourceName) {
    uriBuilder.path(serviceProperties.getResourceUrl(resourceName));
    return self();
  }

  public T queryParam(@NotNull String key, @NotNull String value) {
    uriBuilder.queryParam(key, value);
    return self();
  }

  public T queryParams(@NotNull @NotEmpty Map<String, String> queryParams) {
    for (Map.Entry<String, String> entry : queryParams.entrySet()) {
      uriBuilder.queryParam(entry.getKey(), entry.getValue());
    }
    return self();
  }

  public T build() {
    uri = uriBuilder.toUriString();
    return self();
  }
}
