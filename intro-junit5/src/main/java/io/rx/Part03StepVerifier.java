package io.rx;

import io.rx.domain.User;
import java.time.Duration;
import java.util.function.Supplier;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import org.assertj.core.api.Assertions;

public class Part03StepVerifier {

  public void expectFooBarComplete(Flux<String> flux) {
    StepVerifier.create(flux)
        .expectNext("foo", "bar")
        .verifyComplete();
  }

  public void expectFooBarError(Flux<String> concatWith) {
    StepVerifier.create(concatWith)
        .expectNext("foo", "bar")
        .verifyError(RuntimeException.class);
  }

  public void expectFooBarTimes2Complete(Flux<String> concatWith) {
    StepVerifier.create(concatWith)
        .expectNext("foo", "bar")
        .expectNext("hoge", "moge")
        .verifyComplete();
  }

  public void expectSkylerJesseComplete(Flux<User> flux) {
    StepVerifier.create(flux)
        .expectNextMatches(user -> user.getName().equals("swhite"))
        .assertNext(
            user -> Assertions.assertThat(user.getName()).isEqualToIgnoringCase("jpinkman"))
        .verifyComplete();
  }

  public void expect10Elements(Flux<Long> flux) {
    StepVerifier.create(flux)
        .expectNextCount(10)
        .verifyComplete();
  }


  public void expect3600Elements(Supplier<Flux<Long>> supplier) {
    StepVerifier.withVirtualTime(supplier)
        // 1時間をスキップする
        .thenAwait(Duration.ofHours(1))
        .expectNextCount(3600)
        .verifyComplete();
  }
}