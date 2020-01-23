package guru.springframework;

class Money implements Expression {
  final int amount;
  private final String currency;

  public Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  protected String currency() {
    return currency;
  }

  static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  @Override
  public boolean equals(Object obj) {
    Money money = (Money) obj;
    if (money == null) {
      return false;
    }
    return amount == money.amount && currency.equals(money.currency);
  }

  @Override
  public String toString() {
    return "Money{" + "amount=" + amount + ", currency='" + currency + '\'' + '}';
  }

  @Override
  public Money reduce(Bank bank, String to) {
    return new Money(amount / bank.rate(this.currency, to), to);
  }

  @Override
  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  @Override
  public Expression times(int multiplier) {
    return new Money(multiplier * amount, currency);
  }
}
