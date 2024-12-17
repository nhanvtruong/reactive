package com.example.reactive.application.exceptions;

public class InvalidContractStatusException extends RuntimeException {

  public InvalidContractStatusException(String message) {
    super(message);
  }
}
