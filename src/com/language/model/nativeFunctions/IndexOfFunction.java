package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.literals.NumericLiteral;

public class IndexOfFunction extends NativeFunctionExpression {
	
	private String value;

	public IndexOfFunction(String value) {
		functionName = "indexOf";
		this.value = value;
	}
	
	@Override
	public NumericLiteral call(ArrayList<AssignmentExpression> arguments) {
		String string = "";
		if (arguments.isEmpty()) {
			return new NumericLiteral(-1);
		}
		try {
			string = arguments.get(0).toPrimitive().toString();
		} catch (Exception e) {
		}
		
		return new NumericLiteral(value.indexOf(string));
	}
}
