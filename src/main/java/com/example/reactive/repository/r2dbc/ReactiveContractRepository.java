package com.example.reactive.repository.r2dbc;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface ReactiveContractRepository extends R2dbcRepository<Contract, Long> {

  @Query("UPDATE contract SET status = :status WHERE id = :id RETURNING *")
  <T> Mono<T> updateContractStatus(@Param("id") Long id, @Param("status") String status, Class<T> clazz);

}
