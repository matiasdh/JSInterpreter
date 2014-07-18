package com.language.model.expression;

import java.util.ArrayList;

import com.language.model.exceptions.UnexpectedStatement;
import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;
import com.language.model.literals.StringLiteral;
import com.language.model.util.CurrentState;
import com.language.model.util.ExpressionType;
import com.language.model.util.ListUtil;

public class FunctionExpression extends SourceElement {

	private Identifier id;
	private ListUtil<Identifier> params;
	private ListUtil<SourceElement> body;
	
	protected FunctionExpression() {}

	public FunctionExpression(Identifier id, ListUtil<Identifier> params, ListUtil<SourceElement> body) {
		this.id = id;
		this.params = params;
		this.body = body;
		CurrentState.getInstance().forcePush(id, this);
	}
	
	public FunctionExpression(ListUtil<Identifier> params, ListUtil<SourceElement> body) {
		this.params = params;
		this.body = body;
	}
	
	@Override
	public EvaluatableExpression call(ArrayList<AssignmentExpression> arguments) throws Exception {
		//TODO: pass list of assignmentExpression and add that variables to the scope
		//checking that the identifier are correct
		
		// Initialize scope
		ArrayList<EvaluatableExpression> evaluatedExpressions = new ArrayList<EvaluatableExpression>();
		for (int i = 0; i < params.size(); i++) {
			EvaluatableExpression varValue = (i < arguments.size()) ? arguments.get(i).getValue() : new NullLiteral();
			evaluatedExpressions.add(varValue);
		}
		
		CurrentState instance = CurrentState.getInstance();
		instance.newScope();
		Boolean beforeFunctionFlag = instance.getFunctionFlag();
		instance.setFunctionFlag(true);
		EvaluatableExpression result = null;
		EvaluatableExpression auxIt = null;
		
		// Initialize scope
		ArrayList<Identifier> paramsList = params.getList();
		for (int i = 0; i < paramsList.size(); i++) {
			Identifier id = paramsList.get(i);
			instance.forcePush(id, evaluatedExpressions.get(i));
		}
		for (SourceElement element : body.getList()) {
			auxIt = evaluate(element, true, instance.getForFlag());
			if (auxIt instanceof EmptyExpression) {
				EmptyExpression emptyValue = (EmptyExpression) auxIt;
				if (emptyValue.getType() == ExpressionType.RETURN) {
					result = emptyValue.getValue();
					break;
				}
			}
		}
		instance.pop();
		instance.setFunctionFlag(beforeFunctionFlag);
		return (result == null) ? new NullLiteral() : result;
	}
	
	@Override
	public Literal toPrimitive() throws Exception {
		return new StringLiteral("function () {}");
	}
	
	@Override
	public EvaluatableExpression getValue() throws Exception {
		return this;
	}	

	private EvaluatableExpression evaluate(SourceElement element,
		boolean functionFlag, boolean forFlag) throws Exception {
		EvaluatableExpression em = element.getValue();
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
