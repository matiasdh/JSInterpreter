package test;

import org.junit.Test;

import com.language.model.literals.NumericLiteral;

public class ObelusExpressionTest extends test.Test {
	
	@Test
	public void test() throws Exception {
		
		parse("1 / 1;", 1);
		parse("1.2 / .8;", 1.4999999999999998);
		parse("12e2 / 1;", 1200);
		parse("0xF / 0x1;", 15);
		parse("010 / 1;", 8);
		
		parse("1 / \"1\";", 1);
		parse(".2 / \".8\";", 0.25);
		parse("\"hola como \" / \"estas?\";", NumericLiteral.getNaN());
		parse("true / 100;", 0.01);
		parse("true / false;", NumericLiteral.getPositiveInfinity());
		parse("false / 67;", 0);
		parse("true / \"hola\";", NumericLiteral.getNaN());
		parse("[1,2,3] / [1,2,3];", NumericLiteral.getNaN());
		parse("[1,2,3] / true;", NumericLiteral.getNaN());
		parse("true / \"hola\";", NumericLiteral.getNaN());
		parse("true / NaN;", NumericLiteral.getNaN());
		parse("[] / NaN;", NumericLiteral.getNaN());
		parse("1 / NaN;", NumericLiteral.getNaN());
		parse("null / NaN;", NumericLiteral.getNaN());
		parse("'1' / NaN;", NumericLiteral.getNaN());
		parse("1 / null;", NumericLiteral.getPositiveInfinity());
		parse("[4] / NaN;", NumericLiteral.getNaN());
		parse("[4] / null;", NumericLiteral.getPositiveInfinity());
		parse("[null] / NaN;", NumericLiteral.getNaN());
		parse("[null, null] / NaN;", NumericLiteral.getNaN());
		parse("[null, true, null] / '';", NumericLiteral.getNaN());
		parse("null / '';", NumericLiteral.getNaN());
		
	}
}
