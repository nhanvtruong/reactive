package com.example.reactive.application.cqrs.command;

import com.example.reactive.application.port.ContractDataAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractCommandHandler {

  private final ContractDataAdapter contractDataAdapter;

}
