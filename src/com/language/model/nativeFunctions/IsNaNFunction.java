package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.BooleanLiteral;
import com.language.model.literals.NumericLiteral;

public class IsNaNFunction extends NativeFunctionExpression {

	public IsNaNFunction() {
		functionName = "isNaN";
	}
	
	@Override
	public EvaluatableExpression call() {
		return new BooleanLiteral(true);
	}
	
	@Override
	public EvaluatableExpression call(ArrayList<AssignmentExpression> arguments) throws Exception {
		NumericLiteral literal = arguments.isEmpty() ? null : arguments.get(0).toPrimitive().toNumeric();
		return new BooleanLiteral(literal == null || literal.isNaN());
	}
}
