package com.example.reactive.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@ControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(DataConflictException.class)
  public ResponseEntity<ErrorsMessage> handleReactiveException(Exception e) {
    ErrorsMessage errorsMessage = new ErrorsMessage(HttpStatus.CONFLICT.name(), e.getMessage());
    return new ResponseEntity<>(errorsMessage, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(AsyncRequestTimeoutException.class)
  public ResponseEntity<ErrorsMessage> handleAsyncTimeoutException() {
    ErrorsMessage errorsMessage = new ErrorsMessage(HttpStatus.REQUEST_TIMEOUT.name(),
        "Request timed out");
    return new ResponseEntity<>(errorsMessage, HttpStatus.REQUEST_TIMEOUT);
  }
}
