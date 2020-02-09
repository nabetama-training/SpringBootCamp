package io.rx;

import reactor.core.publisher.Mono;

public class Part02Mono {


  public Mono<String> emptyMono() {
    return Mono.empty();
  }

  public Mono<String> monoWithNosignal() {
    return Mono.never();
  }

  public Mono<String> fooMono() {
    return Mono.just("foo");
  }

  public Mono<String> errorMono() {
    return Mono.error(new IllegalStateException());
  }
}
