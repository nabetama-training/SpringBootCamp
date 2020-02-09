package io.rx;

import io.rx.domain.User;
import io.rx.repository.ReactiveRepository;
import io.rx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifier.Step;

public class Part06Request {

  ReactiveRepository<User> repository = new ReactiveUserRepository();

  public StepVerifier requestAllExpectFour(Flux<User> flux) {
    return StepVerifier.create(flux)
        .expectNextCount(4)
        .expectComplete();
  }


  public StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
    return StepVerifier.create(flux, 1)
        .expectNext(User.SKYLER)
        .thenRequest(1)
        .expectNext(User.JESSE)
        .thenCancel();
  }

  public Flux<User> fluxWithLog() {
    return repository
        .findAll()
        .log();
  }

  public Flux<User> fluxWithDoOnPrintln() {
    return repository
        .findAll()
        .doOnSubscribe(s -> System.out.println("Starring:"))
        .doOnNext(p -> System.out.println(p.getFirstname() + " " + p.getLastname()))
        .doOnComplete(() -> System.out.println("The end!")); // TO BE REMOVED
  }
}
