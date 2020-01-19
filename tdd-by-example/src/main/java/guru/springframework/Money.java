package guru.springframework;

import java.awt.event.MouseEvent;

public class Money implements Expression {
  protected int amount;
  protected String currency;

  public Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  protected String currency() {
    return currency;
  }

  public static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  public static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  @Override
  public boolean equals(Object obj) {
    Money money = (Money) obj;
    return amount == money.amount && currency == money.currency;
  }

  @Override
  public String toString() {
    return "Money{" + "amount=" + amount + ", currency='" + currency + '\'' + '}';
  }

  @Override
  public Money reduce(Bank bank, String to) {
    return  new Money(amount/bank.rate(this.currency, to), to);
  }

  @Override
  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Expression times(int multiplier) {
    return new Money(multiplier * amount, currency);
  }

  public Expression plus(Money addend) {
    return new Sum(this, addend);
  }
}
