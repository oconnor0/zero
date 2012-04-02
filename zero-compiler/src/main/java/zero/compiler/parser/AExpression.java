/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public abstract class AExpression implements Expression {
  @Override
  public String toString() {
    return toString(new StringBuilder()).toString();
  }
}
