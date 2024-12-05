package com.example.reactive.repository.r2dbc;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ReactiveContractRepository extends R2dbcRepository<Contract, Long> {

}
