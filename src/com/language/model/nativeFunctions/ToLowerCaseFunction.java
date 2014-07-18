package com.language.model.nativeFunctions;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.StringLiteral;

public class ToLowerCaseFunction extends NativeFunctionExpression {
	
	private String value;

	public ToLowerCaseFunction(String value) {
		functionName = "toLowerCase";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		return new StringLiteral(value.toLowerCase());
	}
}
