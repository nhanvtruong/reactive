package com.example.reactive.infrastructure.adapter.r2dbc.model;

import com.example.reactive.domain.events.ContractEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "contract")
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ContractModel {

  @Id
  private Long id;

  private String description;

  private String status;

  private final String contractKey;

  @CreatedDate
  private final LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Transient
  private final List<ContractEvent> domainEvents = new ArrayList<>();

  @DomainEvents
  public List<ContractEvent> publishDomainEvents() {
    return new ArrayList<>(domainEvents);
  }

  @AfterDomainEventPublication
  public void clearDomainEvents() {
    domainEvents.clear();
  }

  public void onContractCreatedEvent() {
    domainEvents.add(new ContractEvent(this));
  }

}
