package io.rx;

import io.rx.domain.User;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@TestInstance(Lifecycle.PER_CLASS)
public class Part03StepVerifierTest {

  Part03StepVerifier workshop = new Part03StepVerifier();

  @Test
  void expectElementsThenComplete() {
    workshop.expectFooBarComplete(Flux.just("foo", "bar"));
  }

  @Test
  void expect2ElementsThenError() {
    workshop
        .expectFooBarError(Flux.just("foo", "bar").concatWith(Mono.error(new RuntimeException())));
  }

  @Test
  void expect2ElementsAfter2ElementsThenComplete() {
    workshop
        .expectFooBarTimes2Complete(Flux.just("foo", "bar").concatWith(Flux.just("hoge", "moge")));
  }

  @Test
  void expectElementsWithThenComplete() {
    workshop.expectSkylerJesseComplete(
        Flux.just(new User("swhite", null, null), new User("jpinkman", null, null))
    );
  }

  @Test
  void count() {
    workshop.expect10Elements(
        // interval(): 0から始めて指定したDuration秒のインターバルを取り続ける
        Flux.interval(Duration.ofSeconds(1)).doOnNext(System.out::println).take(10));
  }

  @Test
  void countWithVirtualTime() {
    workshop.expect3600Elements(() -> Flux.interval(Duration.ofSeconds(1)).take(3600));
  }
}
