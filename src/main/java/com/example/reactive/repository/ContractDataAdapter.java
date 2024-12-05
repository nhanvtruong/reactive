package com.example.reactive.repository;

import com.example.reactive.repository.r2dbc.Contract;
import com.example.reactive.repository.r2dbc.ReactiveContractRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class ContractDataAdapter {

  private final ReactiveContractRepository reactiveContractRepository;

  public ContractDataAdapter(ReactiveContractRepository reactiveContractRepository) {
    this.reactiveContractRepository = reactiveContractRepository;
  }

  @Transactional
  public Mono<Contract> saveContract(Contract contract) {
    return reactiveContractRepository.save(contract);
  }

  public Mono<Contract> getContract(Long contractId) {
    return reactiveContractRepository.findById(contractId);
  }

  @Transactional
  public Mono<Contract> updateContract(Long id , String status) {
    return reactiveContractRepository.updateContractStatus(id, status);
  }
}
