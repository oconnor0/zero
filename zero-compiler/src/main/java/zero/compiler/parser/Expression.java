/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public interface Expression {
  <R, C> R accept(ExpressionVisitor<R, C> v, C c);
  StringBuilder toString(StringBuilder b);
}
