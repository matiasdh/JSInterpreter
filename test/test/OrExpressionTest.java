package test;

import org.junit.Test;

import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.BooleanLiteral;
import com.language.model.literals.StringLiteral;
import com.language.model.literals.NumericLiteral;

public class OrExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		
		parse("true || true;", true);
		parse("false || true;", true);
		
		//string
		parse("\"un string \" || true;", "un string ");
		parse("true || \"un string\";", true);
		parse("false || \"un string\";", "un string");
		parse("\"un string\" || false;", "un string");
		
		//null
		parse("null || true;", true);
		parse("true || null;", true);
		parse("false || null;", null);
		parse("null || false;", false);
		parse("\"un string\" || null;", "un string");
		parse("null || \"un string\";", "un string");
		
		//array
		ArrayLiteral list = new ArrayLiteral();
		parse("[true, false, \"hola\"] || true;", "true,false,hola");
		parse("[true, false, \"hola\"] || [];","true,false,hola");

		list = new ArrayLiteral();
		parse("[] || [true, false, \"hola\"] ;", "true,false,hola");
		
		//numeric
		parse("1 || 0;", 1);
		parse("0 || 0;", 0);
		
		//NaN
		parse("NaN || true;", true);
		parse("NaN || NaN;", NumericLiteral.getNaN());
		parse("true || NaN;", true);
		parse("NaN || false;", false);
		parse("false || NaN;", NumericLiteral.getNaN());
	}

}
