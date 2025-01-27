package com.example.reactive.application.exceptions;

public class KafkaMessageException extends RuntimeException {

  public KafkaMessageException(String message) {
    super(message);
  }
}
