package io.rx;

import io.rx.domain.User;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part07Errors {


  public Mono<User> betterCallSaulForBogusMono(Mono<User> error) {
    return error.onErrorResume(e -> Mono.just(User.SAUL));
  }

  public Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
    return flux.onErrorResume(e -> Flux.just(User.SAUL, User.JESSE));
  }

  public Flux<User> capitalizeMany(Flux<User> just) {
    return just.map(
        user -> {
          try {
            return capitalizeUser(user);
          } catch (CapitalizeError e) {
            throw Exceptions.propagate(e);
          }
        });
  }

  private User capitalizeUser(User user) throws CapitalizeError {
    if (user.equals(User.SAUL)) {
      throw new CapitalizeError();
    }
    return new User(user.getName(), user.getFirstname(), user.getLastname());
  }

  protected final class CapitalizeError extends Exception {}
}
