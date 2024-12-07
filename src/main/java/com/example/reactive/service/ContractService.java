package com.example.reactive.service;

import com.example.reactive.controller.ContractRequestDto;
import com.example.reactive.controller.ContractResponseDto;
import com.example.reactive.controller.UpdateContractRequestDto;
import com.example.reactive.repository.ContractDataAdapter;
import com.example.reactive.repository.r2dbc.Contract;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ContractService {

  private final ContractDataAdapter contractDataAdapter;

  public Mono<Contract> saveContract(ContractRequestDto contractRequestDto) {
    Contract contract = Contract.builder()
        .description(contractRequestDto.description())
        .status(Status.CREATED.name())
        .contractKey(contractRequestDto.contractKey())
        .build();
    return contractDataAdapter.saveContract(contract);
  }

  public Mono<ContractResponseDto> listenToContractStatus(Long id) {
    return Flux.interval(Duration.ofSeconds(1))
        .flatMap(tick -> contractDataAdapter.getContract(id))
        .filter(contract -> !Objects.equals(contract.getStatus(), Status.CREATED.name()))
        .timeout(Duration.ofSeconds(60))
        .next() // Take the first contract that meets the condition
        .map(ContractDataMapper::toResponseDto)
        .subscribeOn(Schedulers.boundedElastic());
  }


  public Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto) {
    return contractDataAdapter.updateContract(requestDto.contractId(), requestDto.status().name())
        .map(ContractDataMapper::toResponseDto);
  }

  public Flux<Contract> getAllContracts(int batchSize , long delay) {
    return contractDataAdapter.getAllContracts(batchSize, delay);
  }


}
