package io.rx;

import io.rx.domain.User;
import reactor.core.publisher.Mono;

public class Part04Transform {

  public Mono<User> capitalizeOne(Mono<User> mono) {
    return mono
        .map(user -> new User(user.getName().toUpperCase(), user.getFirstname().toUpperCase(),
            user.getLastname().toUpperCase()));
  }
}
