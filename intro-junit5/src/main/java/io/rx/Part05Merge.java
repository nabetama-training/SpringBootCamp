package io.rx;

import io.rx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part05Merge {


  public Flux<User> mergeFluxWithInterleave(Flux<User> all, Flux<User> all1) {
    return all.mergeWith(all1);
  }

  public Flux<User> mergeFluxWithNoInterleave(Flux<User> all, Flux<User> all1) {
    return all.concatWith(all1);
  }

  public Flux<User> createFluxFromMultipleMono(Mono<User> skylerMono, Mono<User> marieMono) {
    return Flux.concat(skylerMono, marieMono);
  }
}
