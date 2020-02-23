package io.rx;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
public class MonoSimpleTest {

  private static MonoSimple workshop = new MonoSimple();

  @Test
  void test__MonoJust() {
    StepVerifier.create(workshop.just()).expectNext("Hello, world").verifyComplete();
  }
}
