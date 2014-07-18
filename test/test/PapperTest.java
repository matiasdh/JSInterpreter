package test;

import org.junit.Test;

public class PapperTest extends test.Test {
	@Test
	public void test() throws Exception {
		String test1 = "// Definición de la función\n" +
			"function suma_y_muestra(primerNumero, segundoNumero) {\n" +
			"var resultado = primerNumero + segundoNumero;\n" +
			"alert('El resultado es ' + resultado); }\n" +
			"// Declaración de las variables\n" +
			"var numero1 = 3;\n" +
			"var numero2 = 5;\n" +
			"suma_y_muestra(numero1, numero2);";
		parse(test1, null);
		
		String test2 = "var mensaje1 = 'Mensaje 1'; \n" +
			"var mensaje2 = 'Mensaje 2'; \n" +
			"var mensaje3 = 'Mensaje 3'; \n" +
			" \n" +
			"function muestraMensaje1() { \n" +
			" var mensaje1 = alert(mensaje1); \n" +
			"} \n" +
			" \n" +
			"function muestraMensaje2() { \n" +
			" alert(mensaje2); \n" +
			"} \n" +
			" \n" +
			"function muestraMensaje3() { \n" +
			" alert(mensaje3); \n" +
			"} \n" +
			" \n" +
			"// Comienza el programa principal \n" +
			"muestraMensaje1(); \n" +
			"muestraMensaje2(); \n" +
			"muestraMensaje3(); \n";
		parse(test2, null);
	}
}
