package io.rx;

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


}
