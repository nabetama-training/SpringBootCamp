package io.rx;

import io.rx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part04Transform {

  public Mono<User> capitalizeOne(Mono<User> mono) {
    return mono
        .map(user -> new User(user.getName().toUpperCase(), user.getFirstname().toUpperCase(),
            user.getLastname().toUpperCase()));
  }

  Flux<User> capitalizeMany(Flux<User> flux) {
    return flux.map(u -> new User(
        u.getName().toUpperCase(),
        u.getFirstname().toUpperCase(),
        u.getLastname().toUpperCase()
        )
    );
  }

  public Flux<User> asyncCapitalizeMany(Flux<User> flux) {
    return flux.flatMap(this::asyncCapitalizeUser);
  }

  Mono<User> asyncCapitalizeUser(User u) {
    return Mono.just(new User(u.getName().toUpperCase(), u.getFirstname().toUpperCase(),
        u.getLastname().toUpperCase()));
  }
}
