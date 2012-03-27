/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public interface Expression {
  public StringBuilder toString(StringBuilder b);
}

abstract class AExpression implements Expression {
  @Override
  public String toString() {
    return toString(new StringBuilder()).toString();
  }
}

class NameExpression extends AExpression {
  final String name;

  public NameExpression(final Token token) {
    this.name = token.getText();
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    return b.append(name);
  }
}

class IntegerExpression extends AExpression {
  final int val;

  public IntegerExpression(final Token token) {
    this.val = Integer.parseInt(token.getText());
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    return b.append(val);
  }
}

class BinOpExpression extends AExpression {
  private final Expression left, right;
  private final Token op;

  public BinOpExpression(final Expression left, final Token op, final Expression right) {
    this.left  = left;
    this.op    = op;
    this.right = right;
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append('(');
    left.toString(b);
    b.append(op.getText());
    right.toString(b);
    b.append(')');
    return b;
  }
}

class ValDeclExpression extends AExpression {
  private final NameExpression name;
  private final Expression val;

  public ValDeclExpression(final NameExpression name, final Expression val) {
    this.name = name;
    this.val = val;
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("val ");
    name.toString(b);
    b.append(" = ");
    val.toString(b);
    return b;
  }
}

class FnExpression extends AExpression {
  private final NameExpression[] params;
  private final Expression body;

  public FnExpression(final Expression body, final NameExpression... params) {
    this.params = params == null ? new NameExpression[0] : params;
    this.body = body;
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("fn ");
    for(final NameExpression name : params) {
      name.toString(b).append(' ');
    }
    b.append("-> ");
    body.toString(b);
    return b;
  }
}

class ApplyExpression extends AExpression {
  private final Expression fn;
  private final Expression[] params;

  public ApplyExpression(final Expression fn, final Expression... params) {
    this.fn = fn;
    this.params = params == null ? new Expression[0] : params;
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("((");
    fn.toString(b).append(')');
    for(final Expression param : params) {
      b.append(' ');
      param.toString(b);
    }
    b.append(')');
    return b;
  }
}
