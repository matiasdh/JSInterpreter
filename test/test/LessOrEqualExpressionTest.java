package test;


import org.junit.Test;
public class LessOrEqualExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("true <= true;", true);
		parse("false <= true;", true);
		parse("false <= false;", true);
		
		//string
		parse("\"un string \" <= true;", false);
		parse("true <= \"un string\";", false);
		parse("false <= \"un string\";", false);
		parse("\"un string\" <= false;", false);
		parse("\"un string\" <= \"un string\";", true);
		parse("false <= \"0\";", true);
		parse("true <= \"1\";", true);
		parse("\"0\" <= false;", true);
		parse("\"1\" <= true;", true);
		parse("\"0\" <= true;", true);
		parse("false <= true;", true);
		parse("\"abcdef\" <= \"abcde\";", false);
		parse("\"abcdef\" <= \"abcdefg\";", true);
		parse("\"hola\" <= \"a\";", false);
		parse("\"hola\" <= \"z\";", true);
		parse("\"aa\" <= \"ab\";", true);
		parse("\"ab\" <= \"acb\";", true);
		parse("\"1000a\" <= \"1000b\";", true);
		
		//null
		parse("null < true;", true);
		parse("null < false;", false);
		parse("\"\" < null;", false);
		parse("true < null;", false);
		parse("false < null;", false);
		parse("\"un string\" < null;", false);
		parse("null < \"un string\";", false);
		parse("null < null;", false);
		
		//array
		parse("[true, false, \"hola\"] <= true;", false);
		parse("[true, false, \"hola\"] <= [];", false);
		parse("[] <= [true, false, \"hola\"];", true);
		parse("[] <= [];", true);
		parse("[\"a\", null] <= [\"a\", null];", true);
		parse("[\"a\"] <= [\"a\"];", true);
		parse("[true, false] <= [true];", false);
		parse("[true] <= [true, false];", true);
		parse("[true, false] <= [true, true];", true);
		parse("[true, true] <= [true, false];", false);
		parse("true <= [];", false);
		parse("true <= [true, false];", false);
		parse("true <= [\"0\"];", false);
		parse("true <= [\"5\"];", true);
		parse("false <= [\"1\"];", true);
		
		//numeric
		parse("1 <= true;", true);
		parse("true <= 1;", true);
		parse("0 <= false;", true);
		parse("5 <= true;", false);
		parse("5 <= false;", false);
		parse("\"5\" <= 5;", true);
		parse("1000 <= \"1000b\";", false);
		parse("1000 <= 10;", false);
		parse("1000 <= \"10\";", false);
		
		//NaN
		parse("NaN <= NaN;", false);
		parse("NaN <= 1;", false);
		parse("NaN <= \"NaN\";", false);
		parse("1 <= NaN;", false);
	}

}
