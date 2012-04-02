/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.lang;

import zero.interp.ast.Scope;

public final class Pervasives {
  public static final Val plus = new Val(new Fn2<Integer, Integer, Integer>() {
    @Override
    public Integer apply(final Integer a, final Integer b) {
      return a + b;
    }
  });
  public static final Val minus = new Val(new Fn2<Integer, Integer, Integer>() {
    @Override
    public Integer apply(final Integer a, final Integer b) {
      return a - b;
    }
  });
  public static final Val times = new Val(new Fn2<Integer, Integer, Integer>() {
    @Override
    public Integer apply(final Integer a, final Integer b) {
      return a * b;
    }
  });

  public static Scope addAll(final Scope scope) {
    return scope.add("+", plus).add("-", minus).add("*", times);
  }

  private Pervasives() {}
}
