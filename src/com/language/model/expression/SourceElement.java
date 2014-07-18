package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;

public abstract class SourceElement extends EvaluatableExpression {
	
	@Override
	public Literal toPrimitive() throws Exception {
		EvaluatableExpression res = getValue();
		if(res!=null) {
			return res.toPrimitive();
		}
		return new NullLiteral();
	}

	public EvaluatableExpression call() throws Exception {
		return getValue();		
	}
	
}
