package test;


import org.junit.Test;
public class NotExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("!true;", false);
		parse("!\"un string \";", false);
		parse("!\"\";", true);
		parse("!false;", true);
		parse("!null;", true);
		parse("![];", false);
		parse("![true, false];", false);
		parse("![\"hola\"];", false);
		parse("!1;", false);
		parse("!0;", true);
		parse("!NaN;", true);
	}

}
