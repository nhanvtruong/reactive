package com.example.reactive.service;

import com.example.reactive.repository.r2dbc.Contract;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;

@Service
public class ContractDataSubscriber implements Subscriber<Contract> {

  @Override
  public void onSubscribe(Subscription subscription) {

  }

  @Override
  public void onNext(Contract contract) {

  }

  @Override
  public void onError(Throwable throwable) {

  }

  @Override
  public void onComplete() {

  }
}
