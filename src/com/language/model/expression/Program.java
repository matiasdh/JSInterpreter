package com.language.model.expression;

import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;
import com.language.model.util.CurrentState;
import com.language.model.util.ListUtil;

public class Program extends EvaluatableExpression {
	
	private ListUtil<SourceElement> list;
	
	public Program(ListUtil<SourceElement> list) {
		CurrentState.getInstance();
		this.list = list;
	}

	@Override
	public Literal toPrimitive() throws Exception {
		EvaluatableExpression res = getValue(); 
		if (res!=null) {
			return res.toPrimitive();
		}
		return new NullLiteral();
	}

	@Override
	public EvaluatableExpression getValue() throws Exception {
		EvaluatableExpression res = new EmptyExpression();
		for (SourceElement s: list.getList()) {
			EvaluatableExpression val = s.getValue();
			res = val;
		}
		return res;
	}
	
	
}
