package ejercicios;

import java.util.HashMap;
import java.util.Map;

public class Ejercicio3 {

	public static Long iterativo(Integer A, Integer B, Integer C) {
		
		Map<Tupla, Long> dp = new HashMap<>();

		// En vez de con tuplas se pueden trabajar con "Trios" Trio.of(a, b, c)
        // Recorremos todos los estados desde abajo hacia arriba
		Long res = null;
        for (int a = 0; a <= A; a++) {
            for (int b = 0; b <= B; b++) {
                for (int c = 0; c <= C; c++) {
                    if (a < 3 || b < 3 || c < 3) {
                        // Caso base
                        res = (long) a + (b * b) + (2 * c);
                    } else if (a>=b && a % b == 0) {
                        // Caso divisible
                        res = dp.get(new Tupla(a - 1, b / 2, c / 2))
                            + dp.get(new Tupla(a - 3, b / 3, c / 3));
                    } else {
                        // Caso no divisible
                        res = dp.get(new Tupla(a / 3, b - 3, c - 3))
                            + dp.get(new Tupla(a / 2, b - 2, c - 2));
                    }
                    dp.put(new Tupla(a, b, c), res);
                }
            }
        }
        return dp.get(new Tupla(A, B, C));
	}

	public static Long recursivo_sin_memoria(Integer a, Integer b, Integer c) {
		if (a < 3 || b < 3 || c < 3) return (long) a + (b*b) + (2*c);
		else if (a>=b && a%b==0) return recursivo_sin_memoria(a-1, b/2, c/2) + recursivo_sin_memoria(a-3, b/3, c/3);
		else return recursivo_sin_memoria(a/3, b-3, c-3) + recursivo_sin_memoria(a/2, b-2, c-2);
	}
	
	// Con memoria (esto incluye la creación de tuplas y una función con distintos parámetros):
	
	record Tupla(Integer a, Integer b, Integer c) {};
	
	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c) {
		Long r = recursivo_con_memoria(a, b, c, new HashMap<Tupla, Long>());
		return r;
	}
	
	private static Long recursivo_con_memoria(Integer a, Integer b, Integer c, Map<Tupla, Long> mem) {
		Long res;
		Tupla t = new Tupla(a, b, c);
		if (mem.containsKey(t)) res = mem.get(t);
		else if (a < 3 || b < 3 || c < 3) {
			res = (long) a + (b*b) + (2*c);
			mem.put(t, res);
		} else if (a>=b &&a%b==0) {
			res = recursivo_con_memoria(a-1, b/2, c/2, mem) + recursivo_con_memoria(a-3, b/3, c/3, mem);
			mem.put(t, res);
		} else {
			res = recursivo_con_memoria(a/3, b-3, c-3, mem) + recursivo_con_memoria(a/2, b-2, c-2, mem);
			mem.put(t, res);
		}
		return res;
	}
}