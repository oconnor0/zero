/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

import static zero.compiler.parser.Token.Type.*;

import java.util.List;
import java.util.LinkedList;

// public interface PrefixParselet<T> {
//   Expression parse(Parser parser, T token);
// }

class NamePrefixParselet implements PrefixParselet<Token> {
  @Override
  public Expression parse(final Parser<Token> parser, final Token token) {
    return new NameExpression(token);
  }
}

class IntegerPrefixParselet implements PrefixParselet<Token> {
  @Override
  public Expression parse(final Parser<Token> parser, final Token token) {
    return new IntegerExpression(token);
  }
}

class ValDeclPrefixParselet implements PrefixParselet<Token> {
  @Override
  public Expression parse(final Parser<Token> parser, final Token token) {
    final Token name = parser.consume(NAME);
    parser.consume(EQUALS);
    final Expression val = parser.parseExpression();
    return new ValDeclExpression(new NameExpression(name), val);
    // if(parser.lookAhead(0, NAME, EQUALS)) {
    //   final Token name = parser.consume();
    //   final Token eq = parser.consume();
    //   final Expression val = parser.parseExpression();
    //   return new ValDeclExpression(new NameExpression(name), val);
    // } else {
    //   throw new ParseException(parser.lookAhead(0).getType(), NAME);
    // }
  }
}

class FnPrefixParselet implements  PrefixParselet<Token> {
  @Override
  public Expression parse(final Parser<Token> parser, final Token token) {
    final List<NameExpression> names = new LinkedList<>();
    while(parser.lookAhead(0).getType() == NAME) {
      names.add(new NameExpression(parser.consume()));
    }
    parser.consume(RARROW);
    final Expression body = parser.parseExpression();
    return new FnExpression(body, names.toArray(new NameExpression[names.size()]));
  }
}
