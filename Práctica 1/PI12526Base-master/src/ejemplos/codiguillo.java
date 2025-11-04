package ejemplos;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class codiguillo {
	public static Map<String, Long> funcional(Integer a, Integer b, Integer c) {
		return Stream.iterate(a+b, e->e<c, e -> e+a*b)
		.filter(e-> e%2==0).map(e -> "[" + e%10 + "]")
		.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		}
	
	// Stream.iterate(0, x->x<5, x->x+1).filter(x->x%2)
	// 0, 1, 2, 3, 4 --> 2, 4
	
	public static Map<String, Long> iterativillo(Integer a, Integer b, Integer c) {
		Map<String, Long> respuesta = new HashMap<String, Long>();
		Integer e = a+b;
		while (e<c) {
			if(e%2==0) {
				String aux = "[" + e%10 + "]";
				if(respuesta.containsKey(aux)) respuesta.put(aux, respuesta.get(aux)+1l);
				else respuesta.put(aux, 1l);
			}
			e = e+a*b;
		}
		return respuesta;
	}
	
	public static Map<String, Long> recursivillo(Integer a, Integer b, Integer c){
		return recursivillo(a+b, a, b, c, new HashMap<String, Long>());
	}
	private static Map<String, Long> recursivillo(Integer e, Integer a, Integer b, Integer c, Map<String, Long> mapa){
		Map<String, Long> res = mapa;
		if (e<c) {
			if(e%2==0) {
				String aux = "[" + e%10 + "]";
				if(res.containsKey(aux)) res.put(aux, res.get(aux)+1l);
				else res.put(aux, 1l);
			}
			res = recursivillo(e+a*b, a, b, c, res);
		}
		return res;
	}
	
	public static void main(String... args) {
		Integer a = 1000, b = 3005, c = 2005;
		Map<String, Long> fn = funcional(a,b,c);
		//Map<String, Long> iterativillo = iterativillo(a,b,c);
		//Map<String, Long> recursivillo = recursivillo(a,b,c);
		System.out.println(fn);
		//System.out.println(iterativillo);
		//System.out.println(recursivillo);

	}
}
