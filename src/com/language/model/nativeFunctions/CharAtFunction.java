package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.literals.StringLiteral;

public class CharAtFunction extends NativeFunctionExpression {
	
	private String value;

	public CharAtFunction(String value) {
		functionName = "chartAt";
		this.value = value;
	}
	
	@Override
	public StringLiteral call(ArrayList<AssignmentExpression> arguments) {
		int charIndex;
		int stringLength = value.length();
		if (arguments.isEmpty()) {
			charIndex = 0;
		}
		else {
			try {
				charIndex = arguments.get(0).toPrimitive().toNumeric().toDouble().intValue();
			} catch (Exception e) {
				charIndex = 0;
			}
		}
		charIndex = (charIndex < stringLength) ? charIndex : -1;
		charIndex = (charIndex < 0) ? -1 : charIndex;
		return (charIndex < 0) ? new StringLiteral("") : new StringLiteral(value.charAt(charIndex));
	}
}
