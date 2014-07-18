package com.language.model.expression;

import com.language.model.literals.Literal;

public class PrimaryExpression extends MemberExpression {
	private Literal literal;
	private EvaluatableExpression expression;
	
	protected PrimaryExpression() {
	}
	
	public PrimaryExpression(Literal literal) {
		this.literal = literal;
	}
	
	public PrimaryExpression(Expression expression) {
		this.expression = expression;
	}
	
	public PrimaryExpression(EvaluatableExpression expression) {
		this.expression = expression;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		if (expression == null) {
			return literal.getValue();
		}
		else {
			return expression.getValue();
		}
	}
	
	@Override
	public Literal toPrimitive() throws Exception {
		return getValue().toPrimitive();
	}
}
