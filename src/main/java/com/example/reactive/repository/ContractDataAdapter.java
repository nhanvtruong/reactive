package com.example.reactive.repository;

import com.example.reactive.repository.r2dbc.Contract;
import com.example.reactive.repository.r2dbc.ReactiveContractRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ContractDataAdapter {

  private final ReactiveContractRepository reactiveContractRepository;

  public ContractDataAdapter(ReactiveContractRepository reactiveContractRepository) {
    this.reactiveContractRepository = reactiveContractRepository;
  }

  public Mono<Contract> saveContract(Contract contract) {
    return reactiveContractRepository.save(contract);
  }
}
