/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

import static zero.compiler.parser.Token.Type.*;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;

import com.google.common.collect.ImmutableSet;

// public interface MixfixParselet<T extends Token> {
//   Expression parse(Parser<T> parser, Expression left, T token);
//   int getPrecedence();
// }

class EofParselet implements MixfixParselet<Token> {
  @Override
  public ApplyExpression parse(final Parser<Token> parser, final Expression left, final Token token) {
    throw new ParseException("Unexpected end of input");
  }

  @Override
  public int getPrecedence() {
    return Precedence.LOWEST;
  }
}

class BinOpParselet implements MixfixParselet<Token> {
  private final Token.Type op;
  private final int precedence;

  public BinOpParselet(final Token.Type op, final int precedence) {
    this.op = op;
    this.precedence = precedence;
  }

  @Override
  public BinOpExpression parse(final Parser<Token> parser, final Expression left, final Token token) {
    final Expression right = parser.parseExpression(getPrecedence());
    return new BinOpExpression(left, token, right);
  }

  @Override
  public int getPrecedence() {
    return precedence;
  }
}

class ApplyParselet implements MixfixParselet<Token> {
  private static final Set<Token.Type> ARG_TOKEN_TYPES = ImmutableSet.of(NAME, LPAREN, INTEGER);

  @Override
  public ApplyExpression parse(final Parser<Token> parser, final Expression left, Token token) {
    final List<Expression> args = new LinkedList<>();

    args.add(parseArgument(parser, token));

    token = parser.lookAhead(0);
    while(ARG_TOKEN_TYPES.contains(token.getType())) {
      args.add(parseArgument(parser, parser.consume()));
      token = parser.lookAhead(0);
    }

    return new ApplyExpression(left, args.toArray(new Expression[args.size()]));
  }

  private Expression parseArgument(final Parser<Token> parser, final Token initial) {
    final Token.Type type = initial.getType();
    if(type == NAME) {
      return new NameExpression(initial);
    } else if(type == INTEGER) {
      return new IntegerExpression(initial);
    } else if (type == LPAREN) {
      final Expression arg = parser.parseExpression();
      parser.consume(RPAREN);
      return arg;
    }
    throw new ParseException(type, NAME, LPAREN);
  }

  @Override
  public int getPrecedence() {
    return Precedence.APPLY;
  }
}