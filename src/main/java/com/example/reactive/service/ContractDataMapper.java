package com.example.reactive.service;

import com.example.reactive.controller.ContractResponseDto;
import com.example.reactive.repository.r2dbc.Contract;
import java.util.List;
import java.util.stream.Collectors;
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

  public static List<ContractResponseDto> toResponseDtoList(List<Contract> contracts) {
    return contracts.stream().map(ContractDataMapper::toResponseDto).collect(Collectors.toList());
  }

}
