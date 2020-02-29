package io.rx;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.test.StepVerifier;
import reactor.test.StepVerifier.Step;

@TestInstance(Lifecycle.PER_CLASS)
public class MonoSimpleTest {

  private static MonoSimple workshop = new MonoSimple();

  @Test
  void test__MonoJust() {
    StepVerifier.create(workshop.just()).expectNext("Hello, world").verifyComplete();
  }

  @Test
  void test__MonoFromSupplier() {
    StepVerifier.create(workshop.fromSupplier()).expectNext("Hello, world").verifyComplete();
  }

  @Test
  void test__MonoMap() {
    StepVerifier.create(workshop.MonoMap("nabetama")).expectNext("Hello, nabetama").verifyComplete();
  }
}
