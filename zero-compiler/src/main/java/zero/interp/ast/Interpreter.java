/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.interp.ast;

import zero.lang.*;
import zero.compiler.parser.*;

import java.util.Map;

public class Interpreter {
  public static Result eval(final Expression e, final Scope in) {
    return e.accept(AstWalk.INSTANCE, in);
  }
}

enum AstWalk implements ExpressionVisitor<Result, Scope>  {
  INSTANCE;

  @Override
  public Result visit(NameExpression expr, Scope scope) {
    return new Result(scope.get(expr.name), scope);
  }

  @Override
  public Result visit(IntegerExpression expr, Scope scope) {
    return new Result(new Val(expr.val), scope);
  }

  @Override
  public Result visit(ValDeclExpression expr, Scope scope) {
    final Val fn = new Val(expr.val);
    scope = scope.add(expr.name.name, fn);
    return new Result(fn, scope);
  }

  @Override
  public Result visit(FnExpression expr, Scope scope) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Result visit(ApplyExpression expr, Scope scope) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Result visit(MatchExpression expr, Scope scope) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Result visit(CaseExpression expr, Scope scope) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Result visit(PatternExpression expr, Scope scope) {
    throw new UnsupportedOperationException();
  }
}