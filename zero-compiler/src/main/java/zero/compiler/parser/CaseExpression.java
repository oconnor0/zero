/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class CaseExpression extends AExpression {
  public final PatternExpression bind;
  public final Expression result;

  public CaseExpression(final PatternExpression bind, final Expression result) {
    this.bind = bind;
    this.result = result;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    if(bind.isWildcard()) {
      b.append("else ");
    } else {
      b.append("| ");
      bind.toString(b);
      b.append(" -> ");
    }
    result.toString(b);
    return b;
  }
}
