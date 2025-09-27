package adda.transformaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import us.lsi.common.LongPair;
import us.lsi.math.Math2;
import us.lsi.streams.Stream2;

public class ejemplosTeoria {
	
	public static void main(String... args) {
		// boolean res = esPrimoFnl(4l);
		// System.out.println(res);
		List<Long> prueba = primosEnIntervalo(2l,13l);
		System.out.println(prueba);
		List<LongPair> res2 = primosParIterativo(2l, 10l, 2);
		System.out.println(res2);
		Map<Long, Long> res3 = repDivisores(2l, 13l);
		System.out.println(res3);
		
		// Pruebas
		Map<Long, Long> divs = repDivisoresIt(2l, 13l);
		System.out.println(divs);
		
		String eje11 = eje11recuNoFinal(56, 69);
		System.out.println(eje11);
		
		String eje11x = eje11recuFinal(56, 69);
		System.out.println(eje11x);
		
		String eje11y = eje11iterAtiva(10, 10);
		System.out.println(eje11y);
	}
	
	/*
	 * A continuación se detallan ejemplos ya descritos en los apuntes de teoría,
	 * están centrados en tranformaciones, y su objetivo es ver una misma función
	 * pero escrita de manera completamente diferente.
	 */
	// Cálculo para ver si un número es primo o no.
	static boolean esPrimo1(Long n) {
		Long sqrt = (long) Math.sqrt((double) n);
		return LongStream.rangeClosed(2, sqrt).noneMatch(x->Math2.esDivisible(n, x));
	}
	
	static boolean esPrimo2(Long n) {
		Long sqrt = (long) Math.sqrt((double) n);
		Long e = 2l;
		Boolean b = false;
		while (e <= sqrt && !b) {
			b = Math2.esDivisible(n, e);
			e++;
		}
		return !b;
	}
	
	static boolean esPrimoFnl(Long n) {
		Long sqrt = (long) Math.sqrt((double) n);
		Long e = 2l;
		Boolean b = false;
		b = esPrimoFnl(e, b, sqrt, n);
		return !b;
	}
	// Recursivo Final: Los parámetros del while se ponen como variables dentro de la recursiva final.
	static boolean esPrimoFnl(Long e, Boolean b, Long sqrt, Long n) {
		if (e<=sqrt && !b) {
			b = esPrimoFnl(e+1,Math2.esDivisible(n,e),sqrt,n);
		}
		return b;
	}
	
	// Sumar los enteros contenidos en una lista
	static Integer ejemplo1(List<Integer> ls) {
		return ls.stream().mapToInt(x->x).sum();
	}
	static Integer ejemplo1for(List<Integer> ls) {
		Integer a = 0;
		for (Integer e:ls) {
			a = a + e;
		}
		return a;
	}
	static Integer ejemplo1while(List<Integer> ls) {
		Integer ac = 0;
		Integer i = 0;
		while(i < ls.size()) {
			Integer e = ls.get(i);
			ac = ac + e;
			i++;
		}
		return ac;
	}
	static Integer ejemplo1Fnl(List<Integer> ls) {
		Integer e = 0;
		Integer ac = 0;
		ac = ejemplo1Fnl(e, ac, ls);
		return ac;
	}
	static Integer ejemplo1Fnl(Integer i, Integer b, List<Integer> ls) {
		if (i < ls.size()) {
			b = ejemplo1Fnl(i+1, b + ls.get(i), ls);
		}
		return b;
	}
	
	// Dada una lista de enteros, comprobar si todos son impares
	static boolean ejemplo2(List<Integer> ls) {
		return ls.stream().allMatch(x->x%2==1);
	}
	static boolean ejemplo2for(List<Integer> ls) {
		boolean a = true;
		for (Integer e: ls) {
			a = e%2==1;
			if (!a) break;
		}
		return a;
	}
	static boolean ejemplo2while(List<Integer> ls) {
		boolean res = true;
		Integer i = 0;
		while (i<ls.size() && res) {
			res = ls.get(i)%2==1;
			i++;
		}
		return res;
	}
	static boolean ejemplo3Fnl(List<Integer> ls) {
		boolean res = true;
		Integer i = 0;
		res = ejemplo3Fnl(i, res, ls);
		return res;
	}
	static boolean ejemplo3Fnl(Integer i, boolean res, List<Integer> ls) {
		if (i<ls.size() && res) {
			res = ejemplo3Fnl(i + 1, ls.get(i)%2==1, ls);
		}
		return res;
	}
	
	// Siguiente primo
	static Long siguientePr(Long n) {
		Long e0 = n%2==0?n+1:n+2;
		return Stream.iterate(e0, e->e+2).filter(e->Math2.esPrimo(e)).findFirst().get();
	}
	static Long siguientePrimoIterativo(Long n) { // forma Iterativa
		Long e = n%2==0?n+1:n+2;
		Long r = null;
		while(r==null) {
			if(esPrimo2(e)) {
				r = e;
			}
			e = e+2;
		}
		return r;
	}
	
	// Ejercicio transparencia 52
	// Obtener una lista con los primos menores que n
	static List<Long> primosFuncional(Long n){
		if (n >= 2) 
			return Stream.iterate(2l, x-> x < n, x -> siguientePr(x)).filter(x->esPrimo2(x)).collect(Collectors.toList());
		else
			return new ArrayList<Long>();
	}
	static List<Long> primosFuncionalIntervalo(Long primero, Long ultimo){
		if (primero >= 2l && ultimo > primero) {
			return Stream.iterate(primero, i-> i <= ultimo,i -> siguientePr(i)).filter(x->esPrimo2(x)).toList();
		}
		else return new ArrayList<Long>();
	}
	
	static List<Long> primosIte(Long n){
		List<Long> res = new ArrayList<Long>();
		for(long i=0; i<n; i++) {
			if (esPrimo2(i)) {
				res.add(i);
			}
		}
		return res;
	}
	
	// Página 55
	static List<LongPair> primosPar(Long m, Long n, Integer k) {
		Stream<Long> r = Stream.iterate(Math2.siguientePrimo(m-1), x->x<n, x->Math2.siguientePrimo(x));
		List<LongPair> res = Stream2.consecutivePairs(r).map(p->LongPair.of(p)).filter(t->t.second() - t.first() == k).toList();
		return res;
	}
	// Ejercicio: Hacer la función anterior de forma iterativa.
	
	static List<Long> primosEnIntervalo(Long m, Long n){
		Long i = m;
		List<Long> res = new ArrayList<Long>();
		while (i <= n) {
			if (Math2.esPrimo(i)) {
				res.add(i);
			}
			i = Math2.siguientePrimo(i);
		}
		return res;
	}
	static List<LongPair> primosParIterativo(Long m, Long n, Integer k) {
		List<Long> primosIntervalo = primosEnIntervalo(m, n); // Intentar hacer sin esta funcion, tal y como esta en su forma funcional
		List<LongPair> res = new ArrayList<LongPair>();
		Integer i = 0;
				
		while (i<primosIntervalo.size()-1) {
			Long primero = primosIntervalo.get(i);
			Long segundo = primosIntervalo.get(i+1);
			
			if (segundo-primero==k) {
				res.add(LongPair.of(primero, segundo));
			}
			i++;
		}
		return res;
	}
	
	// Ejercicio: hacer la versio imperativa y recursiva.
	static Stream<Long> divisores(Long n){
		return Stream.iterate(2l, x->x <= (long) Math.sqrt(n), x->x+1).filter(x->n%x==0);
	}
	static Map<Long, Long> repDivisores(Long m, Long n){
		return Stream.iterate(m, x -> x < n, x -> x + 1).flatMap(x -> divisores(x))
				.collect(Collectors.groupingBy(Long::longValue, Collectors.counting()));
	}
	// Iterativa
	static List<Long> divisoresIt(Long n){
		List<Long> res = new ArrayList<Long>();
		Long i = 2l;
		while (i <= (long) Math.sqrt(n)) {
			if (n%i==0) res.add(i);
			i++;
		}
		return res;
	}
	
	static Map<Long, Long> repDivisoresIt(Long m, Long n){
		Map<Long, Long> res = new HashMap<Long, Long>();
		for (long i = m; i < n; i++) {
			for (Long j: divisoresIt(i)) {
				if (res.containsKey(j)) {
					Long cuenta = res.get(j);
					res.put(j, cuenta+1);
				} else {
					res.put(j, 1l);
				}
			}
		}
		return res;
	}
	
	// Ejercicio 11
	/*
	 * Dada la siguiente definición recursiva (que toma como entrada 2 números enteros positivos y devuelve una cadena)
	 */
	
	static String eje11recuNoFinal(Integer a, Integer b) {
		String res = null;
		if (a < 5 || b < 5) {
			res = String.format("(%d)", a*b);
		} else {
			res = String.format("%d", a+b) + eje11recuNoFinal(a/2, b-2); 
		}
		return res;
	}
	
	static String eje11recuFinal(Integer a, Integer b) {
		return aux("", a, b);
	}
	static String aux(String ac, Integer a, Integer b) {
		String res = null;
		if (a < 5 || b < 5) {
			res = ac + String.format("(%d)", a*b);
		} else {
			res = aux(ac + String.format("%d", a+b), a/2, b-2);
		}
		return res;
	}
	
	static String eje11iterAtiva(Integer a, Integer b) {
		String res = "";
		while (!(a < 5 || b < 5)) {
			res = String.format("%s%d", res, a+b);
			a = a/2;
			b = b-2;
		}
		return res + String.format("(%d)", a*b);
	}
	
	// falta la parte funcional, en ella hay que construir una tupla usando un record, si no no se puede hacer.
}
