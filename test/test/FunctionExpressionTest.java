package test;

import org.junit.Test;

public class FunctionExpressionTest extends test.Test {

	@Test
	public void test() throws Exception {
		parse("function prueba() {;};", null);
		parse("function prueba() {a=5; return a;}; prueba();", 5);
		parse("function prueba() {a=5; return a; return c;}; prueba();", 5);
		parse("function prueba() {a=5; return a;}; b = prueba(); b;", 5);
		parse("function prueba(arg1, arg2) {return arg2 + arg1;}; prueba(1,2, 3);", 3);
		parse("function prueba(arg1) {if (arg1 > 0) { return arg1 + prueba(arg1-1);}else {return 1;}}; prueba(100);", 5051);
		parse("var prueba = function (arg1) {if (arg1 > 0) { return arg1 + prueba(arg1-1);}else {return 1;}}; prueba(10);", 56);
		String factorial = "function factorial (n, accumulator) { 	if (n == 0) { 		return accumulator; 	};	return factorial(n-1, n * accumulator); }";
		parse(factorial + "n = 5; factorial(n,1);", 120);
		
	}

}
