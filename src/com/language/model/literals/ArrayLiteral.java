package com.language.model.literals;

import java.util.ArrayList;
import java.util.Collections;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.PrimaryExpression;
import com.language.model.nativeFunctions.ConcatArrayLiteralFunction;
import com.language.model.nativeFunctions.JoinFunction;
import com.language.model.nativeFunctions.PopFunction;
import com.language.model.nativeFunctions.PushFunction;
import com.language.model.nativeFunctions.ReverseFunction;
import com.language.model.nativeFunctions.ShiftFunction;
import com.language.parser.NumericParser;


public class ArrayLiteral extends PrimaryExpression {
	
	private ArrayList<AssignmentExpression> value = new ArrayList<AssignmentExpression>();

	public ArrayLiteral() {
	}

	public void addAll(ArrayList<AssignmentExpression> array) {
		value.addAll(array);
	}	
	
	@Override
	public Literal toPrimitive() {
		String stringValue = toString();
		return new StringLiteral(stringValue);
	}
	
	@Override
	public boolean isCallable() {
		return true;
	}

	@Override
	public boolean isPutable() {
		return false;
	}

	public ArrayList<AssignmentExpression> getList() {
		return value;
	}

	public void add(AssignmentExpression value) {
		this.value.add(value);		
	}

	@Override
	public String toString() {
		return toString(",");
	}
	
	public String toString(String delimeter) {
		String result = "";
		try{
		Literal auxLiteral;
		for (AssignmentExpression exp : value) {
			auxLiteral = exp.toPrimitive();
			if (!(auxLiteral instanceof NullLiteral)) {
				result += auxLiteral.toString();
			}
			result += delimeter;
		}
		} catch(Exception ex) {
			
		}
		int finalLength = result.length() - delimeter.length();
		return result.isEmpty() ? result : result.substring(0, finalLength);
	}

	public void add(Literal value) {
		this.value.add(new PrimaryExpression(value));
	}

	@Override
	public boolean equals(Object obj) {
		try {
			if (!(obj instanceof ArrayLiteral)) {
				return false;
			}
			ArrayLiteral array = (ArrayLiteral) obj;
			int this_size = this.value.size();
			if (array.value.size() != this_size) {
				return false;
			}
			Literal left, right;
			for (int i = 0; i < this_size; i++) {
				left = this.value.get(i).toPrimitive();
				right = array.value.get(i).toPrimitive();
				if (!left.equals(right)) {
					return false;
				}
			}
		} catch (Exception ex) {
			
		}
		return true;
	}

	@Override
	public PrimaryExpression getValue() {
		return this;
	}
	
	@Override
	public Boolean toBoolean() {
		return true;
	}
	
	@Override
	public EvaluatableExpression getProperty(String propertyName) {
		if (propertyName.equals("length")) {
			return new NumericLiteral(value.size());
		}
		if (propertyName.equals("pop")) {
			return new PopFunction(this);
		}
		if (propertyName.equals("shift")) {
			return new ShiftFunction(this);
		}
		if (propertyName.equals("join")) {
			return new JoinFunction(this);
		}
		if (propertyName.equals("reverse")) {
			return new ReverseFunction(this);
		}
		if (propertyName.equals("push")) {
			return new PushFunction(this);
		}
		if (propertyName.equals("concat")) {
			return new ConcatArrayLiteralFunction(this);
		}
		Boolean isNumeric = NumericParser.isNumeric(propertyName);
		if (isNumeric) {
			int index = NumericParser.decodeString(propertyName).toDouble().intValue();
			if ((index < 0) || (index >= value.size())) {
				return new NullLiteral();
			}
			AssignmentExpression elementAt = value.get(index);
			elementAt.setPutable(true);
			return elementAt;			
		}
		return super.getProperty(propertyName);
	}

	public AssignmentExpression pop() {
		int lastIndex = value.size() - 1;
		if (lastIndex < 0) {
			return new PrimaryExpression(new NullLiteral());
		}
		AssignmentExpression returnValue = value.get(lastIndex);
		value.remove(lastIndex);
		return returnValue;
		
	}

	public EvaluatableExpression shift() {
		int firstIndex = 0;
		if (value.size() == 0) {
			return new PrimaryExpression(new NullLiteral());
		}
		AssignmentExpression returnValue = value.get(firstIndex);
		value.remove(firstIndex);
		return returnValue;
	}

	public StringLiteral join(String string) {
		return new StringLiteral(toString(string));
	}

	public void reverse() {
		Collections.reverse(value);
	}

	public void push(AssignmentExpression toPush) {
		value.add(toPush);
	}

	public void addAll(ArrayLiteral array) {
		addAll(array.value);
	}
}
