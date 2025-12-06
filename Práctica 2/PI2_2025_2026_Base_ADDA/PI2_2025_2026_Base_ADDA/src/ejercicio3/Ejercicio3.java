package ejercicio3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Pair;
import us.lsi.graphs.views.SubGraphView;


public class Ejercicio3 {

	// == Apartado A == //
	public static Graph<Investigador,Colaboracion> getSubgraph_EJ3A(Graph <Investigador, Colaboracion> g ) {
		Predicate<Investigador> pv1 = i -> i.getFNacimiento() < 1982 || g.edgesOf(i).stream().anyMatch(c -> c.getNColaboraciones() > 5);
		Predicate<Colaboracion> pv2 = c -> c.getNColaboraciones() > 5;
		Graph<Investigador, Colaboracion> vista = SubGraphView.of(g, pv1, pv2);
				
		GraphColors.toDot(g,"ficheros_generados/PI2E3_ApartadoA.gv",
				v->String.valueOf("INV-"+v.getId()+" "+v.getFNacimiento()), //que etiqueta mostrar en vertices y aristas
				e->String.valueOf(e.getNColaboraciones().intValue()),
				v->GraphColors.colorIf(Color.blue, vista.containsVertex(v)),
				e->GraphColors.colorIf(Color.blue, vista.containsEdge(e)));
		
		return vista;
	}
	
	// == Apartado B == //
	public static List<Investigador> getMayoresColaboradores_E3B (Graph<Investigador,Colaboracion> g) {
		List<Investigador> res = g.vertexSet().stream()
	            .sorted(Comparator.comparingInt((Investigador i) -> g.edgesOf(i).size()).reversed())
	            .limit(5).toList();
		
		GraphColors.toDot(g,"ficheros_generados/PI2E3_ApartadoB.gv",
				v->String.valueOf("INV-"+v.getId()), //que etiqueta mostrar en vertices y aristas
				e->String.valueOf(e.getNColaboraciones().intValue()),
				v->GraphColors.colorIf(Color.blue, Color.green, res.contains(v)),
				e->GraphColors.color(Color.green));
		
		return res;
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
	    
	    List<Colaboracion> C = new ArrayList<Colaboracion>();
		for (Investigador i: res.keySet()) {
			Pair<Investigador, Colaboracion> par = Pair.of(i, g.getEdge(i, res.get(i).getFirst()));
			C.add(par.second());
		}
		GraphColors.toDot(g,"ficheros_generados/PI2E3_ApartadoC.gv",
				v->String.valueOf("INV-"+v.getId()), //que etiqueta mostrar en vertices y aristas
				e->String.valueOf(e.getNColaboraciones().intValue()),
				v->GraphColors.color(Color.black),
				e->GraphColors.colorIf(Color.blue, Color.black, C.contains(e)));
	    
	    return res;
	}
	
	// == Apartado D == //
	public static Pair<Investigador,Investigador> getParMasLejano_E3D (Graph<Investigador,Colaboracion> g) {
		Pair<Investigador, Investigador> res = null;
		
		Integer distancia = -1; // Distancia mínima 0
		for (Investigador fuente: g.vertexSet()) {
			for (Investigador objetivo: g.vertexSet()) {
				if (!fuente.equals(objetivo)) {
					GraphPath<Investigador, Colaboracion> miCamino = BFSShortestPath.findPathBetween(g, fuente, objetivo);
					if(miCamino.getVertexList().size() - 2 > distancia) {
						//Actualizas la pareja y su distancia 
						res = Pair.of(miCamino.getVertexList().get(0), miCamino.getVertexList().get(miCamino.getVertexList().size()-1)); 
						distancia = miCamino.getVertexList().size() - 2; 
					}
				}
			}
		}
		
		GraphPath<Investigador, Colaboracion> gp = BFSShortestPath.findPathBetween(g, res.first(), res.second()); 
		
		GraphColors.toDot(g, "ficheros_generados/PI2E3_ApartadoD.gv", 
				v->"INV-" + v.getId(), 
				e->"", 
				v->GraphColors.colorIf(Color.red, Color.black, gp.getVertexList().contains(v)),
				e->GraphColors.colorIf(Color.red, Color.black, gp.getEdgeList().contains(e))
				);
		
		return res; 		
	}
	
	// == Apartado E == //
	public static List<Set<Investigador>> getReuniones_E3E (Graph<Investigador,Colaboracion> g) {
		return null;
	}

}
