/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.interp.ast;

import zero.compiler.parser.*;

import java.util.Map;

public class Interpreter {
  // with pervasives
  // without

  Result eval(Expression e, Scope in) {
    return null;
  }
}

class Scope {
  Map<String, ?> defs;
}

class Result {
  Expression val;
  Scope updated;
}