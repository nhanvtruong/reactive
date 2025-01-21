package com.example.reactive.interfaces.services;

import com.example.reactive.interfaces.res.ContractResponseDto;
import com.example.reactive.interfaces.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.rq.UpdateContractRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractService {

  Mono<ContractResponseDto> saveContract(CreateContractRequestDto createContractRequestDto);

  Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto);

  Flux<ContractResponseDto> getAllContractsWithPagination(int number, int size);

  Mono<ContractResponseDto> subscribeToContractStatusChange(Long id);

  Flux<ContractResponseDto> getAllContracts(int batchSize, long delay);

}
