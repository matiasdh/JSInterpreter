package com.language.model.literals;

public class NumericLiteral extends Literal {
	
	private Double value;
	private static NumericLiteral NaNValue = new NumericLiteral(Double.NaN);
	
	public static NumericLiteral getNaN() {
		return NaNValue;
	}
	
	public static NumericLiteral getPositiveInfinity() {
		NumericLiteral return_value = new NumericLiteral();
		return_value.value = Double.POSITIVE_INFINITY;
		return return_value;
	}

	public static NumericLiteral getNegativeInfinity() {
		NumericLiteral return_value = new NumericLiteral();
		return_value.value = Double.NEGATIVE_INFINITY;
		return return_value;
	}

	// Constructors
	public NumericLiteral(NumericLiteral num) {
		this.value = num.value;
	}

	public NumericLiteral(Double num) {
		this.value = num;
	}

	public NumericLiteral() {
		this.value = null;
	}

	public NumericLiteral(int num) {
		this.value = (double) num;
	}
	
	// Class Methods
	public Literal getValue() {
		return this;
	}
	public void add(NumericLiteral value) {
		this.value += value.value;
	}
	
	public void substract(NumericLiteral value) {
		this.value -= value.value;
	}
	
	public void multiply(NumericLiteral value) {
		this.value *= value.value;
	}

	public void divide(NumericLiteral value) {
		this.value /= value.value;
	}
	
	public NumericLiteral increment() {
		this.value += 1;
		return this;
	}
	
	public NumericLiteral decrement() {
		this.value -= 1;
		return this;
	}
	
	public String toString() {
		if (value.intValue() == value){
			Integer int_value = value.intValue();
			return int_value.toString();
		}
		else {
			return value.toString();
		}
	}
	
	public boolean equals(Object obj) {		
		if (obj instanceof NumericLiteral) {
			return (this.value.equals(((NumericLiteral) obj).value));
		}
		else if (obj instanceof Double) {
			return (this.value.equals(((Double) obj)));
		}
		else if (obj instanceof Integer) {
			return ((this.value.intValue() == this.value) && (this.value.intValue() == (Integer)obj));
		}
		else {
			return false;
		}
	}
	
	public boolean isGreater(Object obj) {
		if (obj instanceof NumericLiteral) {
			return this.value > ((NumericLiteral) obj).value;
		}
		if (obj instanceof Double) {
			return (this.value > ((Double) obj));
		}
		if (obj instanceof Integer) {
			return this.value.intValue() > (Integer)obj;
		}
		return false;
	}
	
	public boolean isLess(Object obj) {
		if (obj instanceof NumericLiteral) {
			return this.value < ((NumericLiteral) obj).value;
		}
		if (obj instanceof Double) {
			return (this.value < ((Double) obj));
		}
		if (obj instanceof Integer) {
			return this.value.intValue() < (Integer)obj;
		}
		return false;
	}

	@Override
	public Boolean toBoolean() {
		return (value != 0) && (!value.isNaN());
	}

	@Override
	public NumericLiteral toNumeric() {
		return this;
	}

	@Override
	public StringLiteral toJSString() {
		return new StringLiteral(value.toString());
	}

	@Override
	public boolean isNaN() {
		return value.isNaN();
	}

	@Override
	public Literal clone() {
		// TODO Auto-generated method stub
		return new NumericLiteral(this.value);
	}
	
	public Double toDouble() {
		return value;
	}
}
