package ejercicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
		
		// List<Integer> res = new ArrayList<Integer>();
		
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
	
	public record Tupla(Integer a, Integer b) {};
	
	public static List<Integer> f_funcional (Integer a, Integer b) {
		
		UnaryOperator<Tupla> nx = t -> {
		    if (t.a() > t.b()) {
		        return new Tupla(t.a() % t.b(), t.b() - 1);
		    } else {
		        return new Tupla(t.a() - 2, t.b() / 2);
		    }
		};
		
		
		List<Tupla> tuplas =  Stream.iterate(new Tupla(a, b), t -> !(t.a() < 2 || t.b() < 2), nx).toList();
		
		List<Integer> res = new ArrayList<>(tuplas.stream().map(t -> (t.a() > t.b()) ? t.a() : t.b()).toList());
		
		// fix de chatgpt para el ultimo elemento (es decir, el primero)
	    // -> aquí está el fix: si no hay tuplas incluidas, la tupla terminal es (a,b) inicial,
	    //    en otro caso la tupla terminal es nx.apply(últimaTuplaIncluida)
	    Tupla terminal = tuplas.isEmpty() ? new Tupla(a, b) : nx.apply(tuplas.get(tuplas.size() - 1));
	    res.add(terminal.a() * terminal.b()); // producto con la tupla terminal);
	    
		Collections.reverse(res);
		return res;
	}

}
