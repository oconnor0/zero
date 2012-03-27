/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public final class Precedence {
  public static final int LOWEST = 0;
  public static final int VAL    = 0;
	public static final int PLUS   = 3;
	public static final int TIMES  = 4;
  public static final int APPLY  = 9;

  private Precedence() {}
}
