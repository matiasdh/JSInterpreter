package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;

public class Initialiser extends EvaluatableExpression {
	
	private AssignmentExpression assignmentExpression;

	public Initialiser(AssignmentExpression a) {
		this.assignmentExpression = a;
	}

	@Override
	public Literal toPrimitive()throws Exception {
		EvaluatableExpression res = getValue();
		if(res!=null) {
			return res.toPrimitive();
		}
		return new NullLiteral();
	}
	@Override
	public EvaluatableExpression getValue() throws Exception {
		return assignmentExpression.getValue();
	}
	
}
