package com.example.reactive.controller.dtos;

import com.example.reactive.service.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateContractRequestDto(@NotNull Long contractId, Status status) {

}
