package com.language.model.expression;

public abstract class AssignmentExpression extends EvaluatableExpression {

	public void setPutable(boolean b) {
		// UnaryExpression will overwrite this method
	}
	
}
