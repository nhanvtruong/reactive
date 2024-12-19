package com.example.reactive.infrastructure.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

  private ContractProperties contract;

  private final ServiceProperties service;

}
