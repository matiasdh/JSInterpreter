package com.language.model.expression;

import com.language.model.literals.BooleanLiteral;
import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;
import com.language.model.literals.NumericLiteral;
import com.language.model.util.CurrentState;
import com.language.model.util.Operator;

public class UnaryExpression extends AssignmentExpression {
	
	private EvaluatableExpression value;
	private boolean markedAsPutable;
	
	protected UnaryExpression() {}
	
	public UnaryExpression(UnaryExpression value, Operator operator) {
		this.operator = operator;
		this.value = value;
		this.markedAsPutable = false;
	}
	
	public UnaryExpression(PostfixExpression value) {
		super.operator = null;
		this.value = value;
		this.markedAsPutable = false;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		if (value instanceof PostfixExpression) {
			return ((PostfixExpression) value).getValue();
		}
		else if (value instanceof UnaryExpression) {
			EvaluatableExpression evaluatedThis = ((UnaryExpression) value).getValue();
			switch (operator) {
			case PLUS:
				return ((Literal) evaluatedThis).toNumeric();
			case MINUS:
				NumericLiteral numericResult = ((Literal) evaluatedThis).toNumeric();
				numericResult.multiply(new NumericLiteral(-1));
				return numericResult;
			case NOT:
				Boolean result = evaluatedThis.getValue().toBoolean();
				return new BooleanLiteral(!(result !=null && result));
			default:
				return evaluatedThis;			
			}
		}
		return null;
	}

	@Override
	public Literal toPrimitive() throws Exception {
		return getValue().toPrimitive();
	}
	
	@Override
	public boolean isPutable() {
		return markedAsPutable || value.isPutable();
	}
	
	@Override
	public void setPutable(boolean b) {
		this.markedAsPutable = true;
	}
	
	public void putValue(EvaluatableExpression value) {
		if (!(this.value instanceof UnaryExpression)) {
			this.value = new PrimaryExpression(value);
		} else {
			this.value.putValue(value);
		}
	};
}
