/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.lang;

import zero.compiler.parser.Expression;

public class Val {
  public static final Val UNIT = new Val("()");

  public static Val wrap(final Object o) {
    if(o instanceof Val) {
      return (Val) o;
    } else {
      return new Val(o);
    }
  }

  public final Object val;

  public Val(final Object val) {
    if(val instanceof Val) {
      throw new IllegalArgumentException("cannot wrap " + val + " in Val");
    }
    this.val = val;
  }

  public boolean isFn2() {
    return val instanceof Fn2;
  }

  public boolean isExpression() {
    return val instanceof Expression;
  }

  public Fn2 asFn2() {
    return (Fn2) val;
  }

  public Integer asInteger() {
    return (Integer) val;
  }

  public Expression asExpression() {
    return (Expression) val;
  }

  @Override
  public String toString() {
    final StringBuilder b = new StringBuilder();
    b.append("Val(").append(val).append(")");
    return b.toString();
  }
}
