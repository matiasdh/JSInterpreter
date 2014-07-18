package com.language.model.expression;

import com.language.model.expression.EmptyExpression;
import com.language.model.literals.Literal;
import com.language.model.util.ExpressionType;

public class ContinueStatement  extends Statement {
	
	public ContinueStatement() {
	}

	@Override
	public EvaluatableExpression getValue() {
		return new EmptyExpression(ExpressionType.CONTINE);
	}
}
