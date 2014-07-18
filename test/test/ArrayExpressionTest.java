package test;

import org.junit.Test;

import com.language.model.literals.*;

public class ArrayExpressionTest extends test.Test {
	
	@Test
	public void test() {
		parse("[true, false];", "true,false");
		parse("[true, false, null, \"hola que tal?\", \"\"];", "true,false,,hola que tal?,");
		parse("[true && null];", "");
		parse("[true && null, \"chau\"];", ",chau");
		
		//Properties
		parse("[true && null, \"chau\"].length;", 2);
		parse("[].length;", 0);
		parse("[,,,,,,,,].length;", 8);
		parse("[,].length;", 1);
		parse("[[1,2,3],[]].length;", 2);
		parse("[[1,2,3],[]]['length'];", 2);
		
		parse("[1,2,3,4,5].pop;", "function pop() { [native code] }");
		parse("[1,2,3,4,5].pop();", 5);
		parse("[1,2,3,4,5].pop() + 5;", 10);
		parse("[1,2,3,4, 'hola pepe'].pop() + 5;", "hola pepe5");
		parse("[1,2,3,4, ['hola pepe']].pop().pop();", "hola pepe");
		parse("[1,2,3,4, [['hola pepe']]].pop().pop();", "hola pepe");
		parse("[1 ,2 ,[3 , 4, [5]]].pop().pop().pop();", 5);
		
		parse("[1,2,3,4,5].shift;", "function shift() { [native code] }");
		parse("[1,2,3,4,5].shift();", 1);
		parse("[1,2,3,4,5].shift() + 5;", 6);
		parse("['hola pepe', 1,2,3,4].shift() + 5;", "hola pepe5");
		parse("[[1],2,3,4, ['hola pepe']].shift().shift();", 1);
		parse("[[['hola pepe']], 1,2,3,4].shift().shift();", "hola pepe");
		parse("[1 ,2 ,[[3] , 4, [5]]].pop().shift().pop();", 3);
		
		parse("[true, false].join(':::' + '?');", "true:::?false");
		parse("[true, false].join(',,');", "true,,false");
		parse("[true, false, [1,2,3]].join('_');", "true_false_1,2,3");
		parse("[true, false, [1,2,3]].join();", "true,false,1,2,3");
		
		parse("[true, false, [1,2,3]].reverse();", "1,2,3,false,true");
		parse("[true, false, [1,2,3]].reverse(123);", "1,2,3,false,true");
		
		parse("[].push(123, true && false);", 2);
		parse("a = [1,2,3]; a[1];", 2);
		parse("a = [1,2,3]; a[1] = true || false;a[1];", true);
		
		parse("[1,2,3,4,5].concat;", "function concat() { [native code] }");
		parse("[1,2,3,4,5].concat();", "1,2,3,4,5");
		parse("[1,2,3,4,5].concat(1);", "1,2,3,4,5,1");
		parse("[1,2,3,4,5].concat(1, 2, [3,4,5]);", "1,2,3,4,5,1,2,3,4,5");
		parse("[1,2,3,4,5].pop() + 5;", 10);
		parse("var a = [1,2,[3,4,5,[6,7,8]]];a[2][3][2] = 'string'; a[2][3][2];", "string");
		parse("var a = [1,2,[3,4,5,[6,7,8]]];a[2][3][2] = 10; a;", "1,2,3,4,5,6,7,10");
		parse("var a = [1,2,3,4]; b = a[1];b; b = 5; a[1];", 2);
		
	}
	
}
