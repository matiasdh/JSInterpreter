package com.language.model.literals;

import com.language.model.expression.EvaluatableExpression;
import com.language.model.nativeFunctions.CharAtFunction;
import com.language.model.nativeFunctions.ConcatStringLiteralFunction;
import com.language.model.nativeFunctions.IndexOfFunction;
import com.language.model.nativeFunctions.LastIndexOfFunction;
import com.language.model.nativeFunctions.SplitFunction;
import com.language.model.nativeFunctions.SubstringFunction;
import com.language.model.nativeFunctions.ToLowerCaseFunction;
import com.language.model.nativeFunctions.ToUpperCaseFunction;
import com.language.parser.NumericParser;

public class StringLiteral extends Literal {
	
	private String value;
	
	public StringLiteral(String str) {
		this.value = str;
	}

	public StringLiteral() {
		this.value = "";
	}

	public StringLiteral(char value) {
		this.value = value + "";
	}

	@Override
	public Boolean toBoolean() {
		return !value.isEmpty();
	}

	@Override
	public NumericLiteral toNumeric() {		
		return NumericParser.isNumeric(this) ? NumericParser.decodeString(value) : NumericLiteral.getNaN();
	}

	@Override
	public StringLiteral toJSString() {
		return this;
	}
	
	public String toString() {
		return value;
	}

	@Override
	public boolean isNaN() {
		return !NumericParser.isNumeric(this);
	}
	
	@Override
	public EvaluatableExpression getProperty(String propertyName) {
		if (propertyName.equals("length")) {
			return new NumericLiteral(value.length());
		}
		if (propertyName.equals("toUpperCase")) {
			return new ToUpperCaseFunction(value);
		}
		if (propertyName.equals("toLowerCase")) {
			return new ToLowerCaseFunction(value);
		}
		if (propertyName.equals("concat")) {
			return new ConcatStringLiteralFunction(this);
		}
		if (propertyName.equals("indexOf")) {
			return new IndexOfFunction(value);
		}
		if (propertyName.equals("substring")) {
			return new SubstringFunction(value);
		}
		if (propertyName.equals("charAt")) {
			return new CharAtFunction(value);
		}
		if (propertyName.equals("split")) {
			return new SplitFunction(value);
		}
		if (propertyName.equals("lastIndexOf")) {
			return new LastIndexOfFunction(value);
		}
		return super.getProperty(propertyName);
	}	
	
	public StringLiteral concat(StringLiteral js_string) {
		StringLiteral return_value = new StringLiteral();
		return_value.value = this.value.concat(js_string.value);
		return return_value;
	}
	
	public StringLiteral concatAndUpdate(String string) {
		this.value = this.value.concat(string);
		return this;
	}
	
	public boolean equals(Object obj) {		
		if (obj instanceof StringLiteral) {
			return (this.value.equals(((StringLiteral) obj).value));
		}
		else if (obj instanceof String) {
			return (this.value.equals(((String) obj)));
		}
		else {
			return false;
		}
	}

	@Override
	public Literal clone() {
		return new StringLiteral(this.value);
	}

}
