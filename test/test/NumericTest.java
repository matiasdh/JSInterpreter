package test;

import org.junit.Test;

import com.language.model.literals.NumericLiteral;
public class NumericTest extends test.Test {
	
	@Test
	public void test() throws Exception {
		
		parse("0x123ABC;", 1194684);
		parse("0XF;", 15);
		parse("1234;", 1234);
		parse("1234.1234;", 1234.1234);
		parse(".1234;", 0.1234);
		parse("010;", 8);
		parse("2E2;", 200);
		parse("2.000E-2;", 0.02);
		parse(".2E-2;", 0.002);
		
		parse("isNaN;","function isNaN() { [native code] }");
		parse("isNaN();",true);
		parse("isNaN(NaN);",true);
		parse("isNaN(2);",false);
		parse("isNaN('0' + 'xF');",false);
		
		parse("parse('0' + 'xF');",15);
		parse("parse;","function parse() { [native code] }");
		parse("parse('   -15   ');",-15);
		
	}
}
