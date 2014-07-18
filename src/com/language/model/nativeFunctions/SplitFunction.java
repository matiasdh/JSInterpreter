package com.language.model.nativeFunctions;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.StringLiteral;

public class SplitFunction extends NativeFunctionExpression {
	
	private String value;

	public SplitFunction(String value) {
		functionName = "split";
		this.value = value;
	}
	
	@Override
	public ArrayLiteral call(ArrayList<AssignmentExpression> arguments) {
		ArrayLiteral result = new ArrayLiteral();
		if (arguments.isEmpty()) {
			result.add(new StringLiteral(value));
			return result;
		}
		String string;
		try {
			 string = arguments.get(0).toPrimitive().toString();
		} catch (Exception e) {
			string = "";
		}
		String[] raw_result = value.split(string);
		int startAt = (string.equals("")) ? 1 : 0;
		for (int i = startAt; i < raw_result.length; i++) {
			result.add(new StringLiteral(raw_result[i]));
		}
		return result;
	}
}
