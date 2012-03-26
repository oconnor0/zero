/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

// public interface MixfixParselet<T extends Token> {
//   Expression parse(Parser<T> parser, Expression left, T token);
//   int getPrecedence();
// }

class BinOpParselet implements MixfixParselet<Token> {
  private final Token.Type op;
  private final int precedence;

  public BinOpParselet(final Token.Type op, final int precedence) {
    this.op = op;
    this.precedence = precedence;
  }

  @Override
  public Expression parse(final Parser<Token> parser, final Expression left, final Token token) {
    final Expression right = parser.parseExpression(getPrecedence());
    return new BinOpExpression(left, token, right);
  }

  @Override
  public int getPrecedence() {
    return precedence;
  }
}