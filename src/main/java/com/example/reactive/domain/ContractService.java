package com.example.reactive.domain;

import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import com.example.reactive.interfaces.dtos.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.dtos.rq.UpdateContractRequestDto;
import com.example.reactive.infrastructure.adapter.ContractDataAdapterImpl;
import com.example.reactive.infrastructure.model.ContractModel;
import java.time.Duration;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ContractService {

  private final ContractDataAdapterImpl contractDataAdapter;

  public Mono<ContractResponseDto> saveContract(CreateContractRequestDto createContractRequestDto) {
    ContractModel contractModel = ContractDataMapper.toContract(createContractRequestDto);
    contractModel.setStatus(Status.CREATED.name());
    return contractDataAdapter.saveContract(contractModel)
        .map(ContractDataMapper::toResponseDto);
  }

  public Mono<ContractResponseDto> detectContractStatusChange(Long id) {
    return Flux.interval(Duration.ofSeconds(1))
        .flatMap(tick -> contractDataAdapter.detectContractStatusChange(id))
        .filter(contractModel -> !Objects.equals(contractModel.getStatus(), Status.CREATED.name()))
        .timeout(Duration.ofSeconds(30),contractDataAdapter.detectContractStatusChange(id))
        .next()
        .map(ContractDataMapper::toResponseDto)
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto) {
    return contractDataAdapter.updateContract(requestDto.contractId(), requestDto.status().name());
  }

  public Flux<ContractResponseDto> getAllContracts(int batchSize, long delay) {
    return contractDataAdapter.getAllContracts(batchSize, delay)
        .map(ContractDataMapper::toResponseDto);
  }

  public Flux<ContractResponseDto> getAllContractsWithPagination(Pageable pageable) {
    return contractDataAdapter.getAllContractWithPagination(pageable);
  }

}