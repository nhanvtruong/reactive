package com.example.reactive.controller.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateContractRequestDto(@NotNull String description, @NotNull String contractKey) {

}
