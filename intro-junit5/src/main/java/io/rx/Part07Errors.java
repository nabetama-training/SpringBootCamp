package io.rx;

import io.rx.domain.User;
import reactor.core.publisher.Mono;

public class Part07Errors {


  public Mono<User> betterCallSaulForBogusMono(Mono<User> error) {
    return error.onErrorResume(e -> Mono.just(User.SAUL));
  }
}
