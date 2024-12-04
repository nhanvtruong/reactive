package com.example.reactive.controller;

import lombok.Builder;

@Builder
public record ContractResponseDto(Long id,String description , String status) {

}
