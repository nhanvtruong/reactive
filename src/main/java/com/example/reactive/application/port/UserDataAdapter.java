package com.example.reactive.application.port;

import reactor.core.publisher.Mono;

public interface UserDataAdapter {

  Mono<String> getUserServiceHeartbeat();

}
