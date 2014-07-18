package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.PrimaryExpression;
import com.language.model.literals.ArrayLiteral;

public class ConcatArrayLiteralFunction extends NativeFunctionExpression {
	
	private ArrayLiteral value;

	public ConcatArrayLiteralFunction(ArrayLiteral value) {
		functionName = "concat";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		return value;
	}
	
	@Override
	public EvaluatableExpression call( ArrayList<AssignmentExpression> arguments) throws Exception {
		if (arguments.isEmpty()) {
			return call();
		}
		EvaluatableExpression toConcat;
		for (AssignmentExpression expr : arguments) {
			toConcat = expr.getValue();
			if (toConcat instanceof ArrayLiteral) {
				value.addAll((ArrayLiteral) toConcat);
				continue;
			}
			value.push(new PrimaryExpression(expr.getValue()));
		}
		return value;
	}
}
