package com.example.reactive.service;

import com.example.reactive.controller.ContractRequestDto;
import com.example.reactive.controller.ContractResponseDto;
import com.example.reactive.repository.r2dbc.Contract;
import com.example.reactive.repository.ContractDataAdapter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ContractService {

  private final ContractDataAdapter contractDataAdapter;

  public ContractService(ContractDataAdapter contractDataAdapter) {
    this.contractDataAdapter = contractDataAdapter;
  }

  public Mono<ContractResponseDto> saveContract(ContractRequestDto contractRequestDto) {
    Contract contract = new Contract();
    contract.setDescription(contractRequestDto.description());
    contract.setStatus(Status.CREATED.name());
    return contractDataAdapter.saveContract(contract).map(
        c -> new ContractResponseDto(c.getId(), c.getDescription(), c.getStatus())
    );
  }

}
