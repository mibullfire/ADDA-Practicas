package ejercicios;

public class Ejercicio3 {

	public static Long iterativo(Integer a, Integer b, Integer c) {
		
		while (!(a < 3 || b < 3 || c < 3)) {
			
			if (a%b==0) {
				
			} else {
				
			}
			
		}
		return (long) a + (b*b) + (2*c);
	}

	public static Long recursivo_sin_memoria(Integer a, Integer b, Integer c) {

		if (a < 3 || b < 3 || c < 3) return (long) a + (b*b) + (2*c);
		else if (a%b==0) return recursivo_sin_memoria(a-1, b/2, c/2) + recursivo_sin_memoria(a-3, b/3, c/3);
		else return recursivo_sin_memoria(a/3, b-3, c-3) + recursivo_sin_memoria(a/2, b-2, c-2);
		
	}

	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c) {
		return 0L;
	}

}
 