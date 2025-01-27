package com.example.reactive.application.exceptions;

public class KafkaConvertMessageException extends RuntimeException {

  public KafkaConvertMessageException(String message) {
    super(message);
  }
}
