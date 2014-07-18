package com.language.model.nativeFunctions;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.StringLiteral;

public class ReverseFunction extends NativeFunctionExpression {
	
	private ArrayLiteral value;

	public ReverseFunction(ArrayLiteral value) {
		functionName = "reverse";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		value.reverse();
		return value;
	}
}
