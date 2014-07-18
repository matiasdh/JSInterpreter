package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.util.CurrentState;

public class VariableDeclarationNoIn extends EvaluatableExpression {
	
	private Identifier id;
	private Initialiser in = null;
	
	public VariableDeclarationNoIn(Identifier id, Initialiser in) {
		this.id = id;
		this.in = in;
	}

	public VariableDeclarationNoIn(Identifier id) {
		this.id = id;
	}

	public Identifier getIdentifier() {
		return id;
	}
	
	@Override
	public Literal toPrimitive() throws Exception {
		EvaluatableExpression res = getValue();
		if(res!=null) {
			return res.toPrimitive();
		}
		return null;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		if (in!=null) {
			return in.getValue();	
		}
		return null;
	}
}
