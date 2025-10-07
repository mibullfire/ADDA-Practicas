package ejercicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Ejercicio2 {
	
	public static List<Integer> f_RNF (Integer a, Integer b) {
		
		List<Integer> res = new ArrayList<Integer>();
		
		if (a < 2 || b < 2) res.add(a*b);
		else if (a > b) {
			List<Integer> rec = f_RNF(a%b, b-1);
			rec.add(a);
			return rec;
		}
		else {
			List<Integer> rec = f_RNF(a-2, b/2);
			rec.add(b);
			return rec;
		}
		return res;
	}
	
	public static List<Integer> f_it (Integer a, Integer b) {
		
		List<Integer> res = new ArrayList<Integer>();
		
		while (!(a < 2 || b < 2)) {
			if (a > b) {
			res.add(a);
			a = a%b;
			b--;
			} else {
				res.add(b);
				a -= 2;
				b /= 2;
			}
		}
		res.add(a*b);
		return res.reversed();
	}
	
	public static List<Integer> f_RF (Integer a, Integer b) {
		List<Integer> ac = new ArrayList<Integer>();
		return f_RF(a, b, ac);
		
	}
	
	public static List<Integer> f_RF (Integer a, Integer b, List<Integer> ac) {
				
		if (a < 2 || b < 2) ac.add(a*b);
		else if (a > b) {
			ac.add(a);
			return f_RF(a%b, b-1, ac);
		}
		else {
			ac.add(b);
			return f_RF(a-2, b/2, ac);
		}
		
		return ac.reversed();	
		
	}
	
	private static record Tupla2(List<Integer> ac, Integer a, Integer b) {
		public static Tupla2 of(List<Integer> x, Integer a, Integer b) {
			return new Tupla2(x, a, b);
		}
		public static Tupla2 first(Integer a, Integer b) {
			return Tupla2.of(new ArrayList<>(), a, b);
		}
		public Tupla2 next() {
	        List<Integer> res = new ArrayList<>(ac);
	        Integer nuevo_a = a;
	        Integer nuevo_b = b;
	        if(a > b) {
	        res.add(0,a);
	        nuevo_a = a % b;
	        nuevo_b --;
	      } else {
	        res.add(0,b);
	        nuevo_a = a - 2;
	        nuevo_b = b / 2;
	      }
	      return Tupla2.of(res, nuevo_a, nuevo_b);
		}
	}
	
	public static List<Integer> f_funcional(Integer a, Integer b) {
		Tupla2 t = Stream.iterate(Tupla2.first(a,b), e->e.next()).filter(e->e.a()<2||e.b()<2)
				.findFirst().get();
		t.ac().add(0, t.a()*t.b());
		return t.ac();
	}
}
