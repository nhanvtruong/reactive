package com.example.reactive.repository;

import com.example.reactive.repository.r2dbc.Contract;
import com.example.reactive.service.ContractDataSubscriber;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractDataPublisher implements Publisher<Contract> {

  private final ContractDataSubscriber contractDataSubscriber;

  @Override
  public void subscribe(Subscriber<? super Contract> contractDataSubscriber) {
  }
}
