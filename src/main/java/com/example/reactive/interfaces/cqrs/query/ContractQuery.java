package com.example.reactive.interfaces.cqrs.query;

import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractQuery {

  Flux<ContractResponseDto> getAllContractsWithPagination(int number, int size);

  Mono<ContractResponseDto> subscribeToContractStatusChange(Long id);

  Flux<ContractResponseDto> getAllContracts(int batchSize, long delay);

}
