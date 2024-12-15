package com.example.reactive.application.cqrs.query;

import com.example.reactive.application.mapper.ContractMapper;
import com.example.reactive.application.port.ContractDataAdapter;
import com.example.reactive.interfaces.cqrs.query.ContractQuery;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ContractQueryHandler implements ContractQuery {

  private final ContractDataAdapter contractDataAdapter;

  @Override
  public Flux<ContractResponseDto> getAllContractsWithPagination(int number, int size) {
    Pageable pageable = Pageable.ofSize(size).withPage(number - 1);
    return contractDataAdapter.getAllContractWithPagination(pageable);
  }

  @Override
  public Mono<ContractResponseDto> subscribeToContractStatusChange(Long id) {
    return contractDataAdapter.detectContractStatusChange(id)
        .map(ContractMapper.INSTANCE::toResponseDto);
  }

  @Override
  public Flux<ContractResponseDto> getAllContracts(int batchSize, long delay) {
    return contractDataAdapter.getAllContracts(batchSize, delay)
        .map(ContractMapper.INSTANCE::toResponseDto);
  }
}
