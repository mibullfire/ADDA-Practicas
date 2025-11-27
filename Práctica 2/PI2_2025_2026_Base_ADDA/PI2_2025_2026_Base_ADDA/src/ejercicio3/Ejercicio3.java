package ejercicio3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;

import us.lsi.common.Pair;
import us.lsi.graphs.views.SubGraphView;


public class Ejercicio3 {

	// == Apartado A == //
	public static Graph<Investigador,Colaboracion> getSubgraph_EJ3A(Graph <Investigador, Colaboracion> g ) {
		Predicate<Investigador> pv1 = i -> i.getFNacimiento() < 1982 || g.edgesOf(i).stream().anyMatch(c -> c.getNColaboraciones() > 5);
		Predicate<Colaboracion> pv2 = c -> c.getNColaboraciones() > 5;
		Graph<Investigador, Colaboracion> vista = SubGraphView.of(g, pv1, pv2);
		return vista;
	}
	
	// == Apartado B == //
	public static List<Investigador> getMayoresColaboradores_E3B (Graph<Investigador,Colaboracion> g) {
		return g.vertexSet().stream()
	            .sorted(Comparator.comparingInt((Investigador i) -> g.edgesOf(i).size()).reversed())
	            .limit(5).toList();
	}

	// == Apartado C == //
	public static Map<Investigador, List<Investigador>> getMapListaColabroradores_E3C(Graph<Investigador, Colaboracion> g) {
	    Map<Investigador, List<Investigador>> res = new HashMap<>();
	    Comparator<Colaboracion> cmp = Comparator.comparing((Colaboracion c) -> c.getNColaboraciones());
	    
	    for (Investigador i : g.vertexSet()) {
	    	List<Investigador> colaboradores = g.edgesOf(i).stream()
    			.sorted(cmp.reversed())
                .map(e -> {
                    Investigador target = g.getEdgeTarget(e);
                    return target.equals(i) ? g.getEdgeSource(e) : target;
                }).toList();
    		res.put(i, colaboradores);
	    }
	    return res;
	}
	
	public static Pair<Investigador,Investigador> getParMasLejano_E3D (Graph<Investigador,Colaboracion> g) {
	   return null;  
		
	}
	
	public static List<Set<Investigador>> getReuniones_E3E (Graph<Investigador,Colaboracion> g) {
		return null;
	}

}
