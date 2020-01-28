package io.rx.repository;

import io.rx.domain.User;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveUserRepository implements ReactiveRepository<User> {

  private final static long DEFAULT_DELAY_IN_MS = 100;

  private final long delayInMs;

  private final List<User> users;

  public ReactiveUserRepository() {
    this(DEFAULT_DELAY_IN_MS);
  }

  public ReactiveUserRepository(long delayInMs) {
    this.delayInMs = delayInMs;
    this.users = new ArrayList<>(Arrays.asList(User.SKYLER, User.JESSE, User.WALTER, User.SAUL));
  }

  @Override
  public Mono<Void> save(Publisher<User> publisher) {
    return null;
  }

  @Override
  public Mono<User> findFirst() {
    return withDelay(Mono.just(users.get(0)));
  }

  @Override
  public Flux<User> findAll() {
    return withDelay(Flux.fromIterable(users));
  }

  @Override
  public Mono<User> findById(String name) {
    User user = users.stream().filter((u) -> u.getName().equals(name))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(name + " is not available"));
    return withDelay(Mono.just(user));
  }

  private Mono<User> withDelay(Mono<User> userMono) {
    return Mono
//        delayInMsのぶん遅延させる
        .delay(Duration.ofMillis(delayInMs))
        // 単に遅延させたいだけなので, delay()のreturn valueは使わずに読み捨てる。
        // 引数で受け取ったuserMonoをそのままreturnする
        .flatMap(c -> userMono);
  }

  private Flux<User> withDelay(Flux<User> userFlux) {
    return Flux
//        delayInMsのインターバルで
        .interval(Duration.ofMillis(delayInMs))
//        userFluxを順に返す。インデックスは読み捨て
        .zipWith(userFlux, (i, user) -> user);
  }
}
