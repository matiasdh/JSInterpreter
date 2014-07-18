package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.PrimaryExpression;
import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.StringLiteral;

public class ConcatStringLiteralFunction extends NativeFunctionExpression {
	
	private StringLiteral value;

	public ConcatStringLiteralFunction(StringLiteral value) {
		functionName = "concat";
		this.value = value;
	}
	
	@Override
	public StringLiteral call() {
		return value;
	}
	
	@Override
	public StringLiteral call( ArrayList<AssignmentExpression> arguments) throws Exception {
		if (arguments.isEmpty()) {
			return call();
		}
		String parameter = arguments.get(0).toPrimitive().toString();
		return value.concatAndUpdate(parameter);
	}
}
