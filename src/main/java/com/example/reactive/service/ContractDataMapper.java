package com.example.reactive.service;

import com.example.reactive.controller.dtos.ContractResponseDto;
import com.example.reactive.controller.dtos.CreateContractRequestDto;
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
        .createdAt(contract.getCreatedAt())
        .updatedAt(contract.getUpdatedAt())
        .build();
  }

  public static Contract toContract(CreateContractRequestDto requestDto) {
    return Contract.builder()
        .description(requestDto.description())
        .contractKey(requestDto.contractKey())
        .build();
  }

}
