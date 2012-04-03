/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.interp.ast;

public class NotBoundException extends RuntimeException {
  public NotBoundException(final String name) {
    this(name, null);
  }
  public NotBoundException(final String name, final Throwable cause) {
    super("\"" + name + "\" not bound in scope", cause);
  }
}