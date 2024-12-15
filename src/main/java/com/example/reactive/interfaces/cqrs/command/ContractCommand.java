package com.example.reactive.interfaces.cqrs.command;

import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import com.example.reactive.interfaces.dtos.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.dtos.rq.UpdateContractRequestDto;
import reactor.core.publisher.Mono;

public interface ContractCommand {

  Mono<ContractResponseDto> saveContract(CreateContractRequestDto createContractRequestDto);

  Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto);

}
