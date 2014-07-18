package com.language.model.expression;

public abstract class IterationStatement extends Statement {
	
	protected Statement statement;
	protected Expression expression1;
	protected Expression expression2; 
	
	public IterationStatement(Expression expression1, Expression expression2, Statement statement) {
		this.statement = statement;	
		this.expression1 = expression1;
		this.expression2 = expression2;
	}
	
}
