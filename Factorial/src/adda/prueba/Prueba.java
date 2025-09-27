package adda.prueba;

import java.util.List;

public class Prueba {
	
	// Clase 11/09/25
	
	static Double sumLista(List<Double> list) {
		Integer i = 0;
		Double b = 0.;
		while(i < list.size()) {
			b = b + list.get(i);
			i++;
		}
		return b;
	}
	
	static Double sumListaRec(List<Double> list) {
		Integer i =  0;
		Double b = 0.;
		b = sumListaRec(i, b, list);
		return b;
	}
	
	static Double sumListaRec(Integer i, Double b, List<Double> list) {
		if(i < list.size()) {
			b = sumListaRec(i + 1, b + list.get(i), list);
		}
		return b;
	}


}
