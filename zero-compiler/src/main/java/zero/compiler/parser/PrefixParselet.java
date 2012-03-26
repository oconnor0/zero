/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public interface PrefixParselet<T extends Token> {
  Expression parse(Parser<T> parser, T token);
}
