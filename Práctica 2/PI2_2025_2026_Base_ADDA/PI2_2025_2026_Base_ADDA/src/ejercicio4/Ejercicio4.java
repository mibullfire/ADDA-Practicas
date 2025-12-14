package ejercicio4;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio4 {

	public static GraphPath<Interseccion,Calle> getSubgraph_EJ4A(String mIn, String mOut,Graph <Interseccion, Calle> g, String ftest) {
		Interseccion origen = g.vertexSet().stream().filter(x->x.getNombre().equals(mIn)).findFirst().get();
		Interseccion destino = g.vertexSet().stream().filter(x->x.getNombre().equals(mOut)).findFirst().get();
		
		// Cuando ya tenemos los dos vértices aplicamos el algoritmo de Dijkstra:
		GraphPath<Interseccion, Calle> res = DijkstraShortestPath.findPathBetween(g, origen, destino);
		
		GraphColors.toDot(g,ftest,
				x->x.getNombre(), x->""+x.getId(),
				v->GraphColors.colorIf(Color.blue, Color.black, res.getVertexList().contains(v)),
				e->GraphColors.colorIf(Color.blue, Color.black, res.getEdgeList().contains(e)));
		
		System.out.println(ftest + " generado!");
		
		return res;
	}
	
	public static GraphPath<Interseccion,Calle> getRecorrido_E4B(Graph <Interseccion, Calle> g) {
		HeldKarpTSP<Interseccion, Calle> TSP = new HeldKarpTSP<Interseccion, Calle>();
		GraphPath<Interseccion, Calle> res = TSP.getTour(g); 
		
		GraphColors.toDot(g, "ficheros_generados/EJ4ApartadoB.gv",
				v->v.getNombre(), 
				e->"" + e.getEsfuerzo(), 
				v->GraphColors.colorIf(Color.blue, Color.black, res.getVertexList().contains(v)), 
				e->GraphColors.colorIf(Color.blue, Color.black, res.getEdgeList().contains(e))
				);
		
		System.out.println("ficheros_generados/EJ4ApartadoB.gv" + " generado!");
		
		return res; 
	}
	
	public static Graph<Interseccion,Calle> getRecorridoMaxRelevante_E4C(Set<Calle> cs,Graph <Interseccion, Calle> g, String ftest) {
		
		// Predicados:
		Predicate<Calle> callesNoCortadas = c -> !(cs.stream().mapToInt(Calle::getId).anyMatch(cId -> cId == c.getId()));
		Function<Set<Interseccion>, Integer> calculoRelevancia = s -> s.stream().mapToInt(Interseccion::getRelevancia).sum(); 
		
		// Grafo con todos los vértices y calles que no están cortadas:
		Graph<Interseccion, Calle> gc = SubGraphView.ofEdges(g, callesNoCortadas);
		
		ConnectivityInspector<Interseccion, Calle> ci = new ConnectivityInspector<Interseccion, Calle>(gc);
		
		// Agrupar los caminos por su relevancia:
		Map<Integer, List<Interseccion>> relevanciaCamino = ci.connectedSets().stream().collect(Collectors.toMap(calculoRelevancia, s->s.stream().toList()));
		
		List<Interseccion> caminoMasRelevante = relevanciaCamino.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList().getLast().getValue();
		
		GraphColors.toDot(gc, ftest, 
				v->"INT-" + v.getId() + " Relevancia " + v.getRelevancia(), 
				e->"", 
				v->GraphColors.colorIf(Color.red, Color.black, caminoMasRelevante.contains(v)), 
				e->GraphColors.colorIf(Color.red, Color.black, 
						caminoMasRelevante.contains(gc.getEdgeSource(e)) || caminoMasRelevante.contains(gc.getEdgeTarget(e)))
				);
		
		return gc;
	}
		
}
