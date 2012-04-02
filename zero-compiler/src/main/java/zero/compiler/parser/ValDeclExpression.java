/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class ValDeclExpression extends AExpression {
  public final NameExpression name;
  public final Expression val;

  public ValDeclExpression(final NameExpression name, final Expression val) {
    this.name = name;
    this.val = val;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("val ");
    name.toString(b);
    b.append(" = ");
    val.toString(b);
    return b;
  }
}
