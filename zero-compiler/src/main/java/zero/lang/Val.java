/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.lang;

import zero.compiler.parser.Expression;

public class Val {
  final Object val;

  public Val(final Object val) {
    this.val = val;
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
}
