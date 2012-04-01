/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public interface Expression {
  <R, C> R accept(ExpressionVisitor<R, C> v, C c);
  StringBuilder toString(StringBuilder b);
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
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
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
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    return b.append(val);
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
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
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
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
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
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("((");
    fn.toString(b);
    b.append(')');
    for(final Expression param : params) {
      b.append(' ');
      param.toString(b);
    }
    b.append(')');
    return b;
  }
}

class MatchExpression extends AExpression {
  private final Expression val;
  private final CaseExpression[] cases;

  public MatchExpression(final Expression val, final CaseExpression... cases) {
    this.val = val;
    this.cases = cases == null ? new CaseExpression[0] : cases;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    b.append("match ");
    val.toString(b);
    b.append(" with ");
    for(final CaseExpression c : cases) {
      c.toString(b);
      b.append(' ');
    }
    b.append("end");
    return b;
  }
}

class CaseExpression extends AExpression {
  private final PatternExpression bind;
  private final Expression result;

  public CaseExpression(final PatternExpression bind, final Expression result) {
    this.bind = bind;
    this.result = result;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    if(bind.isWildcard()) {
      b.append("else ");
    } else {
      b.append("| ");
      bind.toString(b);
      b.append(" -> ");
    }
    result.toString(b);
    return b;
  }
}

class PatternExpression extends AExpression {
  public static final PatternExpression WILDCARD = new PatternExpression(new NameExpression(new Token(Token.Type.NAME, "_")));

  private final Expression pattern;

  public PatternExpression(final Expression pattern) {
    this.pattern = pattern;
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> v, C c) {
    return v.visit(this, c);
  }

  @Override
  public StringBuilder toString(final StringBuilder b) {
    pattern.toString(b);
    return b;
  }

  public boolean isWildcard() {
    return pattern.getClass() == NameExpression.class
      && ((NameExpression) pattern).name.equals("_");
  }
}
