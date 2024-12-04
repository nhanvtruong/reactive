package com.example.reactive.repository;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "contract")
public class Contract {

  @Id
  private Long id;

  private String description;

  private String status;

  public Contract(Long id, String description, String status) {
    this.id = id;
    this.description = description;
    this.status = status;
  }

  public Contract() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contract contract = (Contract) o;
    return Objects.equals(id, contract.id) && Objects.equals(description,
        contract.description) && Objects.equals(status, contract.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, status);
  }
}
