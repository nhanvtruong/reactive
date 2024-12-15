package com.example.reactive.application.port;

import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface ContractDataAdapter {

  Flux<ContractResponseDto> getAllContractWithPagination(Pageable pageable);

}
