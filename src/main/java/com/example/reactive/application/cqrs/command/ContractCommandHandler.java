package com.example.reactive.application.cqrs.command;

import com.example.reactive.application.mapper.ContractMapper;
import com.example.reactive.application.port.ContractDataAdapter;
import com.example.reactive.domain.entity.Contract;
import com.example.reactive.domain.events.ContractCreatedEvent;
import com.example.reactive.infrastructure.adapter.r2dbc.model.ContractModel;
import com.example.reactive.interfaces.res.ContractResponseDto;
import com.example.reactive.interfaces.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.rq.UpdateContractRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ContractCommandHandler implements ContractCommand {

  private final ContractDataAdapter contractDataAdapter;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  @Transactional
  public Mono<ContractResponseDto> saveContract(CreateContractRequestDto createContractRequestDto) {
    Contract contract = ContractMapper.INSTANCE.toContractEntity(createContractRequestDto);
    contract.createContract();
    ContractModel contractModel = ContractMapper.INSTANCE.toContractModel(contract);
    return contractDataAdapter.saveContract(contractModel)
        .doOnSuccess(savedContract -> {
          savedContract.onContractCreatedEvent();
          eventPublisher.publishEvent(new ContractCreatedEvent(savedContract));
        })
        .map(ContractMapper.INSTANCE::toResponseDto);
  }

  @Override
  public Mono<ContractResponseDto> updateContract(UpdateContractRequestDto requestDto) {
    Contract contract = ContractMapper.INSTANCE.toContractEntity(requestDto);
    contract.updateContractStatus(requestDto.status());
    return contractDataAdapter.updateContract(contract.getId(), contract.getStatus());
  }

}
