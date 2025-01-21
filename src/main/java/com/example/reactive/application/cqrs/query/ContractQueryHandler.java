package com.example.reactive.application.cqrs.query;

import com.example.reactive.application.mapper.ContractMapper;
import com.example.reactive.application.port.ContractDataAdapter;
import com.example.reactive.application.port.UserDataAdapter;
import com.example.reactive.interfaces.res.ContractResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class ContractQueryHandler implements ContractQuery {

  private final ContractDataAdapter contractDataAdapter;

  private final UserDataAdapter userDataAdapter;

  @Override
  public Flux<ContractResponseDto> getAllContractsWithPagination(int number, int size) {
    return userDataAdapter.getUserServiceHeartbeat()
        .doOnSuccess(m -> log.info("userServiceHeartbeat: {}", m))
        .thenMany(contractDataAdapter.getAllContractWithPagination(
            Pageable.ofSize(size).withPage(number - 1)));
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
