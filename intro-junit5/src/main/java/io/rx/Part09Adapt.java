package io.rx;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.rx.domain.User;
import java.util.concurrent.CompletableFuture;
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
}
