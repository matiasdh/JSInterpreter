package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.util.CurrentState;

public class MemberExpression extends LeftHandSideExpression {
	
	private EvaluatableExpression value;
	EvaluatableExpression property;
	
	protected MemberExpression() {
	}

	public MemberExpression(PrimaryExpression value) {
		this.value = value;
		this.property = null;
	}
	
	public MemberExpression(FunctionExpression value) {
		this.value = value;
		this.property = null;
	}

	public MemberExpression(MemberExpression value, EvaluatableExpression property) {
		this.value = value;
		this.property = property;
	}

	@Override
	public Literal toPrimitive() throws Exception {
		return getValue().toPrimitive();
	}
	
	@Override
	public EvaluatableExpression getValue() throws Exception {
		if (value instanceof Identifier) {
			return value;
		}
		
		EvaluatableExpression evaluated = value.getValue();
		if (property == null) {
			return evaluated;
		}
		String propertyName = property.toPrimitive().toString();
		return evaluated.getValue().getProperty(propertyName);
	}
	
	@Override
	public boolean isPutable() {
		
		try {
			return getValue().isPutable();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void putValue(EvaluatableExpression value) {
		this.value = value;
	}
}
