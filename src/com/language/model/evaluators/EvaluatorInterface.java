package com.language.model.evaluators;

import com.language.model.expression.EvaluatableExpression;

public interface EvaluatorInterface {
	
	public EvaluatableExpression evaluateBinary(EvaluatableExpression left, EvaluatableExpression right) throws Exception ;
}
