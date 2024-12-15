package com.example.reactive.interfaces.dtos.rq;

import com.example.reactive.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateContractRequestDto(@NotNull Long contractId, Status status) {

}
