package com.example.reactive.infrastructure.adapter;

import com.example.reactive.application.port.ContractDataAdapter;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import com.example.reactive.application.exceptions.UniqueConstraintException;
import com.example.reactive.infrastructure.model.ContractModel;
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
public class ContractDataAdapterImpl implements ContractDataAdapter {

  private final ReactiveContractRepository reactiveContractRepository;

  @Transactional
  public Mono<ContractModel> saveContract(ContractModel contractModel) {
    return reactiveContractRepository.save(contractModel)
        .onErrorResume(DataIntegrityViolationException.class,
            ex -> Mono.error(new UniqueConstraintException(ex.getMessage())));
  }

  public Mono<ContractModel> detectContractStatusChange(Long contractId) {
    return reactiveContractRepository.findById(contractId)
        .log();
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
