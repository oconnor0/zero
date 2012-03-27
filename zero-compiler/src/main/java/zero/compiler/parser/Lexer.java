/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

import static java.lang.Character.*;

import zero.compiler.parser.Token.Type;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.Iterator;

public class Lexer implements Iterator<Token> {
  private final PushbackReader in;

  public Lexer(final String s) {
    this.in = new PushbackReader(new StringReader(s));
  }

  public boolean hasNext() {
    return true;
  }

  public Token next() {
    try {
      int c = peek();

      if(isWhitespace(c)) {
        lex(Check.WHITESPACE);
      }

      c = peek();

      if(c > 0) {
        if(isLowerCase(c)) {
          return read(Check.NAME, Type.NAME);
        } else if(isDigit(c)) {
          return read(Type.INTEGER, Check.INTEGER);
        } else if(Check.SYMBOL.is(c)) {
          return read(Check.SYMBOL, Type.ERROR);
        }
      }

      return Token.EOF;
    } catch(final IOException exn) {
      throw new ParseException("Cannot lex input stream", exn) ;
    }
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }

  private String lex(final Check check) throws IOException {
    final StringBuilder b = new StringBuilder();
    int c;
    while(check.is(c = read())) {
      b.append((char)c);
    }
    in.unread(c);
    return b.toString();
  }

  private Token read(final Token.Type type, final Check check) throws IOException {
    return new Token(type, lex(check));
  }

  private Token read(final Check check, final Token.Type deflt) throws IOException {
    final String text = lex(check);
    Token.Type type = deflt;
    try {
      type = Type.fromString(text);
    } catch(final ParseException ignore) {}
    return new Token(type, text);
  }

  private int peek() throws IOException {
    final int c = in.read();
    in.unread(c);
    return (c == (char) -1) ? -1 : c;
  }

  private int read() throws IOException {
    final int c = in.read();
    return c;
  }
}

enum Check {
  NAME {
    public boolean is(final int c) {
      return isLowerCase(c);
    }
  },
  INTEGER {
    public boolean is(final int c) {
      return isDigit(c);
    }
  },
  SYMBOL {
    public boolean is(final int c) {
      return "~`!@#$%^&*()_-+=[]{}\\|;:\"'<>,.?/".contains(Character.toString((char)c));
    }
  },
  WHITESPACE {
    public boolean is(final int c) {
      return isWhitespace(c);
    }
  };

  abstract boolean is(int c);
}
