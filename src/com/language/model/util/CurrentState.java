package com.language.model.util;

import java.util.Stack;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.Identifier;
import com.language.model.nativeFunctions.AlertFunction;
import com.language.model.nativeFunctions.IsNaNFunction;
import com.language.model.nativeFunctions.ParseFunction;

public class CurrentState {
	private static Stack<Scope> stack;
	private static CurrentState instance = null;
	private static boolean functionFlag;
	private static boolean forFlag;
	
	CurrentState() {
		functionFlag = false;
		forFlag = false;
		stack = new Stack<Scope>();
	}
	
	public static CurrentState getInstance() {
		if (instance==null) {
			instance = new CurrentState();
			instance.newScope();
			
			//function definitions
			instance.forcePush(new Identifier("isNaN"), new IsNaNFunction());
			instance.forcePush(new Identifier("parse"), new ParseFunction());
			instance.forcePush(new Identifier("alert"), new AlertFunction());
		}
		return instance;
	}
	
	public Scope newScope() {
		Scope scope;
		if (stack.isEmpty()) {
			scope = new Scope();
		} else {
			scope = new Scope(stack.peek().getLevel());
		}
		stack.push(scope);
		return scope;
	}
	
	public Scope pop() {
		return stack.pop();
	}
	
	public Scope peek() {
		return stack.peek();
	}
	
	public void update(int level, Scope s) {
		stack.set(level, s);
	}

	public boolean isGloblal(int parent, Identifier id) {
		Scope p = stack.get(parent);
		return p.isLocal(id)!=-1 || p.isGloblal(id);
	}

	public void push(int parent, Identifier id, EvaluatableExpression value) {
		stack.get(parent).push(id, value);
	}
	
	public void push(Identifier id, EvaluatableExpression value) {
		Scope top = stack.peek();
		top.push(id, value);
	}

	public void forcePush(Identifier id, EvaluatableExpression value) {
		Scope top = stack.peek();
		top.forcePush(id, value);
	}
	
	public void clear() {
		instance = null;
		stack.clear();
	}

	public EvaluatableExpression getValue(int parent, Identifier id) {
		return stack.get(parent).getValue(id);
	}
	
	public EvaluatableExpression getValue(Identifier id) {
		return stack.peek().getValue(id);
	}
	
	public boolean getFunctionFlag() {
		return functionFlag;
	}
	
	public boolean getForFlag() {
		return forFlag;
	}
	
	public void setFunctionFlag(boolean flag) {
		this.functionFlag = flag;
	}
	
	public void setForFlag(boolean flag) {
		this.forFlag = flag;
	}

}
