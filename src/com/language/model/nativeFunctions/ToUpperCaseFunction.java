package com.language.model.nativeFunctions;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.StringLiteral;

public class ToUpperCaseFunction extends NativeFunctionExpression {
	
	private String value;

	public ToUpperCaseFunction(String value) {
		functionName = "toUpperCase";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		return new StringLiteral(value.toUpperCase());
	}
	
}
