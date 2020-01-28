package io.rx;

import io.rx.domain.User;
import java.util.List;
import java.util.stream.Collectors;
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

  private List<Integer> getNumbers() {
    return List.of(3, 1, -4, 1, -5, 9, -2, 6, 5, 3, 5);
  }

  private boolean isOverMinus5(int n) {
    return n > -5;
  }

  List<Integer> getNumsOverMinus5() {
    return this.getNumbers()
        .stream()
        .filter(this::isOverMinus5)
        .collect(Collectors.toList());
  }
}
