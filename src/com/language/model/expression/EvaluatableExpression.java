package com.language.model.expression;

import java.util.ArrayList;

import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;
import com.language.model.literals.NumericLiteral;
import com.language.model.util.Operator;

public abstract class EvaluatableExpression {
	protected Operator operator;
	
	public abstract Literal toPrimitive() throws Exception;
	public abstract EvaluatableExpression getValue() throws Exception;

	public Boolean toBoolean() throws Exception {
		Boolean result = toPrimitive().toBoolean();
		return (result != null && result);
	}
	
	public boolean isCallable() {
		return false;
	}
	
	public EvaluatableExpression getProperty(String propertyName) {
		return new NullLiteral();
	}

	public EvaluatableExpression call(ArrayList<AssignmentExpression> arguments) throws Exception {
		if (this instanceof NumericLiteral) {
			throw new IllegalArgumentException("SyntaxError: Unexpected token ILLEGAL");
		}
		return new NullLiteral();
	}
	
	public boolean isPutable() {
		return false;
	}
	public void putValue(EvaluatableExpression value) {
		// Stub Method
	};
}
