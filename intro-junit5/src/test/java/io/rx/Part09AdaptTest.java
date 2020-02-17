package io.rx;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.rx.domain.User;
import io.rx.repository.ReactiveRepository;
import io.rx.repository.ReactiveUserRepository;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class Part09AdaptTest {

  Part09Adapt workshop = new Part09Adapt();
  ReactiveRepository<User> repository = new ReactiveUserRepository();

  @Test
  public void adaptToFlowable() {
    Flux<User> flux = repository.findAll();
    Flowable<User> flowable = workshop.fromFluxToFlowable(flux);
    StepVerifier.create(workshop.fromFlowableToFlux(flowable))
        .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
        .verifyComplete();
  }

  @Test
  void adaptToObservable() {
    Flux<User> flux = repository.findAll();
    Observable<User> observable = workshop.fromFluxToObservable(flux);
    StepVerifier.create(workshop.fromObservableToFlux(observable))
        .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
        .verifyComplete();
  }

  @Test
  public void adaptToSingle() {
    Mono<User> mono = repository.findFirst();
    Single<User> single = workshop.fromMonoToSingle(mono);
    StepVerifier.create(workshop.fromSingleToMono(single)).expectNext(User.SKYLER).verifyComplete();
  }

  @Test
  public void adaptToCompletableFuture() {
    Mono<User> mono = repository.findFirst();
    CompletableFuture<User> future = workshop.fromMonoToCompletableFuture(mono);
    StepVerifier.create(workshop.fromCompletableFutureToMono(future))
        .expectNext(User.SKYLER)
        .verifyComplete();
  }

  @Test
  void flatMapTrainingTest() {
    StepVerifier.create(workshop.flatMapTraining()).expectNext(8, 10, 14).verifyComplete();
  }
}
