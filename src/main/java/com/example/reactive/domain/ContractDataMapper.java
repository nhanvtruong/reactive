package com.example.reactive.domain;

import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import com.example.reactive.interfaces.dtos.rq.CreateContractRequestDto;
import com.example.reactive.infrastructure.model.ContractModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractDataMapper {

  public static ContractResponseDto toResponseDto(ContractModel contractModel) {
    return ContractResponseDto.builder()
        .id(contractModel.getId())
        .description(contractModel.getDescription())
        .status(contractModel.getStatus())
        .contractKey(contractModel.getContractKey())
        .createdAt(contractModel.getCreatedAt())
        .updatedAt(contractModel.getUpdatedAt())
        .build();
  }

  public static ContractModel toContract(CreateContractRequestDto requestDto) {
    return ContractModel.builder()
        .description(requestDto.description())
        .contractKey(requestDto.contractKey())
        .build();
  }

}
