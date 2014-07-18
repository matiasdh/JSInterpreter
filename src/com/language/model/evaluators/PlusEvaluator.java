package com.language.model.evaluators;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.Literal;
import com.language.model.literals.NumericLiteral;
import com.language.model.literals.StringLiteral;

public class PlusEvaluator implements EvaluatorInterface {
	
	//	Let lref be the result of evaluating LogicalANDExpression.
	//	Let lval be GetValue(lref).
	//	If ToBoolean(lval) is false, return lval.
	//	Let rref be the result of evaluating BitwiseORExpression.
	//	Return GetValue(rref).

	@Override
	public Literal evaluateBinary(EvaluatableExpression left, EvaluatableExpression right) throws Exception  {
		Literal leftLiteral, rightLiteral;
		leftLiteral = left.toPrimitive();
		rightLiteral = right.toPrimitive();
		if ((leftLiteral instanceof StringLiteral) || (rightLiteral instanceof StringLiteral)) {
			return new StringLiteral(leftLiteral.toString() + rightLiteral.toString());
		}
		NumericLiteral leftNumeric = leftLiteral.toNumeric();
		NumericLiteral res = new NumericLiteral(leftNumeric);
		res.add(rightLiteral.toNumeric());
		return res;
	}
}
