package io.rx;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class Part02MonoTest {

  Part02Mono workshop = new Part02Mono();

  @Test
  void empty() {
    Mono<String> mono = workshop.emptyMono();
    StepVerifier.create(mono)
        .verifyComplete();
  }

  @Test
  void noSignal() {
    Mono<String> mono = workshop.monoWithNosignal();
    StepVerifier.create(mono)
        .expectSubscription()
        .expectTimeout(Duration.ofSeconds(1))
        .verify();
  }

  @Test
  void fromValue() {
    Mono<String> mono = workshop.fooMono();
    StepVerifier.create(mono)
        .expectNext("foo")
        .verifyComplete();
  }

  @Test
  void error(){
    Mono<String>mono = workshop.errorMono();
    StepVerifier.create(mono)
        .verifyError(IllegalStateException.class);
  }
}
