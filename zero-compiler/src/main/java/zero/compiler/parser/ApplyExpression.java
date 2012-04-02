/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class ApplyExpression extends AExpression {
  public final Expression fn;
  public final Expression[] params;

  public ApplyExpression(final Expression fn, final Expression... params) {
    this.fn = fn;
    this.params = params == null ? new Expression[0] : params;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("((");
    fn.toString(b);
    b.append(')');
    for(final Expression param : params) {
      b.append(' ');
      param.toString(b);
    }
    b.append(')');
    return b;
  }
}
