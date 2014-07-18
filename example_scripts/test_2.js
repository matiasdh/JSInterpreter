alert("Se probara la function exponencial");
var power = function (base, exponent) {
  if (exponent == 0) {
    return 1;
  } else {
    return base * power(base, exponent - 1);
  }
};
var text_to_print = "Se espera 2^2=4, retorna: " + power(2,2) + "\n" + 
"Se espera 2^2=4, retorna: ".concat(power(2,3)).concat("\n") + 
"Se espera 2^4=16, retorna: " + power(2,4);
alert(text_to_print);

function factorial (n, accumulator) {
	if (n == 0) {
		return accumulator;
	} else {
	return factorial(n - 1, n * accumulator);
	}
};

fin_linea = "\n";
text_to_print = "Se probara la function factorial.".concat(fin_linea.concat(fin_linea)) +
"Se espera 120, se obtuvo " + factorial(5, 1);
alert(text_to_print);