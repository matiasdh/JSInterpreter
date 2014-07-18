package com.language;

import java.util.ArrayList;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EmptyExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.Identifier;
import com.language.model.expression.PrimaryExpression;
import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.NullLiteral;
import com.language.model.literals.StringLiteral;
import com.language.model.util.CurrentState;
import com.language.parser.ExpressionParser;

public class Main {

	public static void main(String[] args) throws Exception {

		if (args.length==0) {
			System.out.println("You must pass a file to parse");
			return;
		}

		//create the general scope
		CurrentState instance = CurrentState.getInstance();
		
		//create array to pass add to the scope with the params x y z 
		ArrayLiteral arrayParam = new ArrayLiteral();
		ArrayList<AssignmentExpression> params = new ArrayList<AssignmentExpression>();

		for (int i = 1; i < args.length; i++) {
			params.add(new PrimaryExpression(new StringLiteral(args[i])));
		}
	
		arrayParam.addAll(params);
		
		//push into the scope the params 
		instance.push(new Identifier("$ARG"), arrayParam);
		
		//parse the file
		EvaluatableExpression expression;
		try {
			expression = ExpressionParser.parseFile(args[0]);
		} catch (Exception e) {
			if (e.getCause() == null) {
				System.out.println(e.getMessage());
			}
			else {
				System.out.println(e.getCause().getMessage());
			}
			return;
		}
		EvaluatableExpression eval = expression.getValue();
		if ((eval!=null) && !(eval instanceof NullLiteral) && !(eval instanceof EmptyExpression)) {
			System.out.println(eval);
		}
	}
}
