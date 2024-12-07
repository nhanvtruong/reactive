package com.example.reactive.controller;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ContractRequestDto(@NotNull String description, @NotNull String contractKey) {

}
