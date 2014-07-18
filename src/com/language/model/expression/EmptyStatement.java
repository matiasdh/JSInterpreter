package com.language.model.expression;

import com.language.model.literals.NullLiteral;

public class EmptyStatement extends Statement  {
	
	
	public EmptyStatement() {
	}

	@Override
	public EvaluatableExpression getValue() {
		return new NullLiteral();
	}

}
