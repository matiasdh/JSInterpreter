package com.language.model.expression;

import com.language.model.expression.EmptyExpression;
import com.language.model.literals.Literal;
import com.language.model.literals.StringLiteral;
import com.language.model.util.ExpressionType;

public class ReturnStatement extends Statement {
	
	private Expression expression;
	
	public ReturnStatement() {
	}

	public ReturnStatement(Expression expression) {
		this.expression = expression;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		if (expression==null) {
			return new EmptyExpression(ExpressionType.RETURN);
		}
		EvaluatableExpression evaluatedResult = expression.getValue();
		if (evaluatedResult instanceof Identifier) {
			evaluatedResult = evaluatedResult.getValue();
		}
		return new EmptyExpression(ExpressionType.RETURN, evaluatedResult);
	}

}
