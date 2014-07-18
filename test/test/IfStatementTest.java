package test;

import org.junit.Test;

public class IfStatementTest extends test.Test {
	@Test
	public void test() throws Exception {
		parse("if(true){}", null);
		parse("if(true){a=1; b=2;}", 2);
		parse("if(false){a=1; b=2;}", null);
		parse("if(1){var a=1; b=2;}", 2);
		parse("var a = 2; if(a){var a=1;}", 1);
		parse("if(1){var a=1; b = a;}", 1);
		parse("{if(2){var a=1; b=2;} if(a){c=20;}}", 20);
		parse("if(2){var a=1; b=2;} if(b){b='hola';}", "hola");
		parse("if(2){var a=1; b=2; if(b){b='hola';}}", "hola");
		parse("if(true){} else {}", null);
		parse("if(false){} else {b=3;v=7;}v;", 7);
		parse("if(false){} else {b=3;if (b>4){v=7;}else{v=8;}}", 8);
		parse("if (true) a=33;a;", 33);
	}
}
