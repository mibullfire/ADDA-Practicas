package ejercicios;

import java.math.BigInteger;

public class Ejercicio4 {
	
	public static Double funcRecDouble(Integer a) {
		Double res = 5.;
		if (a >= 10) res = Math.sqrt(a*3) * funcRecDouble(a-2);
		return res;
	}
	
	public static BigInteger funcRecBig(Integer a) {
		BigInteger res = BigInteger.valueOf(5);
		if (a >= 10) res = BigInteger.valueOf(a*3).sqrt().multiply(funcRecBig(a-2));
		return res;
	}
	
	public static Double funcItDouble(Integer a) {
		Double res = 5.;
		while (!(a<10)) {
			res = res * Math.sqrt(a*3);
			a -= 2;
		}
		return res;
	}
	
	public static BigInteger funcItBig(Integer a) {
		BigInteger res = BigInteger.valueOf(5);
		while (!(a<10)) {
			res = BigInteger.valueOf(a*3).sqrt().multiply(res);
			a -= 2;
		}
		return res;
	}

}