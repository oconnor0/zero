/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class MatchExpression extends AExpression {
  public final Expression val;
  public final CaseExpression[] cases;

  public MatchExpression(final Expression val, final CaseExpression... cases) {
    this.val = val;
    this.cases = cases == null ? new CaseExpression[0] : cases;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("match ");
    val.toString(b);
    b.append(" with ");
    for(final CaseExpression c : cases) {
      c.toString(b);
      b.append(' ');
    }
    b.append("end");
    return b;
  }
}
