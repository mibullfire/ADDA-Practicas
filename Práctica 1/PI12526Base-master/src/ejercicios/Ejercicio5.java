package ejercicios;

import java.math.BigInteger;

public class Ejercicio5 {

	public static Double ejercicio5ItDouble(Integer n) {
		Double res = 1.;
		
	    for (int k = 7; k <= n; k++) {
	        res = 1 + res * log2(k - 1);
	    }
		
		return res;
	}
	
	public static Double ejercicio5RecDouble(Integer n) {
		if (n>6) return 1 + ejercicio5RecDouble(n-1) * log2(n-1);
		else return 1.;
	}
	
	public static BigInteger ejercicio5ItBigInteger(Integer n) {
		BigInteger res = BigInteger.ONE;
		
	    for (int k = 7; k <= n; k++) {
	        res = res.multiply(BigInteger.valueOf(log2(k - 1))).add(BigInteger.ONE);
	    }
		
		return res;
	}
	
	public static BigInteger ejercicio5RecBigInteger(Integer n) {
		if (n>6) return ejercicio5ItBigInteger(n-1).multiply(BigInteger.valueOf(log2(n - 1))).add(BigInteger.ONE);
		else return BigInteger.ONE;
	}

	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}