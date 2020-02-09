package io.rx;

import io.rx.domain.User;
import io.rx.repository.ReactiveRepository;
import io.rx.repository.ReactiveUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class Part06RequestTest {

  Part06Request workshop = new Part06Request();
  ReactiveRepository<User> repository = new ReactiveUserRepository();

  @Test
  void requestAll() {
    Flux<User> flux = repository.findAll();
    StepVerifier verifier = workshop.requestAllExpectFour(flux);
    verifier.verify();
  }

  @Test
  void requestOneByOne() {
    Flux<User> flux = repository.findAll();
    StepVerifier verifier = workshop.requestOneExpectSkylerThenRequestOneExpectJesse(flux);
    verifier.verify();
  }

  @Test
  void experimentWithLog() {
    Flux<User> flux = workshop.fluxWithLog();
    StepVerifier.create(flux, 0)
        .thenRequest(1)
        .expectNextMatches(u -> true)
        .thenRequest(1)
        .expectNextMatches(u -> true)
        .thenRequest(2)
        .expectNextMatches(u -> true)
        .expectNextMatches(u -> true)
        .verifyComplete();
  }

  @Test
  void experimentWithDoOn() {
    Flux<User> flux = workshop.fluxWithDoOnPrintln();
    StepVerifier.create(flux)
        .expectNextCount(4)
        .verifyComplete();
  }
}
