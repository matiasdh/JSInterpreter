package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;
import com.language.model.util.CurrentState;

public class Identifier extends PrimaryExpression {
	
	private String id;
	
	public Identifier(String id) {
		this.id = id;
	}
	
	public String getName() {
		return id;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception{
		return CurrentState.getInstance().getValue(this);
	}
	
	@Override
	public Literal toPrimitive() throws Exception {
		EvaluatableExpression val = getValue();
		if (val!=null) {
			return getValue().toPrimitive();
		}
		return new NullLiteral();
	}
	
	@Override
	public boolean isPutable() {
		return true;
	}
	
	@Override
	public void putValue(EvaluatableExpression value) {
		CurrentState.getInstance().push(this, value);
	}
}
