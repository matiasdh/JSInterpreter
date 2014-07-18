package com.language.model.nativeFunctions;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.StringLiteral;

public class ShiftFunction extends NativeFunctionExpression {
	
	private ArrayLiteral value;

	public ShiftFunction(ArrayLiteral value) {
		functionName = "shift";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		return value.shift();
	}
}
