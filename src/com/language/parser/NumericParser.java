package com.language.parser;

import com.language.model.literals.NumericLiteral;
import com.language.model.literals.StringLiteral;

public class NumericParser {
	private static String pattern = getPattern();

	public static boolean isNumeric(String jsstring) {
		String element_string = jsstring.toString();
		return  (element_string.isEmpty()) || (element_string.matches(pattern));
	}
	
	public static boolean isNumeric(StringLiteral jsstring) {
		return isNumeric(jsstring.toString().trim());
	}
	
	public static NumericLiteral decodeString(String raw_encoded_number) {
		String encoded_number = raw_encoded_number.trim();
		// Return 0 if the string is empty
		if (encoded_number.isEmpty()) {
			return new NumericLiteral(0);			
		}
		Integer exponent_index = getExponentIndexFor(encoded_number);
		Boolean has_exponent = (exponent_index >= 0);
		Boolean is_decimal = (encoded_number.indexOf('.') >= 0);
		double exponent_value = Math.pow(10,
				getExponentPowerFor(encoded_number));
		// Remove the exponent part
		String sanitized_number = (exponent_index >= 0) ? encoded_number
				.substring(0, exponent_index) : encoded_number;
		if (is_decimal) {
			return new NumericLiteral (has_exponent ? Double.parseDouble(sanitized_number)
					* exponent_value : Double.parseDouble(sanitized_number));
		} else {
			return new NumericLiteral((Double) (has_exponent ? (Long.decode(sanitized_number)
					* exponent_value) : Long.decode(sanitized_number)));
		}
	}
	
	private static String getPattern() {
		// initialize the pattern
		String string_pattern = "";
		String white_space = "[" + System.getProperty("line.separator") + " ]*";
		// Numeric part of the string
		string_pattern += "[+-]?((\\d)*(\\.(\\d)*)?|\\.(\\d)*)";
		// Exponent part of the number
		string_pattern += "([eE][-+]?(\\d)+)?";
		// Hexadecimal Numbers
		string_pattern = "(" + string_pattern + ")|";
		string_pattern += "(0(x|X)[0-9a-fA-F]+)";
		string_pattern = "" + white_space + "(" + string_pattern + ")"
				+ white_space + "";
		return string_pattern;
	}

	private static Integer getExponentPowerFor(String encoded_number) {
		Integer exponent_index = getExponentIndexFor(encoded_number);
		if (exponent_index >= 0) {
			return Integer.decode(encoded_number.substring(exponent_index + 1));
		} else {
			return 0;
		}
	}

	private static Integer getExponentIndexFor(String encoded_number) {
		return (encoded_number.indexOf('e') >= 0) ? encoded_number.indexOf('e')
				: encoded_number.indexOf('E');
	}
}
