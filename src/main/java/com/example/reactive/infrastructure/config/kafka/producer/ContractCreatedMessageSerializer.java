package com.example.reactive.infrastructure.config.kafka.serializer;

import com.example.reactive.application.event.message.ContractCreatedMessage;
import org.springframework.stereotype.Component;

@Component
public class ContractCreatedMessageSerializer extends
    KafkaMessageSerializer<ContractCreatedMessage> {
}
