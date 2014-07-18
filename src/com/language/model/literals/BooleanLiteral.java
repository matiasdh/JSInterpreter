package com.language.model.literals;


public class BooleanLiteral extends Literal {
	
	private Boolean value;
	
	public BooleanLiteral(boolean value) {
		this.value = value;
	}

	@Override
	public Boolean toBoolean() {
		return value;
	}

	@Override
	public NumericLiteral toNumeric() {
		return (value) ? new NumericLiteral(1) : new NumericLiteral(0);
	}

	@Override
	public StringLiteral toJSString() {
		return new StringLiteral(value.toString());
	}

	@Override
	public boolean isNaN() {
		return false;
	}
	
	public String toString() {
		return value.toString();
	}
	
	public boolean equals(Object obj) {		
		if (obj instanceof BooleanLiteral) {
			return (this.value == ((BooleanLiteral) obj).value);
		}
		else if (obj instanceof Boolean) {
			return (this.value == ((Boolean) obj));
		}
		else {
			return false;
		}
	}

	@Override
	public Literal clone() {
		// TODO Auto-generated method stub
		return new BooleanLiteral(this.value);
	}
}
