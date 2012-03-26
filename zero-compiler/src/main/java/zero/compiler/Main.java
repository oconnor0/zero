/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler;

import java.io.Console;

import zero.lang.Version;
import zero.compiler.parser.*;

public final class Main {
  static final Console C = System.console();
  static final String PROMPT = "> ";
  static final String NL = System.lineSeparator();

  public static void main(final String[] args) {
    C.printf(welcome());
    C.printf(PROMPT);
    String line;
    while((line = C.readLine()) != null) {
      handleSpecial(line);

      final Lexer l  = new Lexer(line);
      final Parser p = new Parser(l);
      final Expression expr = p.parseExpression();
      C.printf("%s%n", expr);
      C.printf(PROMPT);
    }
  }

  private static void handleSpecial(final String line) {
    if(line.equals("#exit")) {
      System.exit(0);
    }
  }

  private static String welcome() {
    final StringBuilder b = new StringBuilder();
    b.append("zero repl v").append(Version.string()).append(NL);
    b.append(" (C) 2012 Matt O'Connor <thegreendragon@gmail.com>").append(NL);
    return b.toString();
  }
}
