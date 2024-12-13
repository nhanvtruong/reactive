package com.example.reactive.repository;

import com.example.reactive.controller.dtos.ContractResponseDto;
import com.example.reactive.exceptions.UniqueConstraintException;
import com.example.reactive.repository.r2dbc.Contract;
import com.example.reactive.repository.r2dbc.ReactiveContractRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ContractDataAdapter {

  private final ReactiveContractRepository reactiveContractRepository;

  @Transactional
  public Mono<Contract> saveContract(Contract contract) {
    return reactiveContractRepository.save(contract)
        .onErrorResume(DataIntegrityViolationException.class,
            ex -> Mono.error(new UniqueConstraintException(ex.getMessage())));
  }

  public Mono<Contract> detectContractStatusChange(Long contractId) {
    return reactiveContractRepository.findById(contractId)
        .log();
  }


  @Transactional
  public Mono<ContractResponseDto> updateContract(Long id, String status) {
    return reactiveContractRepository.updateContractStatus(id, status, ContractResponseDto.class);
  }

  public Flux<Contract> getAllContracts(int batchSize, long delay) {
    return reactiveContractRepository.findAll()
        .bufferTimeout(batchSize, Duration.ofMillis(delay))
        .flatMap(Flux::fromIterable);
  }

  public Flux<ContractResponseDto> getAllContractWithPagination(Pageable pageable) {
    return reactiveContractRepository.findAllByOrderById(pageable, ContractResponseDto.class);
  }


}
