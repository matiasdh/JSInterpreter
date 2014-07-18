package test;

import java.util.ArrayList;

import org.junit.Test;

import com.language.model.literals.ArrayLiteral;
import com.language.model.literals.BooleanLiteral;
import com.language.model.literals.StringLiteral;

public class VarExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
	
		parse("var a= 1;", 1);
		parse("var a=        1;", 1);
		parse("var $a= \"hola\";", "hola");
		parse("var $a= true ;", true);
		parse("var a13 = true ;", true);		
		parse("var a_ = true ;", true);	
		parse("var ____adsgrg_ = 123 ;", 123);	
		parse("var eeeedga_          = \"sa\" ;", "sa");	
		parse("var                     a= 1;", 1);
		parse("var                     a               = 1;", 1);

//		//definition
		parse("  a= 1;", 1);
		parse("  a= [1,2,3];a.pop();", 3);
		parse("  a= [1,2,[1,2,3]];a.pop().pop();", 3);
		parse("  a= [1,2,3];a.pop();a;", "1,2");
		parse("a=        1;", 1);
		parse("$a= \"hola\";", "hola");
		parse("$a= true ;", true);
		parse("a13 = true ;", true);		
		parse("a_ = true ;", true);	
		parse("____adsgrg_ = 123 ;", 123);	
		parse("        eeeedga_     = \"sa\" ;", "sa");	
	
	}

}
