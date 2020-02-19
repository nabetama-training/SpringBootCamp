package io.rx;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.rx.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part09Adapt {

  Flowable<User> fromFluxToFlowable(Flux<User> flux) {
    return Flowable.fromPublisher(flux);
  }

  public Flux<User> fromFlowableToFlux(Flowable<User> flowable) {
    return Flux.from(flowable);
  }

  public Observable<User> fromFluxToObservable(Flux<User> flux) {
    return Observable.fromPublisher(flux);
  }

  public Flux<User> fromObservableToFlux(Observable<User> observable) {
    return Flux.from(observable.toFlowable(BackpressureStrategy.BUFFER));
  }

  public Single<User> fromMonoToSingle(Mono<User> mono) {
    return Single.fromPublisher(mono);
  }

  public Mono<User> fromSingleToMono(Single<User> single) {
    return Mono.from(single.toFlowable());
  }

  public CompletableFuture<User> fromMonoToCompletableFuture(Mono<User> mono) {
    return mono.toFuture();
  }

  public Mono<User> fromCompletableFutureToMono(CompletableFuture<User> future) {
    return Mono.fromFuture(future);
  }

  private Integer plus1(int i) {
    return i + 1;
  }

  private Mono<Integer> plus2(int i) {
    return Mono.just(i + 2);
  }

  public Flux<Integer> flatMapTraining() {
    return Flux.fromStream(Stream.of(1, 2, 4).map(this::plus1)) // mapにはMonoを喰わせることができない
        .flatMap(this::plus2) // flatMapにはMonoを食わせることしかできない
        .map(this::time2);
  }

  private Integer time2(Integer integer) {
    return integer * 2;
  }

  public Flux<Integer> fluxFilter() {
    return Flux.fromIterable(Arrays.asList(1, 2, 3, 4)).filter(n -> n % 2 == 0);
  }

  public Flux<Integer> fluxTake() {
    List<Integer> nums = new ArrayList<>();
    return Flux.fromIterable(Arrays.asList(0, 1, 2, 3, 4, 5, 6))
        .mergeWith(Flux.fromIterable(nums))
        .take((2));
  }

  public Flux<Object> fluxMap() {
    return Flux.just("hoge", "fuga").map(s -> "- " + s + " -");
  }
}
