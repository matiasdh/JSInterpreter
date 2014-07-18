package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.PrimaryExpression;
import com.language.model.literals.ArrayLiteral;

public class PushFunction extends NativeFunctionExpression {
	
	private ArrayLiteral value;

	public PushFunction(ArrayLiteral value) {
		functionName = "push";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		return value;
	}
	
	@Override
	public EvaluatableExpression call( ArrayList<AssignmentExpression> arguments) {
		value.addAll(arguments);
		return value.getProperty("length");
	}
}
