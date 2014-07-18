package com.language.model.expression;

public class ExpressionStatement extends Statement  {
	
	private Expression expression;
	
	public ExpressionStatement(Expression expression) {
		this.expression = expression;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		return expression.getValue();
	}
}
