package com.language.model.util;


public enum ParserSymType {
	TIMES(16),
	AND(7),
	DOUBLE_MINUS(15),
	PLUS(12),
	OR(6),
	RPAREN(11),
	NUMERIC_STRING(5),
	NOT(9),
	NUMERIC_LITERAL(4),
	GREATER(18),
	CLOSE_ARRAY(26),
	LPAREN(10),
	STRING_LITERAL(3),
	BOOLEAN(2),
	NaN(24),
	EQUALS(8),
	COMMA(27),
	EOF(0),
	DISTINCT(22),
	DOUBLE_PLUS(14),
	MINUS(13),
	LESS_OR_EQUAL(20),
	error(1),
	OPEN_ARRAY(25),
	ARRAY(28),
	OBELUS(17),
	GREATER_OR_EQUAL(21),
	NULL(23),
	LESS(19),
	ERROR(500);
	
	private int code;
	
	ParserSymType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public boolean isNumericType() {
		return equals(NUMERIC_LITERAL) || equals(NUMERIC_STRING) || equals(BOOLEAN) || equals(NULL);
	}
	
	public static ParserSymType getValue(int code) {
		for (ParserSymType t : ParserSymType.values()) {
			if(t.getCode()==code){
				return t;
			}
		};
		return ParserSymType.ERROR;
	}

}
