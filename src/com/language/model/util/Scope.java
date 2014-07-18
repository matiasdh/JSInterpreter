package com.language.model.util;

import java.util.Stack;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.Identifier;
import com.language.model.literals.NullLiteral;

public class Scope {

	private Stack<Pair<Identifier, EvaluatableExpression>> localStack;
	private int level;
	private int parent;
	
	public Scope() {
		level = 0;
		localStack = new Stack<Pair<Identifier,EvaluatableExpression>>();
	}
	
	public int getLevel() {
		return level;
	}
	
	public Scope(int parent) {
		localStack = new Stack<Pair<Identifier,EvaluatableExpression>>();
		this.level = parent +1;
		this.parent = parent;
	}

	boolean isGloblal(Identifier id) {
		return (level!=0 && CurrentState.getInstance().isGloblal(parent, id));
	}
	
	public void push(Identifier id, EvaluatableExpression value) {
		int i = isLocal(id);
		if (i!=-1) {
			localStack.set(i, new Pair<Identifier, EvaluatableExpression>(id, value));
			CurrentState.getInstance().update(level, this);
			return;
		}
		if (isGloblal(id)) {
			CurrentState.getInstance().push(parent, id, value);
			return;
		} 
		localStack.push(new Pair<Identifier, EvaluatableExpression>(id, value));
		CurrentState.getInstance().update(level, this);
	}
	
	public void forcePush(Identifier id, EvaluatableExpression value) {
		int i = isLocal(id);	
		if (i!=-1) {
			localStack.set(i, new Pair<Identifier, EvaluatableExpression>(id, value));
		} else {
			localStack.push(new Pair<Identifier, EvaluatableExpression>(id, value));
		}
		CurrentState.getInstance().update(level, this);
	}
	
	public EvaluatableExpression getValue(Identifier id) {
		int i = isLocal(id);
		if (i!=-1) {
			return localStack.get(i).getRigh();
		}
		if (level!=0) {
			return CurrentState.getInstance().getValue(parent, id);
		}
		return null;
	}
	
	public int isLocal(Identifier name) {
		int i=0;
		for (Pair<Identifier, EvaluatableExpression> element : localStack) {
			if (name.getName().equals(element.getLeft().getName())) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public void clear() {
		localStack.clear();
		CurrentState.getInstance().update(level, this);
	}

	public String printStack() {
		String res = "";
		for (Pair<Identifier, EvaluatableExpression> element: localStack) {
			res = res + element.getLeft().getName() + "=" + element.getRigh() + ",";
		}
		return res.substring(0, res.length() -1);
	}

}
