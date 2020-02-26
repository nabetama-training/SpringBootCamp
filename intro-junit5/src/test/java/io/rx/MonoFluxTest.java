package io.rx;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@TestInstance(Lifecycle.PER_CLASS)
public class MonoFluxTest {

  private static MonoFluxImpl monoFlux = new MonoFluxImpl();

  @Test
  void test__FluxToMono() {
    Mono<String> stringMono = Flux.fromIterable(Arrays.asList("a", "b", "c", "d")).next();

    Assertions.assertEquals(stringMono.block(), "a");
  }
}
