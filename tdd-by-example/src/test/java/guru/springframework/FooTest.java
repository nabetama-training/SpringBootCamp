package guru.springframework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FooTest {

  @Test
  void getBar() {
    Foo foo = new Foo();
    String result = foo.getBar();

    assertEquals("FooBar", result);
  }
}
