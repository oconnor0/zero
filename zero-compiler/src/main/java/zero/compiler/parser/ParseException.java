/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

import java.util.Arrays;

public class ParseException extends RuntimeException {

  public ParseException(final String message) {
    super(message);
  }

  public ParseException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ParseException(final Token.Type found, final Token.Type... expected) {
    super(String.format("Expected a token of type(s) %s, but found %s", Arrays.toString(expected), found));
  }
}