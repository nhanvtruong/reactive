package com.example.reactive.infrastructure.config.kafka.consumer;

import com.example.reactive.application.event.message.ContractCreatedMessage;
import com.example.reactive.infrastructure.config.properties.KafkaPropertiesConfig;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConsumerConfig {

  private final KafkaPropertiesConfig kafkaPropertiesConfig;

  @Bean
  public ConsumerFactory<String, ContractCreatedMessage> contractCreatedMessageConsumerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaPropertiesConfig.getBootstrapServers());
    configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        ContractCreatedMessageDeserializer.class);
    configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "contract-created-group");
    return new DefaultKafkaConsumerFactory<>(configProps);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, ContractCreatedMessage> contractCreatedContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, ContractCreatedMessage> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(contractCreatedMessageConsumerFactory());
    return factory;
  }
}
