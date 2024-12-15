package com.example.reactive.application.exceptions;

public class UniqueConstraintException extends RuntimeException {
  public UniqueConstraintException(String message) {
    super(message);
  }
}
