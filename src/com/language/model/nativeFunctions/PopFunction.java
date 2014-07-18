package com.language.model.nativeFunctions;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.StringLiteral;

public class PopFunction extends NativeFunctionExpression {
	
	private ArrayLiteral value;

	public PopFunction(ArrayLiteral value) {
		functionName = "pop";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		return value.pop();
	}
}
