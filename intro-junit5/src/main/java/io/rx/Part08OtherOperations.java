package io.rx;

import io.rx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part08OtherOperations {

  public Flux<User> userFluxFromStringFlux(
      Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
    return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
        .doOnNext(System.out::println)
        .map(tuple -> new User(tuple.getT1(), tuple.getT2(), tuple.getT3()));
  }

  public Mono<User> useFastestMono(Mono<User> first, Mono<User> first1) {
    return Mono.first(first, first1);
  }

  public Flux<User> useFastestFlux(Flux<User> user1, Flux<User> user2) {
    return Flux.first(user1, user2);
  }

  public Mono<Void> fluxCompletion(Flux<User> flux) {
    return flux.then();
  }

  public Mono<User> nullAwareUserToMono(User user) {
    // Mono<User>またはemptyを返す
    return Mono.justOrEmpty(user);
  }

  public Mono<User> emptyToSkyler(Mono<User> userMono) {
    // Mono<User> がemptyだったときにデフォルト値を返す
    return userMono.defaultIfEmpty(User.SKYLER);
  }
}
