package com.example.reactive.infrastructure.adapter.r2dbc.repository;

import com.example.reactive.application.exceptions.UniqueConstraintException;
import com.example.reactive.application.port.ContractDataAdapter;
import com.example.reactive.domain.vo.Status;
import com.example.reactive.infrastructure.adapter.r2dbc.model.ContractModel;
import com.example.reactive.infrastructure.config.properties.ApplicationProperties;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import java.time.Duration;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ContractDataAdapterImpl implements ContractDataAdapter {

  private final ReactiveContractRepository reactiveContractRepository;

  private final ApplicationProperties applicationProperties;

  @Transactional
  public Mono<ContractModel> saveContract(ContractModel contractModel) {
    return reactiveContractRepository.save(contractModel)
        .onErrorResume(DataIntegrityViolationException.class,
            ex -> Mono.error(new UniqueConstraintException(ex.getMessage())));
  }

  public Mono<ContractModel> detectContractStatusChange(Long contractId) {
    return Flux.interval(Duration.ofSeconds(applicationProperties.getContract().delay()))
        .flatMap(tick -> reactiveContractRepository.findById(contractId))
        .filter(contractModel -> !Objects.equals(contractModel.getStatus(), Status.CREATED.name()))
        .timeout(Duration.ofSeconds(applicationProperties.getContract().timeout()),
            reactiveContractRepository.findById(contractId))
        .next()
        .subscribeOn(Schedulers.boundedElastic());
  }


  @Transactional
  public Mono<ContractResponseDto> updateContract(Long id, String status) {
    return reactiveContractRepository.updateContractStatus(id, status, ContractResponseDto.class);
  }

  public Flux<ContractModel> getAllContracts(int batchSize, long delay) {
    return reactiveContractRepository.findAll()
        .bufferTimeout(batchSize, Duration.ofMillis(delay))
        .flatMap(Flux::fromIterable);
  }

  public Flux<ContractResponseDto> getAllContractWithPagination(Pageable pageable) {
    return reactiveContractRepository.findAllByOrderById(pageable, ContractResponseDto.class);
  }


}
