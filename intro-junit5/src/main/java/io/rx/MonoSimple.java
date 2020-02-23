package io.rx;

import reactor.core.publisher.Mono;

public class MonoSimple {


  public Mono<String> just() {
    return Mono.just("Hello, world");
  }

  public Mono<String> fromSupplier() {
    return Mono.fromSupplier(() -> "Hello, world");
  }

  public Mono<String> MonoMap(String name) {
    return Mono.just(name).map(n -> "Hello, " + n);
  }
}
