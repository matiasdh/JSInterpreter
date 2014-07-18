package test;

import org.junit.Test;

import com.language.model.literals.NumericLiteral;

public class PlusExpressionTest extends test.Test {
	
	@Test
	public void test() throws Exception {
		parse("1 + 1;", 2);
		parse("1 + 1;", 2);
		parse("1.2 + .8;", 2);
		parse("12e2 + 1;", 1201);
		parse("0xF + 0x1;", 16);
		parse("010 + 1;", 9);
		parse("010 + -1;", 7);
		parse("010 + -1;", 7);
		parse("1 + \"1\";", "11");
		parse(".2 + \".8\";", "0.2.8");
		parse("\"hola como \" + \"estas?\";", "hola como estas?");
		parse("true + 100;", 101);
		parse("true + false;", 1);
		parse("false + 67;", 67);
		parse("true + \"hola\";", "truehola");
		parse("[1,2,3] + [1,2,3];", "1,2,31,2,3");
		parse("[1,2,3] + true;", "1,2,3true");
		parse("true + \"hola\";", "truehola");
		parse("true + NaN;", NumericLiteral.getNaN());
		parse("[] + NaN;", "NaN");
		parse("1 + NaN;", NumericLiteral.getNaN());
		parse("null + NaN;", NumericLiteral.getNaN());
		parse("'1' + NaN;", "1NaN");
		parse("1 + null;", 1);
		parse("[4] + NaN;", "4NaN");
		parse("[4] + null;", "4null");
		parse("[null] + NaN;", "NaN");
		parse("[null, null] + NaN;", ",NaN");
		parse("[null, true, null] + '';", ",true,");
		parse("null + '';", "null");
		parse("a=1; a++;", 1);
		parse("a=1; a--;", 1);
		parse("a=1; a++; a = a +1;", 3);
		parse("a=2; b=4; a=b++; a++;", 4);
		parse("a=100; c=a+5; a--;", 100);
		parse("a=100; c=a +5; a--; b=a;", 99);
		parse("a=100; c=a -5; a--; b=a;", 99);
		parse("a=100; c=a *5; a--; b=a;", 99);
		parse("a=100; c=a /5; a--; b=a;", 99);
		//parse("NaN++;", NumericLiteral.getNaN());
		
	}
}
