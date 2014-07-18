package com.language.model.expression;
import com.language.model.exceptions.*;
import com.language.model.util.CurrentState;
import com.language.model.util.ExpressionType;
import com.language.model.util.ListUtil;

public class BlockStatement extends Statement {
	
	private ListUtil<Statement> list;
	
	public BlockStatement(ListUtil<Statement> list) {
		this.list = list;
	}
	
	@Override
	public EvaluatableExpression getValue() throws Exception {
		if (list.size()==0) {
			return new EmptyExpression();
		}
		boolean forFlag = CurrentState.getInstance().getForFlag();
		boolean functionFlag = CurrentState.getInstance().getFunctionFlag();
		EvaluatableExpression result = new EmptyStatement();
		for (Statement s : list.getList()) {
			EvaluatableExpression value = s.getValue();
			if (s instanceof ReturnStatement) {
				if (functionFlag) {
					return value;
				} else {
					throw new UnexpectedStatement("return");
				}
			}
			if (s instanceof BreakStatement) {
				if (forFlag) {
					return value;
				} 
				throw new UnexpectedStatement("break");
			}
			if (s instanceof ContinueStatement) {
				if (forFlag) {
					return value;
				} 
				throw new UnexpectedStatement("continue");
			}
			if (value instanceof EmptyExpression) {
				ExpressionType t = ((EmptyExpression)value).getType();
				if (t == ExpressionType.RETURN) {
					if (functionFlag) {
						return value;
					}
					throw new UnexpectedStatement("return");
				}
				if (t == ExpressionType.BREAK) {
					if (forFlag) {
						return value;
					}
					throw new UnexpectedStatement("break");
				}
				if (t == ExpressionType.CONTINE) {
					if (forFlag) {
						return value;
					}
					throw new UnexpectedStatement("continue");
				}
				if (t == ExpressionType.EMPTY) {
					continue;
				}
			}
			result = value;
		}
		return result;
	}

}

