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
	    
	    // Usamos un Map para agrupar por longitud
	    Map<Integer, List<String>> resultado = new HashMap<>();
	    
	    // Caso límite: si varA o varB son nulos, o varE es nulo, devolvemos mapa vacío
	    if (varA == null || varB == null || varC == null || varD == null || varE == null) {
	        return resultado;
	    }
	    
	    // Creamos el primer elemento
	    EnteroCadena actual = EnteroCadena.of(varA, varB);
	    
	    // Mientras la condición se cumpla (igual que en el Stream.iterate)
	    while (actual.a() < varC) {
	        
	        // Construir el String = elem.s() + varD
	        String cadena = actual.s() + varD;
	        
	        // Filtro: longitud < varE
	        if (cadena.length() < varE) {
	            int len = cadena.length();
	            // Agrupamos por longitud
	            resultado.computeIfAbsent(len, k -> new ArrayList<>()).add(cadena);
	        }
	        
	        // Calcular el siguiente EnteroCadena (equivalente al UnaryOperator nx)
	        int nuevoA = actual.a() + 2;
	        String nuevoS;
	        if (actual.a() % 3 == 0) {
	            // concatenar el número
	            nuevoS = actual.s() + actual.a().toString();
	        } else {
	            // cuidado: proteger contra String vacío
	            int mod = actual.s().length() == 0 ? 0 : actual.a() % actual.s().length();
	            nuevoS = actual.s().substring(mod);
	        }
	        
	        actual = EnteroCadena.of(nuevoA, nuevoS);
	    }
	    
	    return resultado;
	}
	
	public static Map<Integer,List<String>> solucionRecursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		return null;
	}
}
