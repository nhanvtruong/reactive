package com.example.reactive.application.cqrs.command;

import com.example.reactive.application.mapper.ContractMapper;
import com.example.reactive.application.port.ContractDataAdapter;
import com.example.reactive.domain.vo.Status;
import com.example.reactive.infrastructure.model.ContractModel;
import com.example.reactive.interfaces.cqrs.command.ContractCommand;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import com.example.reactive.interfaces.dtos.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.dtos.rq.UpdateContractRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ContractCommandHandler implements ContractCommand {

  private final ContractDataAdapter contractDataAdapter;

  @Override
  public Mono<ContractResponseDto> saveContract(CreateContractRequestDto createContractRequestDto) {
    ContractModel contractModel = ContractMapper.INSTANCE.toModel(createContractRequestDto);
    contractModel.setStatus(Status.CREATED.name());
    return contractDataAdapter.saveContract(contractModel)
        .map(ContractMapper.INSTANCE::toResponseDto);
  }

  @Override
  public Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto) {
    return contractDataAdapter.updateContract(requestDto.contractId(), requestDto.status().name());
  }

}
