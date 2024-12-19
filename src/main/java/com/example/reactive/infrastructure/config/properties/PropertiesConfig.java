package com.example.reactive.infrastructure.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
    ApplicationProperties.class,
    AuthServiceProperties.class
})
public class PropertiesConfig {

}
