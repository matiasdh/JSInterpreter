package test;

import org.junit.Test;

public class GreaterExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("true > true;", false);
		parse("false > true;", false);
		parse("false > false;", false);

		//string
		parse("\"un string \" > true;", false);
		parse("true > \"un string\";", false);
		parse("false > \"un string\";", false);
		parse("\"un string\" > false;", false);
		parse("\"un string\" > \"un string\";", false);
		parse("false > \"0\";", false);
		parse("true > \"1\";", false);
		parse("\"0\" > false;", false);
		parse("\"1\" > true;", false);
		parse("\"1\" > false;", true);
		parse("false > true;", false);
		parse("\"abcdef\" > \"abcde\";", true);
		parse("\"abcdef\" > \"abcdefg\";", false);
		parse("\"hola\" > \"a\";", true);
		parse("\"hola\" > \"z\";", false);
		parse("\"aa\" > \"ab\";", false);
		parse("\"ab\" > \"acb\";", false);
		parse("\"1000a\" > \"1000b\";", false);
		
		//null
		parse("null > true;", false);
		parse("null > false;", false);
		parse("\"\" > null;", false);
		parse("true > null;", true);
		parse("false > null;", false);
		parse("\"un string\" > null;", false);
		parse("null > \"un string\";", false);
		parse("null > null;", false);
		
		//array
		parse("[true, false, \"hola\"] > true;", false);
		parse("[true, false, \"hola\"] > [];", true);
		parse("[] > [true, false, \"hola\"];", false);
		parse("[] > [];", false);
		parse("[\"a\", null] > [\"a\", null];", false);
		parse("[\"a\"] > [\"a\"];", false);
		parse("[true, false] > [true];", true);
		parse("[true] > [true, false];", false);
		parse("[true, false] > [true, true];", false);
		parse("[true, true] > [true, false];", true);
		parse("true > [];", true);
		parse("true > [true, false];", false);
		parse("true > [\"1\"];", false);
		parse("true > [\"0\"];", true);
		parse("[\"5\"] > true;", true);
		parse("[\"5\"] > true;", true);
		parse("[\"1,3,4\"] > true;", false);
		parse("[\"0000\"] > true;", false);
		parse("[\"1\"] > false;", true);
		parse("[\"1\"] > false;", true);
		parse("[[\"1\"]] > false;", true);
		parse("[[\"19\"]] > true;", true);
		parse("[[\"1,2\"]] > false;", false);
		parse("[[[]]] > false;", false);
		parse("[[\"hola como andas\"]] > true;", false);
		parse("[[[[\"4\"]]]] > false;", true);
		parse("[[\"1\"]] > [];", true);
		parse("[[\"1\"]] > [[]];", true);
		parse("[[\"1\"]] > [[[]]];", true);
		parse("\"10\" > \"8\";", false);
		parse("[\"10\"] > [\"8\"];", false);
		parse("[[\"10\"]] > [[[\"8\"]]];", false);
		
//		//numeric
		parse("1 > true;", false);
		parse("true > 1;", false);
		parse("0 > false;", false);
		parse("5 > true;", true);
		parse("5 > false;", true);
		parse("\"5\" > 5;", false);
		parse("1000 > \"1000b\";", false);
		parse("1000 > 10;", true);
		parse("1000 > \"10\";", true);
		parse("1 > [0];", true);
		parse("1 > [22];", false);
		parse("[[1]] > [1];", false);
		
		//NaN
		parse("NaN > NaN;", false);
		parse("NaN > 1;", false);
		parse("NaN > \"NaN\";", false);
		parse("1 > NaN;", false);
		
	}

}
