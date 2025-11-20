package ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.Set;


import org.jgrapht.Graph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Pair;
import us.lsi.graphs.views.SubGraphView;


public class Ejercicio3 {



	/*
	 * EJERCICIO 3 APARTADO A
	 */
	
	public static Graph<Investigador,Colaboracion> getSubgraph_EJ3A(Graph <Investigador, Colaboracion> g ) {
		
		Graph<Investigador, Colaboracion> vista = SubGraphView.of(g, v->v.getFNacimiento() < 1982, a->a.getNColaboraciones() > 5);
		
		return vista;
	}
	
	public static List<Investigador> getMayoresColaboradores_E3B (Graph<Investigador,Colaboracion> g) {
		return null;
	}

	public static Map<Investigador,List<Investigador>> getMapListaColabroradores_E3C (Graph<Investigador,Colaboracion> g) {
        return null;
	}
	
	public static Pair<Investigador,Investigador> getParMasLejano_E3D (Graph<Investigador,Colaboracion> g) {
	   return null;  
		
	}
	
	public static List<Set<Investigador>> getReuniones_E3E (Graph<Investigador,Colaboracion> g) {
		return null;
	}

}
