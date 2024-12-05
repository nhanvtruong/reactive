package com.example.reactive.service;

import com.example.reactive.controller.ContractResponseDto;
import com.example.reactive.repository.r2dbc.Contract;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractDataMapper {

  public static ContractResponseDto toResponseDto(Contract contract) {
    return ContractResponseDto.builder()
        .id(contract.getId())
        .description(contract.getDescription())
        .status(contract.getStatus())
        .contractKey(contract.getContractKey())
        .build();
  }

}
