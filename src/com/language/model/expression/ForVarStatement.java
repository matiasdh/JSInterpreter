package com.language.model.expression;

import java.rmi.UnexpectedException;
import java.util.ArrayList;

import com.language.model.util.CurrentState;
import com.language.model.util.ExpressionType;
import com.language.model.exceptions.UnexpectedStatement;
import com.language.model.expression.EmptyExpression;
import com.language.model.util.ListUtil;


public class ForVarStatement extends IterationStatement {
	
	private ListUtil<VariableDeclarationNoIn> list;

	public ForVarStatement(ListUtil<VariableDeclarationNoIn> list, Expression expression1, 
			Expression expression2, Statement statement) {
		super(expression1, expression2, statement);
		this.list = list;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		boolean forFlag = CurrentState.getInstance().getFunctionFlag();
		boolean functionFlag = CurrentState.getInstance().getFunctionFlag();
		if (!CurrentState.getInstance().getForFlag()) {
			CurrentState.getInstance().setForFlag(true);
		}
		
		ArrayList<VariableDeclarationNoIn> vars = list.getList();
		for (VariableDeclarationNoIn v: vars) {
			CurrentState.getInstance().forcePush(v.getIdentifier(), v.getValue());
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
