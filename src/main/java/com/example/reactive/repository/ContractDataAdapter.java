package com.example.reactive.repository;

import com.example.reactive.exceptions.DataConflictException;
import com.example.reactive.repository.r2dbc.Contract;
import com.example.reactive.repository.r2dbc.ReactiveContractRepository;
import java.time.Duration;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContractDataAdapter {

  private final ReactiveContractRepository reactiveContractRepository;

  public ContractDataAdapter(ReactiveContractRepository reactiveContractRepository) {
    this.reactiveContractRepository = reactiveContractRepository;
  }

  @Transactional
  public Mono<Contract> saveContract(Contract contract) {
    return reactiveContractRepository.save(contract)
        .onErrorResume(DataIntegrityViolationException.class,
            ex -> Mono.error(new DataConflictException("Data conflict")));
  }

  public Mono<Contract> getContract(Long contractId) {
    return reactiveContractRepository.findById(contractId)
        .log();
  }

  @Transactional
  public Mono<Contract> updateContract(Long id , String status) {
    return reactiveContractRepository.updateContractStatus(id, status);
  }

  public Flux<List<Contract>> getAllContracts(int batchSize , long timeInMilliseconds) {
    return reactiveContractRepository.findAll()
        .bufferTimeout(batchSize,Duration.ofMillis(timeInMilliseconds));
  }
}
