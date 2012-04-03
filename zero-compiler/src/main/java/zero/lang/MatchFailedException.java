/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.lang;

public class MatchFailedException extends RuntimeException {
  public MatchFailedException(final Object matching) {
    this(matching, null);
  }
  public MatchFailedException(final Object matching, final Throwable cause) {
    super("match \"" + matching + "\" with ... failed all cases", cause);
  }
}