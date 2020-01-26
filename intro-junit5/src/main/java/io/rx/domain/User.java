package io.rx.domain;

import java.util.Objects;
import lombok.Data;

@Data
public class User {

  public static final User SKYLER = new User("swhite", "Skyler", "White");
  public static final User JESSE = new User("jpinkman", "Jesse", "Pinkman");
  public static final User WALTER = new User("wwhite", "Walter", "White");
  public static final User SAUL = new User("sgoodman", "Saul", "Goodman");

  private String name;

  private String firstname;

  private String lastname;

  public User(String name, String firstname, String lastname) {
    this.name = name;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(name, user.name) &&
        Objects.equals(firstname, user.firstname) &&
        Objects.equals(lastname, user.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, firstname, lastname);
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        '}';
  }
}
