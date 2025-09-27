package adda.factorial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.geometria.Punto2D;

public class Pruebecitas {
	
	public static void main(String[] args) {
		Integer res = f2(3);
		System.out.println(res);
		Punto2D todo_ok = Punto2D.origen();
		System.out.println(todo_ok);
		
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		
		List<Integer> res2 = hastaMayorN(lista, 3);
		System.out.println(res2);
	}
	
	public static Integer f(Integer n) {
	    Integer r;
	    if (n == 0) {
	        r = 1;
	    } else {
	        r = n * f(n - 1);
	    }
	    return r;
	}
	public static Integer f2(Integer n) {
		Integer r = 1;
		for (int i = n; i > 0; i--) {
			r = r * n;
			n = n - 1;
		}
		return r;
	}
	
	public static Integer Fibo(Integer n) {		
		Integer r;
		if (0 <= n && n <= 1) {
			r = n;
		} else {
			r = Fibo(n-1) + Fibo(n-2);
		}
		return r;
	}
	
	public static List<Integer> hastaMayorN(List<Integer> lista, Integer n){
		return lista.stream().takeWhile(e-> e <= n).collect(Collectors.toList());
	}
}
