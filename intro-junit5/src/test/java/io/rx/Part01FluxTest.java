package io.rx;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class Part01FluxTest {

  Part01Flux workshop = new Part01Flux();

  @Test
  void empty() {
    Flux<String> flux = workshop.emptyFlux();

    StepVerifier.create(flux)
        .verifyComplete();
  }

  
}
