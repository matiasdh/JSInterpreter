package com.language.model.evaluators;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.Literal;

public class AndEvaluator implements EvaluatorInterface {
	
	//	Let lref be the result of evaluating LogicalANDExpression.
	//	Let lval be GetValue(lref).
	//	If ToBoolean(lval) is false, return lval.
	//	Let rref be the result of evaluating BitwiseORExpression.
	//	Return GetValue(rref).

	@Override
	public EvaluatableExpression evaluateBinary(EvaluatableExpression left, EvaluatableExpression right) throws Exception {
		Literal px = left.toPrimitive();
		Boolean pxToBoolean = px.toBoolean();
		if (!(pxToBoolean != null && pxToBoolean)) {
			return left;
		}
		return right;
	}
}
