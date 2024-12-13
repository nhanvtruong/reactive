package com.example.reactive.repository.r2dbc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class R2DbcCriteriaQueryRepository {

  private final DatabaseClient databaseClient;
  private final R2dbcEntityTemplate r2dbcEntityTemplate;

  public static class ContractQueryBuilder {
    private final Criteria criteria = Criteria.empty();

    public ContractQueryBuilder withDescription(String description) {
      criteria.and("description").is(description);
      return this;
    }

    public ContractQueryBuilder withStatus(String status) {
      criteria.and("status").is(status);
      return this;
    }

    public Criteria build() {
      return this.criteria;
    }
  }
}
