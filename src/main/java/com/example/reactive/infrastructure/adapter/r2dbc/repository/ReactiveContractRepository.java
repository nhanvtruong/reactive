package com.example.reactive.infrastructure.adapter.r2dbc.repository;


import com.example.reactive.infrastructure.adapter.r2dbc.model.ContractModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface ReactiveContractRepository extends R2dbcRepository<ContractModel, Long> {

  @Query("UPDATE contract SET status = :status WHERE id = :id RETURNING *")
  <T> Mono<T> updateContractStatus(@Param("id") Long id, @Param("status") String status,
      Class<T> clazz);

  <T> Flux<T> findAllByOrderById(Pageable pageable, Class<T> clazz);

}
