/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

import static zero.compiler.parser.Token.Type.*;

import zero.util.LookAheadIterator;

import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Parser<T extends Token> {
  private final LookAheadIterator<T> lexer;
  private final Map<Token.Type, PrefixParselet<Token>> prefixParselets = new HashMap<>();
  private final Map<Token.Type, MixfixParselet<Token>> mixfixParselets = new HashMap<>();

  public Parser(final Iterator<T> lexer) {
    this.lexer = new LookAheadIterator<>(lexer);
    makeGrammar();
  }

  public Expression parseExpression() {
    return parseExpression(0);
  }

  public Expression parseExpression(final int precedence) {
    T token = consume();
    final PrefixParselet prefix = prefixParselets.get(token.getType());

    if(prefix == null) {
      throw new ParseException(String.format("Cannot parse expression beginning with \"%s\"", token));
    }

    Expression left = prefix.parse(this, token);

    while(precedence < getPrecedence()) {
      token = consume();

      final MixfixParselet mixfix = mixfixParselets.get(token.getType());
      left = mixfix.parse(this, left, token);
    }

    return left;
  }

  public int getPrecedence() {
    return getPrecedence(0);
  }

  public int getPrecedence(final int distance) {
    final MixfixParselet<Token> mixfix = mixfixParselets.get(lookAhead(distance).getType());
    if(mixfix == null) {
      return 0;
    } else {
      return mixfix.getPrecedence();
    }
  }

  public T lookAhead(final int distance) {
    return lexer.lookAhead(distance);
  }

  public boolean lookAhead(final int distance, final Token.Type... types) {
    for(int i = 0, n = types.length; i < n; i++) {
      final Token.Type found = lookAhead(i + distance).getType();
      if(! found.equals(types[i])) {
        return false;
      }
    }
    return true;
  }

  public boolean any(final int distance, final Token.Type... types) {
    final Token.Type found = lookAhead(distance).getType();
    return Arrays.binarySearch(types, found) >= 0;
  }

  public T consume() {
    System.out.println(lexer.lookAhead(0));
    return lexer.next();
  }

  public T consume(final Token.Type expected) {
    final Token.Type found = lookAhead(0).getType();
    if(found == expected) {
      return consume();
    } else {
      throw new ParseException(found, expected);
    }
  }

  private void makeGrammar() {
    prefixParselets.put(NAME,     new NamePrefixParselet());
    prefixParselets.put(INTEGER,  new IntegerPrefixParselet());
    prefixParselets.put(VAL,      new ValDeclPrefixParselet());
    prefixParselets.put(FN,       new FnPrefixParselet());
    prefixParselets.put(LPAREN,   new GroupingPrefixParselet());

    mixfixParselets.put(PLUS,     new BinOpParselet(PLUS, Precedence.PLUS));
    mixfixParselets.put(MINUS,    new BinOpParselet(MINUS, Precedence.PLUS));
    mixfixParselets.put(ASTERISK, new BinOpParselet(ASTERISK, Precedence.TIMES));
  }
}
