package com.example.reactive.service;

import com.example.reactive.controller.ContractRequestDto;
import com.example.reactive.controller.ContractResponseDto;
import com.example.reactive.controller.UpdateContractRequestDto;
import com.example.reactive.repository.ContractDataAdapter;
import com.example.reactive.repository.r2dbc.Contract;
import java.time.Duration;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

@Service
@RequiredArgsConstructor
public class ContractService {

  private final ContractDataAdapter contractDataAdapter;

  public Mono<ContractResponseDto> saveContract(ContractRequestDto contractRequestDto) {
    Contract contract = Contract.builder()
        .description(contractRequestDto.description())
        .status(Status.CREATED.name())
        .contractKey(contractRequestDto.contractKey())
        .build();
    return contractDataAdapter.saveContract(contract).map(
        c -> new ContractResponseDto(c.getId(), c.getDescription(), c.getStatus(),
            c.getContractKey())
    );
  }

  public Mono<ContractResponseDto> listenToContractStatus(Long id) {
    return Flux.interval(Duration.ofSeconds(2))
        .flatMap(tick -> contractDataAdapter.getContract(id)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))))
        .filter(c -> !Objects.equals(c.getStatus(), Status.CREATED.name()))
        .next()
        .map(contract -> new ContractResponseDto(contract.getId(), contract.getDescription(),
            contract.getStatus(), contract.getContractKey()))
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto) {
    return contractDataAdapter.updateContract(requestDto.contractId(), requestDto.status().name())
        .map(c -> ContractResponseDto.builder()
            .id(c.getId())
            .description(c.getDescription())
            .status(c.getStatus())
            .contractKey(c.getContractKey())
            .build());
  }


}
