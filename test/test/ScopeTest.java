package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.language.model.util.CurrentState;
import com.language.model.util.Scope;
import com.language.model.expression.EvaluatableExpression;
import com.language.model.expression.Identifier;
import com.language.model.literals.NumericLiteral;

public class ScopeTest {
	
	@Test
	public void test() {
		Scope parent, scope1, scope2, scope3, result;
		Identifier a = new Identifier("a");
		Identifier b = new Identifier("b");
		Identifier c = new Identifier("c");
		Identifier d = new Identifier("d");
		Identifier e = new Identifier("e");
		/*
		 * a = 1
		 * b = 2
		 * c = 3
		 * { a = 5
		 * 	 d = 6
		 * 	 e = 7
		 * 	 c = 8
		 * }
		 */
		parent = CurrentState.getInstance().newScope();
		parent.push(a, new NumericLiteral(1));
		parent.push(b, new NumericLiteral(2));
		parent.push(c,  new NumericLiteral(3));
		scope1 = CurrentState.getInstance().newScope();
		scope1.push(a,  new NumericLiteral(5));
		scope1.push(d,  new NumericLiteral(6));
		scope1.push(e,  new NumericLiteral(7));
		scope1.push(c,  new NumericLiteral(8));
		result = CurrentState.getInstance().pop();
		result = CurrentState.getInstance().pop();
		assertTrue(result.getValue(a).equals( new NumericLiteral(5)));
		assertTrue(result.getValue(b).equals( new NumericLiteral(2)));
		assertTrue(result.getValue(c).equals( new NumericLiteral(8)));
		assertTrue(result.getValue(d)==null);
		assertTrue(result.getValue(e)==null);
		/*
		 * a = 1
		 * b = 2
		 * c = 3
		 * { var a = 5
		 * 	 d = 6
		 * 	 e = 7
		 * 	 var c = 8
		 * 		{
		 * 			b = 40
		 * 			e = 9
		 * 			var b = 3
		 * 			{
		 * 				var e = 30
		 * 				c = 5
		 * 			}
		 * 		}
		 * 	d = 10
		 * }
		 */
		CurrentState.getInstance().clear();
		parent = CurrentState.getInstance().newScope();
		parent.push(a,  new NumericLiteral(1));
		parent.push(b,  new NumericLiteral(2));
		parent.push(c,  new NumericLiteral(3));
		scope1 = CurrentState.getInstance().newScope();
		scope1.forcePush(a,  new NumericLiteral(5));
		scope1.push(d,  new NumericLiteral(6));
		scope1.push(e,  new NumericLiteral(7));
		scope1.forcePush(c,  new NumericLiteral(8));
		scope2 = CurrentState.getInstance().newScope();
		scope2.push(b,  new NumericLiteral(40));
		scope2.push(e,  new NumericLiteral(9));
		scope2.forcePush(b,  new NumericLiteral(3));
		scope3 = CurrentState.getInstance().newScope();
		scope3.forcePush(e,  new NumericLiteral(30));
		scope3.push(c,  new NumericLiteral(5));
		result = CurrentState.getInstance().pop();
		result = CurrentState.getInstance().pop();
		scope1.push(d,  new NumericLiteral(10));
		result = CurrentState.getInstance().pop();
		result = CurrentState.getInstance().pop();
		assertTrue(result.getValue(a).equals( new NumericLiteral(1)));
		assertTrue(result.getValue(b).equals( new NumericLiteral(40)));
		assertTrue(result.getValue(c).equals( new NumericLiteral(3)));
		
		/*
		 * {var a=1; b=2;}*/
		CurrentState.getInstance().clear();
		parent = CurrentState.getInstance().newScope();
		parent.forcePush(a,  new NumericLiteral(1));
		parent.push(b,  new NumericLiteral(2));
		EvaluatableExpression expr = CurrentState.getInstance().getValue(a);
		assertTrue(( new NumericLiteral(1)).equals(expr));
		
		
	}
}
