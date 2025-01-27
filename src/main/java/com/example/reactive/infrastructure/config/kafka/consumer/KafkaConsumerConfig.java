package com.kafka.consumer.config;

import com.kafka.consumer.controller.ContractCreatedMessage;
import com.kafka.consumer.controller.ContractCreatedMessageDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

//  @Bean
//  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
//      ConsumerFactory<String, String> consumerFactory) {
//    ConcurrentKafkaListenerContainerFactory<String, String> factory =
//        new ConcurrentKafkaListenerContainerFactory<>();
//    factory.setConsumerFactory(consumerFactory);
//    factory.setConcurrency(3);
//    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//    return factory;
//  }

  @Bean
  public ConsumerFactory<String, ContractCreatedMessage> contractCreatedMessageConsumerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        "http://localhost:9094");
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
