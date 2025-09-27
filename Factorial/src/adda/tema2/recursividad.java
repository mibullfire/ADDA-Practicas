package adda.tema2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class recursividad {
	
	// PASAR DE RECURSIVO NO FINAL A RECURSIVO FINAL
	
	static long factorial(int n) {
		long r;
		if (n==0) {
			r = 1;
		} else {
			r = factorial(n-1) * n;
		}
		return r;
	}
	
	/* Convertir la función anterior a recursiva final:
	 * 1) Añadir un parámetro del mismo tipo que la que devuelva la funcion original.
	 * 2) Mantenemos la misma condición para el caso base y recurisvo.
	 * 3) En el resultado en el recursivo debe ser la llamada a la función final manteniendo los mismos argumentos para los parametros
	 * de la funcion original.
	 * 4) Para el nuevo parámetro aplicaremos la operación de combinación de la función original si es conmutativa. En otro caso habrá que adaptarla.
	 * 5) En la llamada inicial debemos inicializar el nuevo parámetro con el elemento nuetro de la operación de combinación.
	 * 6) En el caso base combinamos (como en el paso 4) el antiguo resultado de la función original para el caso base y el nuevo parámetro.
	*/
	
	public static long factorialFnl(int n) {
		return factorialFnl(n, 1);
	}
	
	private static long factorialFnl(int n, long a) {
		long r;
		if (n==0) {
			r = 1;
		} else {
			r = factorialFnl(n-1, a*n);
		}
		return r;
	}
	
	// Problemas con Memoria (Fibonacci)
	public static BigInteger fbcm(Integer n) {
		return fbcm(n, new HashMap<>()); 
	}
	
	public static BigInteger fbcm(Integer n, Map<Integer, BigInteger> mem) {
		BigInteger r;
		if(mem.containsKey(n)) r=mem.get(n);
		else if (n==0) {
			r = BigInteger.ZERO;
		} else if (n==1) {
			r = BigInteger.ONE;
		} else {
			r = fbcm(n-1, mem).add(fbcm(n-2, mem));
			mem.put(n, r);
		}
		return r;
	}
	
	public static void main(String... args) {
		System.out.println(fbcm(6));
	}
	
	static long a, b = 1; 	// Valores iniciales, son necesarios, en este problema en verdad no afectan
							// en otros puede que sí.
	
	// Recursivo Múltiple a Forma Iterativa Múltiple //BOTTOM-UP
	static Long fbIt(Long n) {
		// definimos indice i, con el caso base menor:
		Integer i = 0;
		// Añadimos tantas variables locales como casos base existan y le asignamos los casos bases:
		Long v = 0l; // Invariante: v = fib(i)    	// "Invariante": relación que se mantiene entre valores aunque los valores cambien
		Long u = 1l; // Invariante: u = fib(i+1)	// Si la i la aumento en uno, ambos tienen que aumentar en 1
		
		while (i < n) {
			// <i', v', u'> = <i+1, fib(i'), fib(i'+1)> = <i+1, fib(i+1), fib(i+2)> = 
			// = <i+1, u, a*fib(i+1)+b*(i)> = <i+1, u, a*u+b*v>
			i++;
			Long u0 = u;
			u = a*u+b*v; // u + v (la suma de los dos anteriores)
			v = u0;
		}
		// Cuando salimos del bucle i = n -> fib(n) = v
		return v;
	}
	
	// Página 25
	public static Integer g(Integer a, Integer b) {
		Integer r;
		if (a < 2 && b < 2) {
			r = a + b*b;
		} else if (a < 2 || b < 2) {
			r= a*a + b;
		} else {
			r = g(a/2, b - 1) + g(a/3, b - 2) + g(a - 2, b/4);
		}
		return r;
	}
	
	// g con memoria:
	record Tupla(Integer a, Integer b) {};
	
	public static Integer gcm(Integer a, Integer b) {
		Map<Tupla, Integer> mem = new HashMap<Tupla, Integer>();
		Integer r = gcm(a, b, mem);
		return r;
	}
	
	public static Integer gcm(Integer a, Integer b, Map<Tupla, Integer> mem) {
		Integer r;
		Tupla t = new Tupla(a, b);
		if (mem.containsKey(t)) r = mem.get(t);
		else if (a < 2 && b < 2) {
			r = a + b*b;
			mem.put(t, r);
		} else if (a < 2 || b < 2) {
			r= a*a + b;
			mem.put(t, r);
		} else {
			r = gcm(a/2, b - 1, mem) + gcm(a/3, b - 2, mem) + gcm(a - 2, b/4, mem);
			mem.put(t, r);
		}
		return r;
	}
	
	// Nota: esto es mucho más eficiente que la solución sin memoria
	
	// Usar bottom-up para pasar la funcion g a iterativa:
	
		/* Nota: en este código se muestra una matriz, y como hacer los ejercicios que tienen n casos básicos, 
		 * y no se pueden definir facilmente.
		 * 		El valor de g(a,b) se almacena en una posicion de la matriz para los casos bases
		 *
		 *
		 *		  0   1   2   a
		 * 		+---+---+---+
		 * 	  0 | 0 | 1 | . |
		 * 		+---+---+---+
		 * 	  1 | 1 | 2 | . |
		 * 		+---+---+---+  
		 * 	  2 | . | . |   |  g(2,2) = g(1,1) + g(0,0) + g(0,0) = 2 + 0 + 0 = 2
		 * 		+---+---+---+
		 * 	  b
		 * 
		 * 
		 * 		Para la matriz necesitamos dos bucles, desde 0 hasta a y desde 0 hasta b.
		 */
		public static Integer gIter(Integer a, Integer b) {
			Integer[][] m = {};
			
			Integer v = null;
			
			for (int i = 0; i <= a; i++) {
				for (int j = 0; j <= b; j++) {
					if (i < 2 && j < 2) 	v = i + j*j;
					else if(i < 2 || j < 2) v = i*i + j;
					else {
						v = m[i/2][j-1] +
							m[i/3][i-2] +
							m[i-2][j/4];
					}
					m[i][j] = v;
				}
			}
			
			return m[a][b];
		}
		
		// Mismo ejercicio pero usando un map en vez de una matriz.
		public static Integer gIterTupla(Integer a, Integer b) {
			Map<Tupla, Integer> m = new HashMap<Tupla, Integer>();
			Integer r;
			
			for (int i = 0; i <= a; i++) {
				for (int j = 0; j <= b; j++) {
					Tupla t = new Tupla(i, j);
					if (i < 2 && j < 2) 	r = i + j*j;
					else if(i < 2 || j < 2) r = i*i + j;
					else {
						r = m.get(new Tupla(i/2, j-1)) +
							m.get(new Tupla(i/3, j-2)) +
							m.get(new Tupla(i-2, j/4));
					}
					m.put(t, r);
				}
			}
			
			return m.get(new Tupla(a, b));
		}
	
	

}
