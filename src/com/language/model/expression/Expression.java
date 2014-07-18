package com.language.model.expression;

import com.language.model.literals.Literal;

public class Expression extends EvaluatableExpression {
	private Expression left;
	private AssignmentExpression right;
	
	public Expression(){
		
	}

	public Expression(AssignmentExpression value) {
		this.left = null;
		this.right = value;
	}
	
	public Expression(Expression left, AssignmentExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Literal toPrimitive() throws Exception {
		return right.toPrimitive();
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		if (left!=null) {
			left.getValue();	
		}
		return right.getValue();
	}
}
