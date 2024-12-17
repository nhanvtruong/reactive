package com.example.reactive.domain.events;

import com.example.reactive.infrastructure.adapter.r2dbc.model.ContractModel;

public final class ContractCreatedEvent extends ContractEvent {

  public ContractCreatedEvent(ContractModel contractModel) {
    super(contractModel);
  }
}
