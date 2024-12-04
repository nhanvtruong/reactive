package com.example.reactive.controller;

import com.example.reactive.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contracts")
public class ContractController {

  private final ContractService contractService;

  public ContractController(ContractService contractService) {
    this.contractService = contractService;
  }

  @PostMapping
  public ResponseEntity<Mono<ContractResponseDto>> saveContract( @RequestBody ContractRequestDto contractRequestDto) {
    return new ResponseEntity<>(contractService.saveContract(contractRequestDto), HttpStatus.CREATED);
  }
}
