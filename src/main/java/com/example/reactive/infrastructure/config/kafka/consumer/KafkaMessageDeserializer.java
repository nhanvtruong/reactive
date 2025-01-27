package com.example.reactive.infrastructure.config.kafka.consumer;

import com.example.reactive.application.exceptions.KafkaConvertMessageException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;

@Getter
@Setter
@Log4j2
public abstract class KafkaMessageDeserializer<T> implements Deserializer<T> {

  protected final ObjectMapper objectMapper = new ObjectMapper();
  protected T message;
  private final Class<T> messageType;

  protected KafkaMessageDeserializer(Class<T> messageType) {
    this.messageType = messageType;
  }

  @Override
  public T deserialize(String s, byte[] data) {
    if (data == null || data.length == 0) {
      return null;
    }

    try {
      return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), messageType);
    } catch (Exception e) {
      log.error("Deserialization error: {} ", e.getMessage());
      throw new KafkaConvertMessageException("Unable to deserialize message");
    }
  }
}
