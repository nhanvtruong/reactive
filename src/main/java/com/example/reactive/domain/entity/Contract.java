package com.example.reactive.domain.entity;

import com.example.reactive.application.exceptions.InvalidContractStatusException;
import com.example.reactive.domain.vo.Status;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Contract {

  private Long id;

  private String description;

  private String status;

  private final String contractKey;

  private final LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public void createContract() {
    this.status = Status.CREATED.name();
  }

  public void updateContractStatus(Status status) {
    if (status.equals(Status.CREATED)) {
      throw new InvalidContractStatusException("Cannot change status to CREATED");
    }
    this.status = status.name();
  }

}
