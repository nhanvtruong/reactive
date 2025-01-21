package com.example.reactive.application.cqrs.command;

import com.example.reactive.interfaces.res.ContractResponseDto;
import com.example.reactive.interfaces.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.rq.UpdateContractRequestDto;
import reactor.core.publisher.Mono;

public interface ContractCommand {

  Mono<ContractResponseDto> saveContract(CreateContractRequestDto createContractRequestDto);

  Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto);

}
