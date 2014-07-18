package com.language.model.expression;

public abstract class LeftHandSideExpression extends AssignmentExpression {

	public abstract void putValue(EvaluatableExpression rightval);

}
