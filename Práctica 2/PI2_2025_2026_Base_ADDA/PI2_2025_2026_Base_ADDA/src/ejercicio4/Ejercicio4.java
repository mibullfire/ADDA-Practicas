package ejercicio4;


import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;


public class Ejercicio4 {

	public static GraphPath<Interseccion,Calle> getSubgraph_EJ4A(String mIn, String mOut,Graph <Interseccion, Calle> g, String ftest) {
		var alg = new DijkstraShortestPath<>(g);
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
		return null;
	}
		
}
