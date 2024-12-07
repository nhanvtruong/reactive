package com.example.reactive.repository.r2dbc;

import java.time.Duration;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.scheduler.Schedulers;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class R2DbcHeatUp {

  private final DatabaseClient databaseClient;

  @Bean
  public ApplicationRunner heatUpDatabase() {
    Instant start = Instant.now();
    return args -> databaseClient.sql("SELECT 1")
        .fetch()
        .rowsUpdated()
        .subscribeOn(Schedulers.boundedElastic())
        .doOnTerminate(() -> {
              Instant end = Instant.now();
              long heatUpTime = Duration.between(start, end).toMillis();
              log.info("Heat up database completed after {} ms", heatUpTime);
            }
        ).subscribe();
  }

}
