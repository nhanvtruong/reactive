package com.example.reactive.infrastructure.config.kafka.producer;

import com.example.reactive.application.event.message.ContractCreatedMessage;
import com.example.reactive.infrastructure.config.properties.KafkaPropertiesConfig;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaProducerConfig {

  private final KafkaPropertiesConfig kafkaPropertiesConfig;

  @Bean
  public ProducerFactory<String, ContractCreatedMessage> contractCreatedMessageProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaPropertiesConfig.getBootstrapServers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        org.apache.kafka.common.serialization.StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        ContractCreatedMessageSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, ContractCreatedMessage> contractCreatedTemplate() {
    return new KafkaTemplate<>(contractCreatedMessageProducerFactory());
  }

}
