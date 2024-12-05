package com.example.reactive.controller;

import lombok.Builder;

@Builder
public record ContractRequestDto(String description, String status , String contractKey) {

}
