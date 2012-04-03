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
    return new Result(scope.get(expr.name)/*.visit(this, scope).val*/, scope);
  }

  @Override
  public Result visit(IntegerExpression expr, Scope scope) {
    return new Result(Val.wrap(expr.val), scope);
  }

  @Override
  public Result visit(ValDeclExpression expr, Scope scope) {
    final Val val = expr.val.accept(this, scope).val;
    scope = scope.add(expr.name.name, val);
    return new Result(val, scope);
  }

  @Override
  public Result visit(FnExpression expr, Scope scope) {
    if(scope.params == null) {
      // no parameters passed in, just return ourselves
      return new Result(new Val(expr), scope);
    } else {
      // have parameters, evaluate fn
      for(int i = 0, n = expr.params.length; i < n; i++) {
        final String paramName = expr.params[i].name;
        final Val paramVal = scope.params[i];
        scope = scope.add(paramName, paramVal);
      }
      // clear out parameters so they don't propagate down & muck stuff up
      scope = scope.noParams();
      return expr.body.accept(this, scope);
    }
  }

  @Override
  public Result visit(ApplyExpression expr, Scope scope) {
    final Val valOfFn = expr.fn.accept(this, scope).val;

    final Val[] params = new Val[expr.params.length];
    int i = 0;
    for(final Expression param : expr.params) {
      params[i++] = param.accept(this, scope).val;
    }

    if(valOfFn.isFn2()) {
      final Fn2 fn = valOfFn.asFn2();
      return new Result(Val.wrap(fn.apply(params[0], params[1])), scope);
    } else {
      final FnExpression fn = (FnExpression) valOfFn.asExpression();
      return fn.accept(this, scope.params(params));
    }
  }

  @Override
  public Result visit(MatchExpression expr, Scope scope) {
    final Val matching = expr.val.accept(this, scope).val;
    scope = scope.params(matching);
    throw new UnsupportedOperationException("match");
  }

  @Override
  public Result visit(CaseExpression expr, Scope scope) {
    throw new UnsupportedOperationException("case");
  }

  @Override
  public Result visit(PatternExpression expr, Scope scope) {
    throw new UnsupportedOperationException("pattern");
  }
}