package com.language.model.evaluators;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.Literal;

public class OrEvaluator implements EvaluatorInterface {

	@Override
	public EvaluatableExpression evaluateBinary(EvaluatableExpression left, EvaluatableExpression right) throws Exception  {
		Literal px = left.toPrimitive();
		Boolean pxToBoolean = px.toBoolean();
		if ((pxToBoolean != null) && pxToBoolean) {
			return left;
		}
		return right;
	}
}
