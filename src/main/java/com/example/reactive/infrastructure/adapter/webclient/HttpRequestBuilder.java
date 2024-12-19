package com.example.reactive.infrastructure.adapter.webclient;

import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

abstract class HttpRequestBuilder<T extends HttpRequestBuilder<T>> {

  protected StringBuilder uri = new StringBuilder();
  protected final Map<String, String> queryParams = new HashMap<>();

  @SuppressWarnings("unchecked")
  protected T self() {
    return (T) this;
  }

  public T host(@NotNull String hostName) {
    uri.append(hostName);
    return self();
  }

  public T endpoint(@NotNull String requestUrl) {
    uri.append("/").append(requestUrl);
    return self();
  }

  public T queryParam(String key, String value) {
    this.queryParams.put(key, value);
    return self();
  }

  public T queryParams(Map<String, String> params) {
    this.queryParams.putAll(params);
    return self();
  }
}
