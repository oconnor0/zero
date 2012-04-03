/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.interp.ast;

import zero.lang.*;
import zero.compiler.parser.*;
import zero.util.*;

import java.util.Map;

public class Scope {
  public static final Scope EMPTY = new Scope(Nil.<String, Val>get(), null);

  public final Associative<String, Val> defs;
  public final Val[] params;

  private Scope(final Associative<String, Val> defs, final Val[] params) {
    this.defs = defs;
    this.params = params;
  }

  public Scope add(final String name, final Val val) {
    return new Scope(defs.add(name, val), params);
  }

  public Val get(final String name) {
    final Val found = defs.get(name);
    if(found == null) {
      throw new NotBoundException(name);
    } else {
      return found;
    }
  }

  public Scope params(final Val... params) {
    return new Scope(defs, params);
  }

  public Scope noParams() {
    return new Scope(defs, null);
  }
}
