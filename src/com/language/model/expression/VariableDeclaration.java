package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.util.CurrentState;

public class VariableDeclaration extends EvaluatableExpression {
	
	private Identifier id;
	private Initialiser in = null;
	
	public VariableDeclaration(Identifier id, Initialiser in) {
		this.id = id;
		this.in = in;
	}

	public VariableDeclaration(Identifier id) {
		this.id = id;
	}
	
	public Identifier getIdentifier() {
		return id;
	}

	@Override
	public Literal toPrimitive() throws Exception {
		EvaluatableExpression res = getValue();
		if (res!=null) {
			return res.toPrimitive();
		}
		return null;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		EvaluatableExpression res = null;
		if (in!=null) {
			res = in.getValue();
			CurrentState.getInstance().forcePush(id, res);
		} else {
			CurrentState.getInstance().forcePush(id, null);
		}
		return res;
	}
	
}
