/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.interp.ast;

import zero.lang.*;
import zero.compiler.parser.*;

import java.util.Map;

public class Result {
  public final Val val;
  public final Scope newScope;

  public Result(final Val val) {
    this(val, null);
  }

  public Result(final Scope newScope) {
    this(null, newScope);
  }

  public Result(final Val val, final Scope newScope) {
    this.val = val;
    this.newScope = newScope;
  }
}
