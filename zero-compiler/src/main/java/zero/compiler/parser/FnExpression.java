/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class FnExpression extends AExpression {
  public final NameExpression[] params;
  public final Expression body;

  public FnExpression(final Expression body, final NameExpression... params) {
    this.params = params == null ? new NameExpression[0] : params;
    this.body = body;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("fn ");
    for(final NameExpression name : params) {
      name.toString(b).append(' ');
    }
    b.append("-> ");
    body.toString(b);
    return b;
  }
}
