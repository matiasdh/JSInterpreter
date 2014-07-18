package test;

import org.junit.Test;

import com.language.model.literals.NumericLiteral;

public class AndExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("true && false;", false);
		parse("false && true;", false);
	
//		//string
		parse("\"un string \" && true;", true);
		parse("true && \"un string\";", "un string");
		parse("false && \"un string\";", false);
		parse("\"un string\" && false;", false);
		
		//precedence
		parse("false || false || false || true && !false && true || false;", true);
		parse("true && false || true&&!false &&true || true&&true;", true);
		parse("true && false || true && !false &&true || false;", true);
		parse("true && false || false;", false);
		
		//null
		parse("null && true;", null);
		parse("true && null;", null);
		parse("false && null;", false);
		parse("null && false;", null);
		parse("\"un string\" && null;", null);
		parse("null && \"un string\";", null);
		
		//brackets
		parse("(true && false);", false);
		parse("(true && false || true) &&false || !true;", false);
		parse("true && (false || !true || !(true || true));", false);
		parse("true && false || !true || !true || true;", true);
		
		//array
		parse("[true, false, \"hola\"] && true;", true);
		
		parse("[true, false, \"hola\"] && [];", "");
		parse("[true, false, \"hola\"] && ['hola'];", "hola");
		parse("[] && [true, false, \"hola\"] ;", "");
		
		//numeric
		parse("1 && true;", true);
		parse("0 && true;", 0);
		parse("1 && 0;", 0);
		
		//NaN
		parse("NaN && true;", NumericLiteral.getNaN());
		parse("NaN && NaN;", NumericLiteral.getNaN());
		parse("true && NaN;", NumericLiteral.getNaN());
		parse("NaN && false;", NumericLiteral.getNaN());
		parse("false && NaN;", false);
	}

}
