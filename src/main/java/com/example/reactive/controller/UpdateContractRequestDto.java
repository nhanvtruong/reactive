package com.example.reactive.controller;

import com.example.reactive.service.Status;
import lombok.Builder;

@Builder
public record UpdateContractRequestDto (Long contractId,Status status) {

}
