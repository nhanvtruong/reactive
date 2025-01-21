package com.example.reactive.application.services;

import com.example.reactive.application.cqrs.command.ContractCommand;
import com.example.reactive.application.cqrs.query.ContractQuery;
import com.example.reactive.interfaces.res.ContractResponseDto;
import com.example.reactive.interfaces.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.rq.UpdateContractRequestDto;
import com.example.reactive.interfaces.services.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

  private final ContractCommand contractCommand;

  private final ContractQuery contractQuery;

  @Override
  public Mono<ContractResponseDto> saveContract(CreateContractRequestDto createContractRequestDto) {
    return contractCommand.saveContract(createContractRequestDto);
  }

  @Override
  public Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto) {
    return contractCommand.updateContract(requestDto);
  }

  @Override
  public Flux<ContractResponseDto> getAllContractsWithPagination(int number, int size) {
    return contractQuery.getAllContractsWithPagination(number, size);
  }

  @Override
  public Mono<ContractResponseDto> subscribeToContractStatusChange(Long id) {
    return contractQuery.subscribeToContractStatusChange(id);
  }

  /**
   * Get all contracts but at a steady rate to avoid overwhelm database and client
   * @param batchSize : number of record at a time
   * @param delay : unit of time in millisecond to get a batch
   * @return List of all contract
   */
  @Override
  public Flux<ContractResponseDto> getAllContracts(int batchSize, long delay) {
    return contractQuery.getAllContracts(batchSize, delay);
  }



}
