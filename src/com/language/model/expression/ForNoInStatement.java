package com.language.model.expression;

import java.rmi.UnexpectedException;
import java.util.ArrayList;

import com.language.model.exceptions.UnexpectedStatement;
import com.language.model.expression.EmptyExpression;
import com.language.model.literals.Literal;
import com.language.model.util.CurrentState;
import com.language.model.util.ExpressionType;

public class ForNoInStatement extends IterationStatement {
	

	private Expression expressionNoIn;
	
	public ForNoInStatement(Expression expressionNoIn , Expression expression1, Expression expression2, Statement statement) {
		super(expression1, expression2, statement);
		this.expressionNoIn = expressionNoIn;
	}
	
	@Override
	public EvaluatableExpression getValue() throws Exception {
		boolean forFlag = CurrentState.getInstance().getFunctionFlag();
		boolean functionFlag = CurrentState.getInstance().getFunctionFlag();
		if (!CurrentState.getInstance().getForFlag()) {
			CurrentState.getInstance().setForFlag(true);
		}
		if (expressionNoIn!=null) {
			expressionNoIn.getValue();
		}
		while(true) {
			if (expression1!=null) {
				if (!expression1.toBoolean()) {
						break;
				}
			} else {
				break;
			}
			EvaluatableExpression eval = statement.getValue();
			if (eval instanceof EmptyExpression) {
				EmptyExpression em = (EmptyExpression)eval;
				if (em.getType()==ExpressionType.BREAK) {
					break;
				} else if (em.getType()==ExpressionType.CONTINE) {
					if (expression2!=null) {
						expression2.getValue();
					}
					continue;
				} else if (em.getType()==ExpressionType.RETURN) {
					if (functionFlag) {
						return em.getValue();
					} 
					throw new UnexpectedStatement("return");
				}
			}
			if (expression2!=null) {
				expression2.getValue();
			}
		}
		CurrentState.getInstance().setForFlag(forFlag);
		return new EmptyExpression();

	}

}
