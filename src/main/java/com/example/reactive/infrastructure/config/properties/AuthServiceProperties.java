package com.example.reactive.infrastructure.config.properties;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "services.auth")
public class AuthServiceProperties implements ServiceProperties {

  private String hostname;
  private Map<String, String> resources;

  @Override
  public String getHostname() {
    return hostname;
  }

  @Override
  public String getResourceUrl(String name) {
    return this.resources.get(name);
  }
}
