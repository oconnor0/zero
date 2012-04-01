/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public interface ExpressionVisitor<Return, Context> {
  Return visit(NameExpression expr, Context c);
  Return visit(IntegerExpression expr, Context c);
  Return visit(ValDeclExpression expr, Context c);
  Return visit(FnExpression expr, Context c);
  Return visit(ApplyExpression expr, Context c);
  Return visit(MatchExpression expr, Context c);
  Return visit(CaseExpression expr, Context c);
  Return visit(PatternExpression expr, Context c);
}

/*
<R extends Return> R accept(ExpressionVistor<Return, Context> v, Context c)

<R extends Return> R visitName(NameExpression expr, Context c)
<R extends Return> R visitInteger(IntegerExpression expr, Context c)
*/
