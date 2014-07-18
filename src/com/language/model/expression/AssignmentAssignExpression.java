package com.language.model.expression;

import com.language.model.exceptions.InvalidArgumentsInOperation;
import com.language.model.exceptions.ReferenceErrorException;
import com.language.model.literals.Literal;

public class AssignmentAssignExpression extends AssignmentExpression {
	private LeftHandSideExpression left;
	private AssignmentExpression right;
	
	public AssignmentAssignExpression(LeftHandSideExpression left, AssignmentExpression right) {
		this.left = left;
		this.right = right;
	}
	

	@Override
	public Literal toPrimitive() throws Exception{
		return right.toPrimitive();
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		EvaluatableExpression rightVal = right.getValue();
		EvaluatableExpression leftVal = left.getValue();
		if ((leftVal instanceof Literal) && (!(leftVal instanceof Literal))) {
			throw new ReferenceErrorException("Invalid left-hand side in assignment");
		}
		if (leftVal.isPutable()) {
			leftVal.putValue(rightVal);
		}
		// Second argument in the following boolean expression is because the variables
		// that start with var;
		if ((leftVal instanceof UnaryExpression) || (leftVal instanceof Identifier)) {
			return rightVal;
		}
		throw new Exception("ReferenceError: Invalid left-hand side in assignment");
	}
}
