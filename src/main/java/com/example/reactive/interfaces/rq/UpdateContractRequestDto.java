package com.example.reactive.interfaces.rq;

import com.example.reactive.domain.vo.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateContractRequestDto(@NotNull Long id, Status status) {

}
