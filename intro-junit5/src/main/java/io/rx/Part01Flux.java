package io.rx;

import reactor.core.publisher.Flux;

public class Part01Flux {

  Flux<String> emptyFlux() {
    return Flux.empty();
  }
}
