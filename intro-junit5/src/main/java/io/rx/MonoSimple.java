package io.rx;

import reactor.core.publisher.Mono;

public class MonoSimple {


  public Mono<String> just() {
    return Mono.just("Hello, world");
  }
}
