package com.example.reactive.controller;

import com.example.reactive.repository.r2dbc.Contract;
import com.example.reactive.service.ContractService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {

  private final ContractService contractService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Flux<Contract>> getAllContractsAtSteadyRate(
      @RequestParam int batchSize, @RequestParam long delay) {
    return new ResponseEntity<>(contractService.getAllContracts(batchSize, delay), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Mono<Contract>> saveContract(
      @RequestBody ContractRequestDto contractRequestDto) {
    return new ResponseEntity<>(contractService.saveContract(contractRequestDto),
        HttpStatus.CREATED);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Mono<ContractResponseDto>> listenToContractStatus(@PathVariable Long id) {
    return new ResponseEntity<>(contractService.listenToContractStatus(id), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<Mono<ContractResponseDto>> updateContract(
      @RequestBody UpdateContractRequestDto contractRequestDto) {
    return new ResponseEntity<>(contractService.updateContract(contractRequestDto), HttpStatus.OK);
  }
}
