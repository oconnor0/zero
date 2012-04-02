/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class PatternExpression extends AExpression {
  public static final PatternExpression WILDCARD = new PatternExpression(new NameExpression(new Token(Token.Type.NAME, "_")));

  public final Expression pattern;

  public PatternExpression(final Expression pattern) {
    this.pattern = pattern;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    pattern.toString(b);
    return b;
  }

  public boolean isWildcard() {
    return pattern.getClass() == NameExpression.class
      && ((NameExpression) pattern).name.equals("_");
  }
}
