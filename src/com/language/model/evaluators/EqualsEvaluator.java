package com.language.model.evaluators;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.BooleanLiteral;
import com.language.model.literals.Literal;

public class EqualsEvaluator implements EvaluatorInterface {

	@Override
	public Literal evaluateBinary(EvaluatableExpression left, EvaluatableExpression right) throws Exception {
		Boolean result = Utils.equals(right, left);
		return new BooleanLiteral((result != null) && result);
	}
}