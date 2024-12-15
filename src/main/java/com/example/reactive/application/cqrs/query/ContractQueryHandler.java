package com.example.reactive.application.cqrs.query;

import com.example.reactive.application.port.ContractDataAdapter;
import com.example.reactive.interfaces.cqrs.query.ContractQuery;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ContractQueryHandler implements ContractQuery {

  private final ContractDataAdapter contractDataAdapter;

  @Override
  public Flux<ContractResponseDto> getAllContractsWithPagination(int number, int size) {
    Pageable pageable = Pageable.ofSize(size).withPage(number - 1);
    return contractDataAdapter.getAllContractWithPagination(pageable);
  }
}
