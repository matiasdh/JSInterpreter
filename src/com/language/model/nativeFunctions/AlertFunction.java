package com.language.model.nativeFunctions;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.Literal;
import com.language.model.literals.NullLiteral;

public class AlertFunction extends NativeFunctionExpression {
	
	private ArrayLiteral value;

	public AlertFunction() {
		functionName = "alert";
	}
	
	@Override
	public EvaluatableExpression call() {
		JOptionPane.showMessageDialog(null, "");
		return new NullLiteral();
	}
	
	@Override
	public EvaluatableExpression call( ArrayList<AssignmentExpression> arguments) throws Exception {
		Literal literal = arguments.isEmpty() ? null : arguments.get(0).toPrimitive();
		JOptionPane.showMessageDialog(null, literal.toString());
		return new NullLiteral();
	}
}
