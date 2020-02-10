package io.rx;

import io.rx.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
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
}
