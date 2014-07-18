package com.language.model.expression;

import com.language.model.evaluators.AndEvaluator;
import com.language.model.evaluators.DistinctEvaluator;
import com.language.model.evaluators.EqualsEvaluator;
import com.language.model.evaluators.EvaluatorInterface;
import com.language.model.evaluators.GreaterEvaluator;
import com.language.model.evaluators.GreaterOrEqualEvaluator;
import com.language.model.evaluators.LessEvaluator;
import com.language.model.evaluators.LessOrEqualEvaluator;
import com.language.model.evaluators.MinusEvaluator;
import com.language.model.evaluators.ObelusEvaluator;
import com.language.model.evaluators.OrEvaluator;
import com.language.model.evaluators.PlusEvaluator;
import com.language.model.evaluators.TimesEvaluator;
import com.language.model.literals.Literal;
import com.language.model.util.Operator;

public class BinaryExpression extends AssignmentExpression {
	private EvaluatableExpression left, right;

	protected BinaryExpression() {
	}

	public BinaryExpression(EvaluatableExpression left,
			EvaluatableExpression right, Operator operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	@Override
	public EvaluatableExpression getValue() throws Exception{
		EvaluatorInterface operatorEvaluator;
		switch (this.operator) {
		case EQUALS:
			operatorEvaluator = new EqualsEvaluator();
			break;
		case DISTINCT:
			operatorEvaluator = new DistinctEvaluator();
			break;
		case GREATER:
			operatorEvaluator = new GreaterEvaluator();
			break;
		case LESS:
			operatorEvaluator = new LessEvaluator();
			break;
		case LESS_OR_EQUAL:
			operatorEvaluator = new LessOrEqualEvaluator();
			break;
		case GREATER_OR_EQUAL:
			operatorEvaluator = new GreaterOrEqualEvaluator();
			break;
		case AND:
			operatorEvaluator = new AndEvaluator();
			break;
		case OR:
			operatorEvaluator = new OrEvaluator();
			break;
		case PLUS:
			operatorEvaluator = new PlusEvaluator();
			break;
		case MINUS:
			operatorEvaluator = new MinusEvaluator();
			break;
		case TIMES:
			operatorEvaluator = new TimesEvaluator();
			break;
		case OBELUS:
			operatorEvaluator = new ObelusEvaluator();
			break;
		default: 
			operatorEvaluator = new EqualsEvaluator();
			break;
		}
		return operatorEvaluator.evaluateBinary(this.left.getValue(), this.right.getValue());
	}

	@Override	 
	public Literal toPrimitive() throws Exception {
		return getValue().toPrimitive();
	}
}