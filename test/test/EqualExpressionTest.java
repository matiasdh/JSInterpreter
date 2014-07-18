package test;


import org.junit.Test;
public class EqualExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("true == true;", true);
		parse("false == true;", false);
		parse("false == false;", true);
		
		//string
		parse("\"un string \" == true;", false);
		parse("true == \"un string\";", false);
		parse("false == \"un string\";", false);
		parse("\"un string\" == false;", false);
		parse("\"un string\" == \"un string\";", true);
		parse("false == \"0\";", true);
		parse("true == \"1\";", true);
		parse("\"0\" == false;", true);
		parse("\"1\" == true;", true);
		
		//null
		parse("null == true;", false);
		parse("null == false;", false);
		parse(" \"\" == null;", false);
		parse("true == null;", false);
		parse("false == null;", false);
		parse("\"\" == null;", false);
		parse("\"un string\"==null;", false);
		parse("null ==\"un string\";", false);
		
		//array
		parse("[true, false, \"hola\"] == true;", false);
		parse("[true, false, \"hola\"] == [];", false);
		parse("[] == [true, false, \"hola\"];", false);
		parse("[] == [];", false);
		parse("[\"a\", null] == [\"a\", null];", false);
		parse("[\"a\"] == [\"a\"];", false);
		parse("[]==false;", true);
		parse("[[true]] == [true];", false);
		parse("[[\"1\"]] == [\"1\"];", false);
		
		//numeric
		parse("1 == true;", true);
		parse("0 == false;", true);
		parse("5 == true;", false);
		parse("5 == false;", false);
		parse("1.0 == true;", true);
		parse("0.0 == false;", true);
		parse("5.0 == true;", false);
		parse("5.0 == false;", false);
		parse("true == 1;", true);
		parse("false == 0;", true);
		parse("true == 5;", false);
		parse("false == 5;", false);
		parse("1 == [1];", true);
		parse("1 == [[1]];", true);
		parse("[1] == [1];", false);
		parse("[[1]] == [1];", false);
		parse("a =[[1]]; a == a;", true);
		
		//NaN
		parse("NaN==NaN;", false);
		parse("NaN==1;", false);
		parse("NaN==\"NaN\";", false);
		parse("1==NaN;", false);
	}

}
