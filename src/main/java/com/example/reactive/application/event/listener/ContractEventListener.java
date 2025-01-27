package com.example.reactive.application.event.listener;

import static com.example.reactive.application.event.message.Topic.CONTRACT_CREATED;

import com.example.reactive.application.event.message.ContractCreatedMessage;
import com.example.reactive.application.port.ContractDataPublisher;
import com.example.reactive.domain.events.ContractEvent;
import com.example.reactive.infrastructure.adapter.r2dbc.model.ContractModel;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ContractEventListener {

  private final ContractDataPublisher contractDataPublisher;

  @EventListener
  public void handleContractCreatedEvent(ContractEvent event) {
    log.info("Sending event contract {} created to kafka at {}",
        event.getContractModel().getId(), LocalDateTime.now());

    ContractModel contractModel = event.getContractModel();
    ContractCreatedMessage message = ContractCreatedMessage.builder()
        .contractKey(contractModel.getContractKey())
        .description(contractModel.getDescription())
        .status(contractModel.getStatus())
        .build();
    contractDataPublisher.publishContractCreatedEvent(CONTRACT_CREATED, message);
  }

  @KafkaListener(
      topics = "contract-created",
      groupId = "contract-created-group",
      containerFactory = "contractCreatedContainerFactory"
  )
  public void testConsumer(ContractCreatedMessage message) {
    log.info(message.toString());
  }

}
