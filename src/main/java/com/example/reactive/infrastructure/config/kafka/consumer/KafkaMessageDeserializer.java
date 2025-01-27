package com.kafka.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.serialization.Deserializer;

@Getter
@Setter
public abstract class KafkaMessageDeserializer<T> implements Deserializer<T> {

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  protected final ObjectMapper objectMapper = new ObjectMapper();
  protected T message;
  private final Class<T> messageType;

  public KafkaMessageDeserializer(Class<T> messageType) {
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
      logger.severe("Serialization error: " + e.getMessage());
      throw new RuntimeException("Unable to deserialize message", e);
    }
  }
}
