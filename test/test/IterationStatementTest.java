package test;

import org.junit.Test;


public class IterationStatementTest extends test.Test {

	@Test
	public void test() throws Exception {
		parse("for (var a =1; a< 2; a++) {}; a;", 2);
		parse("for (var a =1; a> 5; a++) {};", null);
		parse("for (var a =1; a> 5; a++) {}; b = a;", 1);
		parse("for (var a =1; null; a++) {}; a;", 1);
		parse("for (var a =1; null; a++) {}; a;", 1);
		parse("for (var a =1; a < 5; a++) {var c = 1000;}; c;", 1000);
		parse("for (var a =1; a < 5; a++) {if(a==2){b=0;}else{c=4;}}; c=b;", 0);
		parse("for (var a =1; a < 5; a++) {break;};a;", 1);
		parse("for (var a =1; a < 5; a++) {break; a =2;};a;", 1);
		parse("for ( a =1; a < 5; a++) {if (a==2){break;}; v=a+1;};v;", 2);
		parse("for (a=1; a < 0; a--) {if (a==2){break;}; v=a+1;}", null);
		parse("for (a =1; a < 5; a++) {break; a =2;};a;", 1);
		parse("for (a =1; a < 5; a++) {if (a!=2) {continue;}else{break;}};a;", 2);
		parse("for (a =1; a < 5; a++) {if (a!=2) {continue;}};a;", 5);
		parse("for (a =1; a < 5; a++) {if (a!=2) continue; else break;};a;", 2);		
	}
}
