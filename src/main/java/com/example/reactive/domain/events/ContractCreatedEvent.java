package com.example.reactive.domain.events;

import com.example.reactive.infrastructure.adapter.r2dbc.model.ContractModel;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;

@Log4j2
@Getter
@EqualsAndHashCode(callSuper = true)
public class ContractCreatedEvent extends ApplicationEvent {

  private final ContractModel contractModel;

  public ContractCreatedEvent(ContractModel contractModel) {
    super(contractModel);
    log.info("ContractCreatedEvent at {} , contractId : {}",
        LocalDateTime.now(), contractModel.getId());
    this.contractModel = contractModel;
  }
}
