package ejercicio4;


import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;


public class Ejercicio4 {

	public static GraphPath<Interseccion,Calle> getSubgraph_EJ4A(String mIn, String mOut,Graph <Interseccion, Calle> g, String ftest) {
		var alg = new DijkstraShortestPath<>(g);
		Interseccion origen = Interseccion.ofId(Integer.valueOf(mIn));
		Interseccion destino = Interseccion.ofId(Integer.valueOf(mOut));
		System.out.println(origen);
		System.out.println(g.vertexSet());
		
		GraphPath<Interseccion, Calle> gp = alg.getPath(origen, destino);
		
		GraphColors.toDot(g,"ficheros_generados/" + ftest + "A.gv",
				x->x.getNombre(), x->""+x.getId(),
				v->GraphColors.styleIf(Style.bold, gp.getVertexList().contains(v)),
				e->GraphColors.styleIf(Style.bold, gp.getEdgeList().contains(e)));
		
		System.out.println(ftest + "A.gv generado en " + "ficheros_generados/p2/ejemplo4");
		
		return null;
	}
	
	public static GraphPath<Interseccion,Calle> getRecorrido_E4B(Graph <Interseccion, Calle> g) {
        return null;
	}
	
	public static Graph<Interseccion,Calle> getRecorridoMaxRelevante_E4C(Set<Calle> cs,Graph <Interseccion, Calle> g, String ftest) {
		return null;
	}
		
}
