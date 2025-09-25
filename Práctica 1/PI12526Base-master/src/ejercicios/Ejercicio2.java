package ejercicios;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Integer> f_funcional (Integer a, Integer b) {
		return null;
	}

}
