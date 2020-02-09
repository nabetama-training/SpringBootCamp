package io.rx;

import io.rx.domain.User;
import io.rx.repository.ReactiveRepository;
import io.rx.repository.ReactiveUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class Part05MergeTest {

  Part05Merge workshop = new Part05Merge();

  final static User MARIE = new User("mschrader", "Marie", "Schrader");
  final static User MIKE = new User("mehrmantraut", "Mike", "Ehrmantraut");

  ReactiveRepository<User> repositoryWithDelay = new ReactiveUserRepository(500);
  ReactiveRepository<User> repository          = new ReactiveUserRepository(MARIE, MIKE);

  //  test --
  @Test
  void mergeWithInterleave() {
    Flux<User> flux = workshop
        .mergeFluxWithInterleave(repositoryWithDelay.findAll(), repository.findAll());
    StepVerifier.create(flux)
        .expectNext(MARIE, MIKE, User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
        .verifyComplete();
  }

  @Test
  void mergeWithNoInterleave() {
    Flux<User> flux = workshop
        .mergeFluxWithNoInterleave(repositoryWithDelay.findAll(), repository.findAll());
    StepVerifier.create(flux)
        .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL, MARIE, MIKE)
        .verifyComplete();
  }

  @Test
  void multipleMonoToFlux() {
    Mono<User> skylerMono = repositoryWithDelay.findFirst();
    Mono<User> marieMono = repository.findFirst();
    Flux<User> flux = workshop.createFluxFromMultipleMono(skylerMono, marieMono);
    StepVerifier.create(flux)
        .expectNext(User.SKYLER, MARIE)
        .verifyComplete();
  }
}
