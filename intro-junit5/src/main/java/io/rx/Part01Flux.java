package io.rx;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class Part01Flux {

  Flux<String> emptyFlux() {
    return Flux.empty();
  }

  Flux<String> fooBarFluxFromValues() {
    return Flux.just("foo", "bar");
  }

  public Flux<String> fooBarFluxFromList() {
    return Flux.fromArray(new String[]{"foo", "bar"});
  }


  public Flux<String> errorFlux() {
    return Flux.error(new IllegalStateException());
  }

  public Flux<Long> counter() {
    return Flux.interval(Duration.ofMillis(100))
        .take(10);
  }
}
