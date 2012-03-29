/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public interface MixfixParselet<T extends Token> {
  Expression parse(Parser<T> parser, Expression left, T token);
  int getPrecedence();
}
