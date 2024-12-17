package com.example.reactive.application.event;

import com.example.reactive.domain.events.ContractEvent;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ContractEventListener {

  @EventListener
  public void handleContractCreatedEvent(ContractEvent event) {
    log.info("Sending event contract {} created to kafka at {}",
        event.getContractModel().getId(), LocalDateTime.now());
  }

}
