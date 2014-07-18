package com.language.model.literals;

public class NullLiteral extends Literal {
	
	public NullLiteral() {}

	@Override
	public Boolean toBoolean() {
		return null;
	}

	@Override
	public NumericLiteral toNumeric() {
		// TODO Auto-generated method stub
		return new NumericLiteral(0);
	}

	@Override
	public StringLiteral toJSString() {
		// TODO Auto-generated method stub
		return new StringLiteral("null");
	}

	@Override
	public boolean isNaN() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return "null";
	}
	
	public boolean equals(Object obj) {		
		if ((obj == null) || (obj instanceof NullLiteral)) {
			return true;
		}
		return false;
	}

	@Override
	public Literal clone() {
		return this;
	}
	
	
}
