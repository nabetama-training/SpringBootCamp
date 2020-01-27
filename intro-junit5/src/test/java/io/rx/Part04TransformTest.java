package io.rx;

import io.rx.domain.User;
import io.rx.repository.ReactiveRepository;
import io.rx.repository.ReactiveUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class Part04TransformTest {

  Part04Transform workshop = new Part04Transform();
  ReactiveRepository<User> repository = new ReactiveUserRepository();

  @Test
  void transformMono() {
    Mono<User> mono = repository.findFirst();
    StepVerifier.create(workshop.capitalizeOne(mono))
        .expectNext(new User("SWHITE", "SKYLER", "WHITE"))
        .verifyComplete();
  }
}
