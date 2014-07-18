package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.ArrayLiteral;

public class JoinFunction extends NativeFunctionExpression {
	
	private ArrayLiteral value;

	public JoinFunction(ArrayLiteral value) {
		functionName = "join";
		this.value = value;
	}
	
	@Override
	public EvaluatableExpression call() {
		return value.join(",");
	}
	
	@Override
	public EvaluatableExpression call( ArrayList<AssignmentExpression> arguments) throws Exception {
		String delimeter = arguments.isEmpty() ? "," : arguments.get(0).toPrimitive().toJSString().toString();
		EvaluatableExpression result = value.join(delimeter);
		return result;
	}
}
