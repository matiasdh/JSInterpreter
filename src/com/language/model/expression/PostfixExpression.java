package com.language.model.expression;

import com.language.model.exceptions.InvalidArgumentsInOperation;
import com.language.model.literals.Literal;
import com.language.model.literals.NumericLiteral;
import com.language.model.util.CurrentState;
import com.language.model.util.Operator;

public class PostfixExpression extends UnaryExpression {
	
	private LeftHandSideExpression value;

	public PostfixExpression(LeftHandSideExpression value) {
		this.value = value;
	}
	
	public PostfixExpression(LeftHandSideExpression value, Operator operator) {
		this.value = value;
		this.operator = operator;
	}
	
	@Override
	public Literal toPrimitive() throws Exception {
		return getValue().toPrimitive();
	}
	
	@Override
	public EvaluatableExpression getValue() throws Exception {
		EvaluatableExpression res = null;
		EvaluatableExpression eval = value.getValue();
		Boolean wasIdentifier = false;
		if (eval instanceof Identifier) {
			eval = eval.getValue();
			wasIdentifier = true;
		}
		if (this.operator == null) {
			return eval;
		}
		if (wasIdentifier) {
			NumericLiteral value = eval.toPrimitive().toNumeric();
			res = new NumericLiteral(value);
			if (res==NumericLiteral.getNaN()) {
				return res;
			}
			switch (operator) {
				case DOUBLE_PLUS:
					value.add(new NumericLiteral(1));
					break;
				case DOUBLE_MINUS:
					value.substract(new NumericLiteral(1));
					break;
				default:
					break;
			}
			return res;
		} 
		else{ //TODO: array access
			throw new InvalidArgumentsInOperation("Invalid left-hand side expression in postfix operation");
		}
	}

	@Override
	public boolean isPutable() {
		try {
			return value.isPutable();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void putValue(EvaluatableExpression value) {
		if (!(this.value instanceof LeftHandSideExpression)) {
			this.value = new PrimaryExpression(value);
		} else {
			this.value.putValue(value);
		}
	};
}
