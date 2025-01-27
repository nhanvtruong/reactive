package com.example.reactive.application.event.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractCreatedMessage {

  private String description;
  private String status;
  private String contractKey;

  @Override
  public String toString() {
    return "ContractCreatedMessage{" +
        "description='" + description + '\'' +
        ", status='" + status + '\'' +
        ", contractKey='" + contractKey + '\'' +
        '}';
  }
}
