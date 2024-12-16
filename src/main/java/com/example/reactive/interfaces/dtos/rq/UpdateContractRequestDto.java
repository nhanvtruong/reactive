package com.example.reactive.interfaces.dtos.rq;

import com.example.reactive.domain.vo.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateContractRequestDto(@NotNull Long contractId, Status status) {

}
