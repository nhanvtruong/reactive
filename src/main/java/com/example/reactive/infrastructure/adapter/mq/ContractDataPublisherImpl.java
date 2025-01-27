package com.example.reactive.infrastructure.adapter.mq;

import com.example.reactive.application.event.message.ContractCreatedMessage;
import com.example.reactive.application.event.message.Topic;
import com.example.reactive.application.port.ContractDataPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractDataPublisherImpl implements ContractDataPublisher {

  private final KafkaTemplate<String, ContractCreatedMessage> contractCreatedTemplate;

  @Override
  public void publishContractCreatedEvent(Topic topic, ContractCreatedMessage message) {
    contractCreatedTemplate.send(topic.getTopicName(), message);
  }
}
