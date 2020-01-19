package guru.springframework;

public class Sum implements Expression {

  Expression augmend;
  Expression addment;

  public Sum(Expression augmend, Expression addment) {
    this.augmend = augmend;
    this.addment = addment;
  }

  @Override
  public Money reduce(Bank bank, String to) {
    int amount = addment.reduce(bank, to).amount + addment.reduce(bank, to).amount;
    return new Money(amount, to);
  }

  @Override
  public Expression plus(Expression addend) {
    return null;
  }
}
