package com.example.reactive.infrastructure.config.kafka.consumer;

import com.example.reactive.application.event.message.ContractCreatedMessage;
import org.springframework.stereotype.Component;

@Component
public class ContractCreatedMessageDeserializer extends
    KafkaMessageDeserializer<ContractCreatedMessage> {

  public ContractCreatedMessageDeserializer() {
    super(ContractCreatedMessage.class);
  }
}
