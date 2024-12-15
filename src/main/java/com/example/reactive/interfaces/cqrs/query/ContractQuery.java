package com.example.reactive.interfaces.cqrs.query;

import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import reactor.core.publisher.Flux;

public interface ContractQuery {

  Flux<ContractResponseDto> getAllContractsWithPagination(int number, int size);

}
