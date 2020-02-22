package io.rx;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.rx.domain.User;
import io.rx.repository.ReactiveRepository;
import io.rx.repository.ReactiveUserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifier.Step;

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

  @Test
  void fluxFilterTest() {
    StepVerifier.create(workshop.fluxFilter()).expectNext(2, 4).verifyComplete();
  }

  @Test
  void fluxTakeTest() {
    StepVerifier.create(workshop.fluxTake()).expectNext(0, 1).verifyComplete();
  }

  @Test
  void fluxMapTest() {
    StepVerifier.create(workshop.fluxMap()).expectNext("- hoge -", "- fuga -").verifyComplete();
  }

  @Test
  void fluxBufferTest() {
    List<Integer> first = Arrays.asList(1, 2);
    List<Integer> second = Arrays.asList(3, 4);
    List<Integer> third = Arrays.asList(5, 6);
    StepVerifier.create(workshop.fluxBuffer()).expectNext(first, second, third).verifyComplete();
  }

  @Test
  void fluxMergeTest() {
    Flux<Integer> nums1 = Flux.just(1, 2, 3);
    Flux<Integer> nums2 = Flux.just(4, 5, 6);
    StepVerifier.create(Flux.merge(nums1, nums2)).expectNext(1, 2, 3, 4, 5, 6).verifyComplete();
  }

  @Test
  void fluxZipTest() {
    
  }
}
