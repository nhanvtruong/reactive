package com.example.reactive.infrastructure.config.kafka.producer;

import com.example.reactive.application.exceptions.KafkaConvertMessageException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serializer;

@Log4j2
public class KafkaMessageSerializer<T> implements Serializer<T> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] serialize(String s, T data) {
    try {
      if (data == null) {
        return new byte[0];
      }

      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      log.error(e);
      throw new KafkaConvertMessageException("Unable to serialize message");
    }
  }
}
