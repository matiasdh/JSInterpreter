package test;

import org.junit.Test;

import com.language.model.literals.NumericLiteral;

public class TimesExpressionTest extends test.Test {
	
	@Test
	public void test() throws Exception {
		
		parse("1 * 1;", 1);
		parse("1.2 * .2;", 0.24);
		parse("12e2 * 100;", 120000);
		parse("0xF * 0x1;", 15);
		parse("010 * 1;", 8);
		
		parse("7 * -1;", -7);

		parse("1 * \"1\";", 1);
		parse(".2 * .8;", 0.16000000000000003);
		parse("\"hola como \" * \"estas?\";", NumericLiteral.getNaN());
		parse("true * 100;", 100);
		parse("true * false;", 0);
		parse("67 * false;", 0);
		parse("true * true;", 1);
		parse("[1,2,3] * [1,2,3];", NumericLiteral.getNaN());
		parse("[1,2,3] * true;", NumericLiteral.getNaN());
		parse("true * \"hola\";", NumericLiteral.getNaN());
		parse("[1] * 5;", 5);
		
	}
}
