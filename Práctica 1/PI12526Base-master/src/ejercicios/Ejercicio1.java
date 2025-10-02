package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Ejercicio1 {
	
	// Record para poder usar la funcion (definido por mi): 
	public record EnteroCadena(Integer a, String s) {
	    public static EnteroCadena of(Integer a, String s) {
	        return new EnteroCadena(a, s);
	    }
	}
	
	// Del enunciado:
	public static Map<Integer,List<String>> solucionFuncional(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a()+2,
				elem.a()%3==0?
					elem.s()+elem.a().toString():
					elem.s().substring(elem.a()%elem.s().length()));
		};			
		
		
		return Stream.iterate(EnteroCadena.of(varA,varB), elem -> elem.a() < varC, nx)
					.map(elem -> elem.s() + varD)
					.filter(nom -> nom.length() < varE)
					.collect(Collectors.groupingBy(String::length));
	}
	
	public static Map<Integer, List<String>> solucionIterativa(Integer varA, String varB, Integer varC, String varD, Integer varE) {
	    
		EnteroCadena e = EnteroCadena.of(varA, varB);
	    Map<Integer, List<String>> ac = new HashMap<>();

	    while (e.a() < varC) {
	        String s = e.s() + varD;
	        if (s.length() < varE) {
	        	if (ac.containsKey(s.length())) {
	        	    ac.get(s.length()).add(s);
	        	} else {
	        	    List<String> nuevaLista = new ArrayList<>();
	        	    nuevaLista.add(s);
	        	    ac.put(s.length(), nuevaLista);
	        	}
	        }
	        
	        e = EnteroCadena.of(e.a()+2, e.a()%3==0?
					e.s()+e.a().toString():
					e.s().substring(e.a()%e.s().length()));
	       
	    }

	    return ac;
	}
	
	public static Map<Integer,List<String>> solucionRecursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		EnteroCadena e = EnteroCadena.of(varA, varB);
		return recFinal(e, new HashMap<>(), varA, varB, varC, varD, varE);
	}
	
	private static Map<Integer, List<String>> recFinal(EnteroCadena e, Map<Integer, List<String>> ac, Integer varA, String varB, Integer varC, String varD, Integer varE){
		Map<Integer, List<String>> r = ac;
		if (e.a() < varC) {
			
	        String s = e.s() + varD;
	        if (s.length() < varE) {
	        	if (ac.containsKey(s.length())) {
	        	    ac.get(s.length()).add(s);
	        	} else {
	        	    List<String> nuevaLista = new ArrayList<>();
	        	    nuevaLista.add(s);
	        	    ac.put(s.length(), nuevaLista);
	        	}
		    }

	        e = EnteroCadena.of(e.a() + 2, e.a()%3==0?
					e.s()+e.a().toString():
					e.s().substring(e.a()%e.s().length()));
	        
	        r = recFinal(e, ac, varA, varB, varC, varD, varE);
		}
		
		return r;
	}
}
