package com.example.reactive.application.mapper;

import com.example.reactive.domain.entity.Contract;
import com.example.reactive.infrastructure.adapter.r2dbc.model.ContractModel;
import com.example.reactive.interfaces.dtos.res.ContractResponseDto;
import com.example.reactive.interfaces.dtos.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.dtos.rq.UpdateContractRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper {

  ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

  ContractResponseDto toResponseDto(ContractModel contractModel);

  ContractModel toModel(CreateContractRequestDto requestDto);

  Contract toContractEntity(CreateContractRequestDto requestDto);

  Contract toContractEntity(UpdateContractRequestDto requestDto);

  ContractModel toContractModel(Contract contract);
}
