package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.FunctionExpression;
import com.language.model.literals.StringLiteral;

public class NativeFunctionExpression extends FunctionExpression {
	
	protected String functionName;
	
	@Override
	public StringLiteral toPrimitive() {
		return new StringLiteral("function "  + functionName + "() { [native code] }");
	}
	
	@Override
	public EvaluatableExpression call( ArrayList<AssignmentExpression> arguments) throws Exception {
		return call();
	}

}
