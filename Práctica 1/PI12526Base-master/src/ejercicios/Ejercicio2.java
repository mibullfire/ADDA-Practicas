package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio2 {
	
	// ===== RECURSIVA NO FINAL ===== //
	public static List<Integer> f_RNF (Integer a, Integer b) {
		List<Integer> res = new ArrayList<Integer>();
		if (a < 2 || b < 2) res.add(a*b);
		else if (a > b) {
			List<Integer> rec = f_RNF(a%b, b-1);
			rec.add(a);
			return rec;
		} else {
			List<Integer> rec = f_RNF(a-2, b/2);
			rec.add(b);
			return rec;
		}
		return res;
	}
	
	// ===== ITERATIVA ===== //
	public static List<Integer> f_it (Integer a, Integer b) {
		List<Integer> res = new ArrayList<Integer>();
		while (!(a < 2 || b < 2)) {
			if (a > b) {
			res.add(0, a); // Con el 0 decimos que se ponga en la primera posición (NO HAY QUE HACER REVERSED)
			a = a%b;
			b--;
			} else {
				res.add(0, b);
				a -= 2;
				b /= 2;
			}
		}
		res.add(0, a*b);
		return res;
	}
	
	// ===== RECURSIVA FINAL ===== //
	public static List<Integer> f_RF (Integer a, Integer b) {
		List<Integer> ac = new ArrayList<Integer>();
		return f_RF(a, b, ac);
	}
	
	public static List<Integer> f_RF (Integer a, Integer b, List<Integer> ac) {	
		if (a < 2 || b < 2) ac.add(0,a*b);
		else if (a > b) {
			ac.add(0,a);
			return f_RF(a%b, b-1, ac);
		}
		else {
			ac.add(0,b);
			return f_RF(a-2, b/2, ac);
		}
		return ac;	
	}
	
	// ===== FUNCIONAL ===== //
	private static record Tupla2(List<Integer> ac, Integer a, Integer b) {
		public static Tupla2 of(List<Integer> ac, Integer a, Integer b) {
			return new Tupla2(ac, a, b);
		}
		public static Tupla2 first(Integer a, Integer b) {
			return Tupla2.of(new ArrayList<Integer>(), a, b);
		}
		public Tupla2 next() {
			Integer nuevo_a, nuevo_b;
	        if(a > b) {
	        ac.add(0,a);
	        nuevo_a = a % b;
	        nuevo_b = b-1;
	      } else {
	        ac.add(0,b);
	        nuevo_a = a - 2;
	        nuevo_b = b / 2;
	      }
	      return Tupla2.of(ac, nuevo_a, nuevo_b);
		}
	}
	
	public static List<Integer> f_funcional(Integer a, Integer b) {
		Tupla2 t = Stream.iterate(Tupla2.first(a,b), e->e.next()).filter(e->e.a()<2||e.b()<2)
				.findFirst().get();
		t.ac().add(0, t.a()*t.b());
		return t.ac();
	}
}