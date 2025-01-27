package com.example.reactive.application.port;

import com.example.reactive.application.event.message.ContractCreatedMessage;
import com.example.reactive.application.event.message.Topic;

public interface ContractDataPublisher {

  void publishContractCreatedEvent(Topic topic, ContractCreatedMessage contractCreatedMessage);
}
