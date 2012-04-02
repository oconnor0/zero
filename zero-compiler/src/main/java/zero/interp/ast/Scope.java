/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.interp.ast;

import zero.lang.*;
import zero.compiler.parser.*;
import zero.util.*;

import java.util.Map;

public class Scope {
  public static final Scope EMPTY = new Scope(Nil.<String, Val>get());

  private final Associative<String, Val> defs;

  private Scope(final Associative<String, Val> defs) {
    this.defs = defs;
  }

  public Scope add(final String name, final Val val) {
    return new Scope(defs.add(name, val));
  }

  public Val get(final String name) {
    return defs.get(name);
  }
}
