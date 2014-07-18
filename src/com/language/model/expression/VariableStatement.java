package com.language.model.expression;

import com.language.model.expression.EmptyExpression;
import com.language.model.util.ListUtil;

public class VariableStatement extends Statement {
	
	ListUtil<VariableDeclaration> list;

	public VariableStatement(ListUtil<VariableDeclaration> list) {
		this.list = list;
	}

	@Override
	public EvaluatableExpression getValue () throws Exception {
		EvaluatableExpression res = new EmptyExpression();
		for (VariableDeclaration v: list.getList()) {
			EvaluatableExpression val = v.getValue();
			if (res!=null){
				res = val;
			}
		}
		return res;
	}
}
