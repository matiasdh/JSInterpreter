package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.literals.StringLiteral;

public class SubstringFunction extends NativeFunctionExpression {
	
	private String value;

	public SubstringFunction(String value) {
		functionName = "substring";
		this.value = value;
	}
	
	@Override
	public StringLiteral call(ArrayList<AssignmentExpression> arguments) {
		int stringLength = value.length();
		if (arguments.isEmpty() || (stringLength == 0)) {
			return new StringLiteral(value);
		}
		int startIndex = 0;
		try {
			startIndex = arguments.get(0).toPrimitive().toNumeric().toDouble().intValue();
		} catch (Exception e) {
		}
		startIndex = (startIndex < stringLength) ? startIndex : stringLength;
		if (arguments.size() == 1) {
			return new StringLiteral(value.substring(startIndex));
		}
		int endIndex = 0;
		try {
			endIndex = arguments.get(1).toPrimitive().toNumeric().toDouble().intValue();
		} catch (Exception e) {
		}
		endIndex = (endIndex < stringLength) ? endIndex : stringLength;
		return new StringLiteral(value.substring(startIndex, endIndex));
	}
}
