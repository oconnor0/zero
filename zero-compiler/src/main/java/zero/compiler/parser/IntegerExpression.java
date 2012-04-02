/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class IntegerExpression extends AExpression {
  public final int val;

  public IntegerExpression(final Token token) {
    this.val = Integer.parseInt(token.getText());
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    return b.append(val);
  }
}
