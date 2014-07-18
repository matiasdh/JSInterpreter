package test;

import org.junit.Test;

public class StringLiteralTest extends test.Test {

	@Test
	public void test() throws Exception {

		parse("'3';", "3");
		parse("'hola';", "hola");
		parse("'0F';", "0F");
		parse("'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa';",
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		//Properties
		parse("'hola'.length;", 4);
		parse("('hola' + 'hola').length;", 8);
		parse("(('hola' + 'hola') + 15).length;", 10);
		parse("'hola' + 'hola';", "holahola");
		parse("\"Una frase con 'comillas simples' dentro\";", "Una frase con 'comillas simples' dentro");
		parse("'Una frase con \"comillas dobles\" dentro';", "Una frase con \"comillas dobles\" dentro");
		
		//Functions
		parse("'hola'.toUpperCase();", "HOLA");
		parse("('hola' + 'HOLA').toUpperCase();", "HOLAHOLA");
		parse("''.toUpperCase;", "function toUpperCase() { [native code] }");

		parse("'HOlA'.toLowerCase();", "hola");
		parse("('hOla' + 'HOLa').toLowerCase().toUpperCase();", "HOLAHOLA");
		parse("''.toLowerCase;", "function toLowerCase() { [native code] }");
		parse("alert('hola!');", null);
		parse("'hola'.concat(' mundo');", "hola mundo");
		parse("a = 'hola'.concat(' mundo').concat('!!!'); a;", "hola mundo!!!");
		parse("'hola'.concat;", "function concat() { [native code] }");
		
		parse("var mensaje = 'Mensaje de prueba';"
				+ "function muestraMensaje() { alert(mensaje); };"
				+ "muestraMensaje();",null);
		parse("'hola'.indexOf('h');", 0);
		parse("'hola'.indexOf('ho');", 0);
		parse("'hola'.indexOf('l');", 2);
		parse("'hola'.indexOf('la');", 2);
		parse("'hola'.indexOf('las');", -1);

		parse("'hola mundo!!!'.substring(1);", "ola mundo!!!");
		parse("'hola mundo!!!'.substring(1).substring(2);", "a mundo!!!");
		
		parse("'hola mundo!!!'.substring(1,2);", "o");
		parse("'hola mundo!!!'.substring(1).substring(2);", "a mundo!!!");
		parse("'hola mundo!!!'.substring(14);", "");
		parse("''.substring(1);", "");
		
		parse("'hola'.charAt(1);", "o");
		parse("'hola'.charAt(4);", "");
		parse("'hola'.charAt(3);", "a");
		parse("'hola'.charAt();", "h");
		parse("''.charAt();", "");
		
		parse("'hola como estas'.split(' ');", "hola,como,estas");
		parse("'hola como estas'.split();", "hola como estas");
		parse("a='a'; 'hola como estas'.split('l' + a);", "ho, como estas");
		parse("a='a'; 'hola como estas'.lastIndexOf(a);", 13);
		parse("a='a'; 'hola como estas'.lastIndexOf();", -1);
		parse("a='a'; 'hola como estas'.lastIndexOf('z');", -1);
		parse("a='a'; 'hola como estas'.indexOf(a);", 3);
		parse("var palabra = \"Hola\"; var letras = palabra.split(\"\");", "H,o,l,a");
		parse("'hola'.split('');", "h,o,l,a");
		parse("'sfsdf sfsdfsdf sgfsdfsd'.split('<');", "sfsdf sfsdfsdf sgfsdfsd");
	}

}
