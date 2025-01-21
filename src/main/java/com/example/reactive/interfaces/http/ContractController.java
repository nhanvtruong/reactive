package com.example.reactive.interfaces.http;

import com.example.reactive.interfaces.res.ContractResponseDto;
import com.example.reactive.interfaces.rq.CreateContractRequestDto;
import com.example.reactive.interfaces.rq.UpdateContractRequestDto;
import com.example.reactive.interfaces.services.ContractService;
import jakarta.validation.constraints.Min;
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

  @GetMapping(value = "/page")
  public ResponseEntity<Flux<ContractResponseDto>> getAllContractsWithPagination(
      @RequestParam @Min(1) final int number,
      @RequestParam @Min(1) final int size) {
    return new ResponseEntity<>(contractService.getAllContractsWithPagination(number, size),
        HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Flux<ContractResponseDto>> getAllContractsAtSteadyRate(
      @RequestParam @Min(1) final int batchSize,
      @RequestParam @Min(1) final long delay) {
    return new ResponseEntity<>(contractService.getAllContracts(batchSize, delay), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Mono<ContractResponseDto>> saveContract(
      @RequestBody CreateContractRequestDto createContractRequestDto) {
    return new ResponseEntity<>(contractService.saveContract(createContractRequestDto),
        HttpStatus.CREATED);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Mono<ContractResponseDto>> subscribeToContractStatusChange(
      @PathVariable final Long id) {
    return new ResponseEntity<>(contractService.subscribeToContractStatusChange(id), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<Mono<ContractResponseDto>> updateContract(
      @RequestBody UpdateContractRequestDto contractRequestDto) {
    return new ResponseEntity<>(contractService.updateContract(contractRequestDto), HttpStatus.OK);
  }
}
