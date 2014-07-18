package com.language.model.literals;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.Expression;

public abstract class Literal extends EvaluatableExpression {

	public abstract Boolean toBoolean();
	public abstract NumericLiteral toNumeric();
	public abstract StringLiteral toJSString();
	public abstract boolean isNaN();
	
	public Literal toPrimitive() {
		return this;
	}
	
	public Literal getValue() {
		return this;
	}
	
	public abstract Literal clone();
}
