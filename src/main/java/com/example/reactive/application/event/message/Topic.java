package com.example.reactive.application.event.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {

  CONTRACT_CREATED("contract-created");

  private final String topicName;

}
