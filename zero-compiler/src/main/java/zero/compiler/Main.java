/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler;

import java.io.Console;
import java.io.InputStream;
import java.io.FileInputStream;

import zero.lang.Version;
import zero.compiler.parser.*;

public final class Main {
  static final Console C = System.console();
  static final String PROMPT = "> ";
  static final String NL = System.lineSeparator();

  public static void main(final String[] args) throws Exception {
    C.printf(welcome());

    if(args.length > 0) {
      for(final String file : args) {
        final Expression expr = read(new FileInputStream(file));
        eval(expr);
      }
    }

    C.printf(PROMPT);
    String line;
    while((line = C.readLine()) != null) {
      handleSpecial(line);

      final Expression expr = read(line);
      eval(expr);
      C.printf(PROMPT);
    }
  }

  private static Expression read(final InputStream in) {
    final Lexer l = new Lexer(in);
    final Parser p = new Parser(l);
    final Expression expr = p.parseExpression();
    return expr;
  }

  private static Expression read(final String line) {
    final Lexer l  = new Lexer(line);
    final Parser p = new Parser(l);
    final Expression expr = p.parseExpression();
    return expr;
  }

  private static void eval(final Expression expr) {
    C.printf("%s%n", expr);
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
