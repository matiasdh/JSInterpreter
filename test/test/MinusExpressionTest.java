package test;

import org.junit.Test;

import com.language.model.literals.NumericLiteral;

public class MinusExpressionTest extends test.Test {
	
	@Test
	public void test() throws Exception {
		
		parse("1 - 1;", 0);
		parse("1.2 - .2;", 1);
		parse("12e2 - 100;", 1100);
		parse("0xF - 0x1;", 14);
		parse("010 - 1;", 7);
		
		parse("7 - -1;", 8);
		
		parse("1 - \"1\";", 0);
		parse(".2 - .8;", -0.6000000000000001);
		parse("\"hola como \" - \"estas?\";", NumericLiteral.getNaN());
		parse("true - 100;", -99);
		parse("true - false;", 1);
		parse("67 - false;", 67);
		parse("true - true;", 0);
		parse("[1,2,3] - [1,2,3];", NumericLiteral.getNaN());
		parse("[1,2,3] - true;", NumericLiteral.getNaN());
		parse("true - \"hola\";", NumericLiteral.getNaN());
		
	}
}
