package com.kafka.consumer.controller;

import org.springframework.stereotype.Component;

@Component
public class ContractCreatedMessageDeserializer extends
    KafkaMessageDeserializer<ContractCreatedMessage> {

  public ContractCreatedMessageDeserializer() {
    super(ContractCreatedMessage.class);
  }
}
