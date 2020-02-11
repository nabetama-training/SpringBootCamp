package io.rx;

import io.rx.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class Part07ErrorsTest {

  Part07Errors workshop = new Part07Errors();

  @Test
  void monoWithValueInsteadOfError() {
    Mono<User> mono = workshop.betterCallSaulForBogusMono(Mono.error(new IllegalStateException()));
    StepVerifier.create(mono).expectNext(User.SAUL).verifyComplete();

    mono = workshop.betterCallSaulForBogusMono(Mono.just(User.SKYLER));
    StepVerifier.create(mono).expectNext(User.SKYLER).verifyComplete();
  }

  @Test
  void fluxWithValueInsteadOfError() {
    Flux<User> flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.error(new IllegalStateException()));
    StepVerifier.create(flux)
        .expectNext(User.SAUL, User.JESSE)
        .verifyComplete();

    flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.just(User.SKYLER, User.WALTER));
    StepVerifier.create(flux)
        .expectNext(User.SKYLER, User.WALTER)
        .verifyComplete();
  }

  @Test
  public void handleCheckedExceptions() {
    Flux<User> flux = workshop.capitalizeMany(Flux.just(User.SAUL, User.JESSE));
    StepVerifier.create(flux)
        .verifyError(Part07Errors.CapitalizeError.class);
  }
}
