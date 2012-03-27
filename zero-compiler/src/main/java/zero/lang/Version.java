/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.lang;

public final class Version {
	public static final int MAJOR = 0;
	public static final int MINOR = 0;
	public static final int PATCH = 1;
	public static final int BUILD = 22;

	public static final String string() {
		return MAJOR + "." + MINOR + "." + PATCH + "." + BUILD;
	}

  private Version() {}
}