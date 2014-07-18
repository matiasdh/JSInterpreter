package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;


public abstract class Statement extends SourceElement {
	
	public Statement() {
	}
	
	@Override
	public Literal toPrimitive() throws Exception {
		EvaluatableExpression res = getValue();
		if (res!=null) {
			return res.toPrimitive();
		} else {
			return new NullLiteral();
		}
	}
	
}
