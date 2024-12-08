package com.example.reactive.repository.r2dbc;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "contract")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Contract {

  @Id
  private Long id;

  private String description;

  private String status;

  private final String contractKey;

  @CreatedDate
  private final LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

}
