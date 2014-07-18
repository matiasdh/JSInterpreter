package com.language.model.evaluators;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;
import com.language.model.literals.NumericLiteral;
import com.language.model.literals.StringLiteral;

public class Utils {

	public static Boolean equals(EvaluatableExpression left, EvaluatableExpression right) throws Exception {
		// Let px and py be the result of calling ToPrimitive.
		Literal px = left.toPrimitive();
		Literal py = right.toPrimitive();
		// If Type(x) is the same as Type(y), then
		if (left.getClass() == right.getClass()) {
			// If Type(x) is Null, return true.
			if (px instanceof NullLiteral) {
				return true;
			}
			//If Type(x) is Number, then
			else if (px instanceof NumericLiteral) {
				//If x or y are NaN, return false.
				if (px.isNaN() || py.isNaN()) {
					return false;
				}					
			}
			//If x is the same Number value as y, return true.
			//If Type(x) is Boolean, return true if x and y are both true or both false.
			if (left instanceof Literal) {
				return px.equals(py);
			}
			//TODO when parsing variables return true iff are instances of the same object
			return left == right;
		}
		else if ((px instanceof NullLiteral) || (py instanceof NullLiteral)) {
			return false;
		}
		else if (((px instanceof NumericLiteral) && px.isNaN()) ||
				((py instanceof NumericLiteral) && py.isNaN()))
		{
			return false;
		}
		// If Type(x) is Number and Type(y) is String, or Type(x) is String and Type(y) is Number
		// If Type(x) is Boolean, return the result of the comparison ToNumber(x) == y.
		// If Type(y) is Boolean, return the result of the comparison x == ToNumber(y).
		// return the result of the comparison ToNumber(x) == ToNumber(y).
		else if ((px.isNaN()) || (py.isNaN())) {
			return px.toString().equals((py.toString()));
		}
		return px.toNumeric().equals((py.toNumeric()));		
	}
	
	public static Boolean less(EvaluatableExpression left, EvaluatableExpression right) throws Exception {
		// Let px and py be the result of calling ToPrimitive(x, hint Number).
		// We convert into strings all types different than JSBoolean, JSNull, Numeric and JString
		Literal px = left.toPrimitive();
		Literal py = right.toPrimitive();
		// If it is the case that both Type(px) is String and Type(py) is String, then
		if ((px instanceof StringLiteral) && (py instanceof StringLiteral)) {
			return px.toString().compareTo(py.toString()) < 0;
		}
		// If it is not the case that both Type(px) is String and Type(py) is String, then
		NumericLiteral npx = px.toNumeric();
		NumericLiteral npy = py.toNumeric();
		// If npx or npy is NaN, return undefined (In this case we decided to return null).
		if (npx.isNaN() || npy.isNaN()) {
			return null;
		}
		return npx.isLess(npy);
	}

}
