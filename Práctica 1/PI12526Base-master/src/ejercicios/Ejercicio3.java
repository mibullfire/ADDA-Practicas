package ejercicios;

import java.util.HashMap;
import java.util.Map;


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
	
	// Con memoria (esto incluye la creación de tuplas y una función con distintos parámetros):
	
	
	record Tupla(Integer a, Integer b, Integer c) {};
	
	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c) {
		Map<Tupla, Long> mem = new HashMap<Tupla, Long>();
		Long r = recursivo_con_memoria(a, b, c, mem);
		return r;
	}
	
	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c, Map<Tupla, Long> mem) {
		Long res;
		Tupla t = new Tupla(a, b, c);
		if (mem.containsKey(t)) res = mem.get(t);
		else if (a < 3 || b < 3 || c < 3) {
			res = (long) a + (b*b) + (2*c);
			mem.put(t, res);
		} else if (a%b==0) {
			res = recursivo_con_memoria(a-1, b/2, c/2, mem) + recursivo_con_memoria(a-3, b/3, c/3, mem);
			mem.put(t, res);
		} else {
			res = recursivo_con_memoria(a/3, b-3, c-3, mem) + recursivo_con_memoria(a/2, b-2, c-2, mem);
			mem.put(t, res);
		}
		return res;
	}
	

}
 