package com.language.model.expression;

import com.language.model.exceptions.UnexpectedStatement;
import com.language.model.expression.EmptyExpression;
import com.language.model.literals.Literal;
import com.language.model.util.CurrentState;
import com.language.model.util.ExpressionType;

public class IfStatement extends Statement {

	private Expression expression;
	private Statement ifStatement;
	private Statement elseStatement;

	public IfStatement(Expression e, Statement s) {
		this.expression = e;
		this.ifStatement = s;
	}

	public IfStatement(Expression e, Statement s1, Statement s2) {
		this.expression = e;
		this.ifStatement = s1;
		this.elseStatement = s2;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		Literal exprRef = expression.toPrimitive();
		boolean functionFlag = CurrentState.getInstance().getFunctionFlag();
		boolean forFlag = CurrentState.getInstance().getForFlag();
		if (exprRef.toBoolean() == true) {
			return evaluate(ifStatement, functionFlag, forFlag);
		}
		if (elseStatement != null) {
			return evaluate(elseStatement, functionFlag, forFlag);
		}
		return new EmptyExpression();
	}

	private EvaluatableExpression evaluate(Statement statement,
			boolean functionFlag, boolean forFlag) throws Exception {
		EvaluatableExpression em = statement.getValue();
		if (em instanceof EmptyExpression) {
			ExpressionType t = ((EmptyExpression) em).getType();
			if (t == ExpressionType.RETURN) {
				if (functionFlag) {
					return em;
				}
				throw new UnexpectedStatement("return");
			}
			if (t == ExpressionType.BREAK) {
				if (forFlag) {
					return em;
				}
				throw new UnexpectedStatement("break");
			}
			if (t == ExpressionType.CONTINE) {
				if (forFlag) {
					return em;
				}
				throw new UnexpectedStatement("continue");
			}
		}
		return em;
	}

}
