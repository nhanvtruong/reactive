package com.example.reactive.exceptions;

public class DataConflictException extends RuntimeException {
  public DataConflictException(String message) {
    super(message);
  }
}
