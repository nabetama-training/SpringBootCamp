package guru.springframework;

public class Sum implements Expression {

  Money augmend;
  Money addment;

  public Sum(Money augmend, Money addment) {
    this.augmend = augmend;
    this.addment = addment;
  }

  public Money reduce(String to) {
    int amount = augmend.amount + addment.amount;
    return new Money(amount, to);
  }
}
