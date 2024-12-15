package com.example.reactive.application.mapper;

import com.example.reactive.infrastructure.model.ContractModel;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import com.example.reactive.interfaces.dtos.rq.CreateContractRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper {

  ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

  ContractResponseDto toResponseDto(ContractModel contractModel);
  
  ContractModel toModel(CreateContractRequestDto requestDto);

}
