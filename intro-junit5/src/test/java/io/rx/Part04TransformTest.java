package io.rx;

import static org.assertj.core.api.Assertions.assertThat;

import io.rx.domain.User;
import io.rx.repository.ReactiveRepository;
import io.rx.repository.ReactiveUserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
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


  @Test
  public void transformFlux() {
    Flux<User> flux = repository.findAll();
    StepVerifier
        .create(workshop.capitalizeMany(flux))
        .expectNext(
            new User("SWHITE", "SKYLER", "WHITE"),
            new User("JPINKMAN", "JESSE", "PINKMAN"),
            new User("WWHITE", "WALTER", "WHITE"),
            new User("SGOODMAN", "SAUL", "GOODMAN"))
        .verifyComplete();
  }

  @Test
  void asyncTransformFlux() {
    Flux<User> flux = repository.findAll();
    StepVerifier.create(workshop.asyncCapitalizeMany(flux))
        .expectNext(
            new User("SWHITE", "SKYLER", "WHITE"),
            new User("JPINKMAN", "JESSE", "PINKMAN"),
            new User("WWHITE", "WALTER", "WHITE"),
            new User("SGOODMAN", "SAUL", "GOODMAN"))
        .verifyComplete();
  }

  @Test
  void streamTest() {
    List<Integer> nums = workshop.getNumsOverMinus5();
    assertThat(nums).containsExactly(3, 1, -4, 1, 9, -2, 6, 5, 3, 5);
  }
}
