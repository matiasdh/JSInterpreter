package test;

import org.junit.Test;

public class DistinctExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("true != true;", false);
		parse("false != true;", true);
		parse("false != false;", false);
		
		//string
		parse("\"un string \" != true;", true);
		parse("true != \"un string\";", true);
		parse("false != \"un string\";", true);
		parse("\"un string\" != false;", true);
		parse("\"un string\" != \"un string\";", false);
		parse("false != \"0\";", false);
		parse("true != \"1\";", false);
		parse("\"0\" != false;", false);
		parse("\"1\" != true;", false);
		
		//null
		parse("null != true;", true);
		parse("null != false;", true);
		parse(" \"\" != null;", true);
		parse("true != null;", true);
		parse("false != null;", true);
		parse("\"\" != null;", true);
		parse("\"un string\"!=null;", true);
		parse("null !=\"un string\";", true);
		
		//array
		parse("[true, false, \"hola\"] != true;", true);
		parse("[true, false, \"hola\"] != [];", true);
		parse("[] != [true, false, \"hola\"];", true);
		parse("[] != [];", true);
		parse("[\"a\", null] != [\"a\", null];", true);
		parse("[\"a\"] != [\"a\"];", true);
		
		//numeric
		parse("1 != true;", false);
		parse("0 != false;", false);
		parse("5 != true;", true);
		parse("5 != false;", true);
		
		//NaN
		parse("NaN!=NaN;", true);
		parse("NaN!=1;", true);
		parse("NaN!=\"NaN\";", true);
		parse("1!=NaN;", true);
	}

}
