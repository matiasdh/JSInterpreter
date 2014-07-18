package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.NumericLiteral;

public class ParseFunction extends NativeFunctionExpression {

	public ParseFunction() {
		functionName = "parse";
	}
	
	@Override
	public EvaluatableExpression call() {
		return new NumericLiteral(0);
	}
	
	@Override
	public EvaluatableExpression call( ArrayList<AssignmentExpression> arguments) throws Exception {
		NumericLiteral literal =
				(NumericLiteral) (arguments.isEmpty() ? call() : arguments.get(0).toPrimitive().toNumeric());
		return literal;
	}
}
