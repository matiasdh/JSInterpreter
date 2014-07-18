function fibonacci(n) {
   if (n < 2){
     return 1;
   }else{
     return fibonacci(n - 2) + fibonacci(n - 1);
   }
}
alert("SE ESPERA FIB(7) = 21, SE OBTUVO " + fibonacci(7));