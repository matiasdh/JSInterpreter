package test;


import org.junit.Test;
public class GreaterOrEqualExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("true >= true;", true);
		parse("false >= true;", false);
		parse("false >= false;", true);
		
		//string
		parse("\"un string \" >= true;", false);
		parse("true >= \"un string\";", false);
		parse("false >= \"un string\";", false);
		parse("\"un string\" >= false;", false);
		parse("\"un string\" >= \"un string\";", true);
		parse("false >= \"0\";", true);
		parse("true >= \"1\";", true);
		parse("\"0\" >= false;", true);
		parse("\"1\" >= true;", true);
		parse("\"1\" >= false;", true);
		parse("false >= true;", false);
		parse("\"abcdef\" >= \"abcde\";", true);
		parse("\"abcdef\" >= \"abcdefg\";", false);
		parse("\"hola\" >= \"a\";", true);
		parse("\"hola\" >= \"z\";", false);
		parse("\"aa\" >= \"ab\";", false);
		parse("\"ab\" >= \"acb\";", false);
		parse("\"1000a\" >= \"1000b\";", false);
		parse("\"1000a\" >= \"1000b\" >= true;", false);
		
		//null
		parse("null >= true;", false);
		parse("null >= false;", true);
		parse("\"\" >= null;", true);
		parse("true >= null;", true);
		parse("false >= null;", true);
		parse("\"un string\" >= null;", false);
		parse("null >= \"un string\";", false);
		parse("null >= null;", true);
		
		//array
		parse("[true, false, \"hola\"] >= true;", false);
		parse("[true, false, \"hola\"] >= [];", true);
		parse("[] >= [true, false, \"hola\"] ;", false);
		parse("[] >= [];", true);
		parse("[\"a\", null] >= [\"a\", null];", true);
		parse("[\"a\"] >= [\"a\"];", true);
		parse("[true, false] >= [true];", true);
		parse("[true] >= [true, false];", false);
		parse("[true, false] >= [true, true];", false);
		parse("[true, true] >= [true, false];", true);
		parse("true >= [];", true);
		parse("true >= [true, false];", false);
		parse("true >= [\"1\"];", true);
		
		//numeric
		parse("1 >= true;", true);
		parse("true >= 1;", true);
		parse("0 >= false;", true);
		parse("5 >= true;", true);
		parse("5 >= false;", true);
		parse("\"5\" >= 5;", true);
		parse("1000 >= \"1000b\";", false);
		parse("1000 >= 10;", true);
		parse("1000 >= \"10\";", true);
		parse("1 >= [0];", true);
		parse("1 >= [22];", false);
		parse("[[1]] >= [1];", true);
		parse("1 >= [1];", true);
		parse("22 >= [22];", true);
		
		//NaN
		parse("NaN >= NaN;", false);
		parse("NaN >= 1;", false);
		parse("NaN >= \"NaN\";", false);
		parse("1 >= NaN;", false);
	}

}
