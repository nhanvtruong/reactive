package com.example.reactive.interfaces.res;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ContractResponseDto(Long id, String description, String status, String contractKey,
                                  LocalDateTime createdAt, LocalDateTime updatedAt) {

}
