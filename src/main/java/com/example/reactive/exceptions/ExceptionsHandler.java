package com.example.reactive.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@Log4j2
@ControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(UniqueConstraintException.class)
  public ResponseEntity<ErrorsMessage> handleReactiveException(Exception e) {
    log.error(e.getMessage(), e);
    ErrorsMessage errorsMessage = new ErrorsMessage(HttpStatus.CONFLICT.name(),
        "Unique constraint violated");
    return new ResponseEntity<>(errorsMessage, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(AsyncRequestTimeoutException.class)
  public ResponseEntity<ErrorsMessage> handleAsyncTimeoutException() {
    ErrorsMessage errorsMessage = new ErrorsMessage(HttpStatus.REQUEST_TIMEOUT.name(),
        "Request timed out");
    return new ResponseEntity<>(errorsMessage, HttpStatus.REQUEST_TIMEOUT);
  }
}
