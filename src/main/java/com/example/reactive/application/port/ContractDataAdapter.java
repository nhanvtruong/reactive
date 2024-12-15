package com.example.reactive.application.port;

import com.example.reactive.infrastructure.model.ContractModel;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractDataAdapter {

  Flux<ContractResponseDto> getAllContractWithPagination(Pageable pageable);

  Mono<ContractModel> detectContractStatusChange(Long id);

  Mono<ContractModel> saveContract(ContractModel contractModel);

  Flux<ContractModel> getAllContracts(int batchSize, long delay);

  Mono<ContractResponseDto> updateContract(Long id, String status);

}
