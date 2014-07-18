package com.language.model.evaluators;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.NumericLiteral;

public class TimesEvaluator implements EvaluatorInterface {

	@Override
	public NumericLiteral evaluateBinary(EvaluatableExpression left, EvaluatableExpression right) throws Exception {
		NumericLiteral leftNumeric, rightNumeric;
		leftNumeric = left.toPrimitive().toNumeric();
		rightNumeric = right.toPrimitive().toNumeric();
		NumericLiteral res = new NumericLiteral(leftNumeric);
		res.multiply(rightNumeric);
		return res;
	}
}
