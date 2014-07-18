package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import com.language.model.expression.AssignmentExpression;
import com.language.model.expression.BinaryExpression;
import com.language.model.expression.EvaluatableExpression;
import com.language.parser.ExpressionParser;

public class Test {

	@org.junit.Test
	public void test() throws Exception {
		
	}
	
	public void parse(String exptext, Object match) {
		EvaluatableExpression expr = null;
		try {
			expr = ExpressionParser.parse(exptext);
			EvaluatableExpression eval = expr.getValue();
			if (eval==null) {
				if (match!=null) {
					System.out.println("invalid value get: " + null);
					System.out.println("expected: " + match);
				}
				assertTrue(match==null);
				return;
			}
			Object obj = eval.toPrimitive();
			if (obj==null) {
				assertTrue(obj==match);
			} else {
				if ((obj instanceof Object[]) && (match instanceof Object[])) {
					assertTrue(Arrays.equals((Object[])obj, (Object[])match));
				} else  {
					if(!obj.equals(match)) {
						System.out.println("invalid value get: " + obj);
						System.out.println("expected: " + match);
					}
					assertTrue(obj.equals(match));
				}
			}
		} catch(Exception ex) {
			if (expr!=null) {
				System.out.println(ex);
				System.out.println("Error while calculating the expression " + expr.toString());
			} else {
				System.out.println("Error while parsing expression, expr=null, " + ex.getMessage());
			}
			
		}
	}

}
