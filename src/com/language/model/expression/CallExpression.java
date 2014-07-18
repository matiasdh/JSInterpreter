package com.language.model.expression;

import java.util.ArrayList;
import java.util.Iterator;

import com.language.model.literals.Literal;
import com.language.model.util.CurrentState;

public class CallExpression extends LeftHandSideExpression {
	
	private LeftHandSideExpression value;
	private ArrayList<AssignmentExpression> arguments;
	private EvaluatableExpression argumentExpression;
	
	public CallExpression(LeftHandSideExpression value, EvaluatableExpression argumentExpression) {
		this.value = value;
		this.arguments = new ArrayList<AssignmentExpression>();
		this.argumentExpression = argumentExpression;
	}

	public CallExpression(LeftHandSideExpression value, ArrayList<AssignmentExpression> arguments) {
		this.value = value;
		this.arguments = arguments;
		this.argumentExpression = null;
	}

	@Override
	public Literal toPrimitive() throws Exception {
		return getValue().toPrimitive();
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		EvaluatableExpression evaluatedValue = value.getValue();
		if (evaluatedValue instanceof Identifier) {
			evaluatedValue = evaluatedValue.getValue();
		}
		if (argumentExpression == null) {
			if (evaluatedValue instanceof FunctionExpression) {
				EvaluatableExpression result = ((FunctionExpression) evaluatedValue).call(arguments);
				return result;
			}
			return evaluatedValue.getValue();
		}
		else {
			String propertyName = argumentExpression.getValue().toPrimitive().toJSString().toString();
			return evaluatedValue.getValue().getProperty(propertyName);
		}
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
	public void putValue(EvaluatableExpression rightval) {
		
		
	}

	@Override
	public void setPutable(boolean value) {
		// Stub method, this class is always putable
	}
	
//	@Override
//	public EvaluatableExpression getValue() {
//		if (property == null) {
//			return value.getValue();
//		}
//		String propertyName = property.toPrimitive().toString();
//		return value.getValue().getProperty(propertyName);
//	}
}