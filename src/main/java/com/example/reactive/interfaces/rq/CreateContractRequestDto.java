package com.example.reactive.interfaces.rq;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateContractRequestDto(@NotNull String description, @NotNull String contractKey) {

}
