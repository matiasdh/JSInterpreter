package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;
import com.language.model.util.ExpressionType;


public class EmptyExpression extends EvaluatableExpression {
	
	private ExpressionType t;
	private EvaluatableExpression evaluatableExpression;
	
	public EmptyExpression() {
		this.t = ExpressionType.EMPTY;
	}

	public EmptyExpression(ExpressionType t) {
		this.t = t;
	}
	
	public EmptyExpression(ExpressionType t, EvaluatableExpression expr) {
		this.t = t;
		this.evaluatableExpression = expr;
	}
	
	public ExpressionType getType() {
		return t;
	}
	
	
	@Override
	public Literal toPrimitive() throws Exception {
		return getValue().toPrimitive();
	}

	@Override
	public EvaluatableExpression getValue() {
		if (evaluatableExpression==null) {
			return new NullLiteral();
		}
		return evaluatableExpression;
	}

}
