package tests;

import org.jgrapht.Graph;

import ejercicio3.Colaboracion;
import ejercicio3.Investigador;
import ejercicio3.Ejercicio3;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class TestEjercicio3 {

	public static void main(String[] args) {
		testEjercicio3("PI2E3_DatosEntrada");
		
	}
	
	public static void testEjercicio3(String file) {
		// Creación del grafo a partir de los datos del fichero .txt:
		Graph<Investigador, Colaboracion> g = GraphsReader
				.newGraph("ficheros/"+file+".txt",
						Investigador::ofFormat,
						Colaboracion::ofFormat,
						Graphs2::simpleGraph);
		
		// Muestra del grafo original
		GraphColors.toDot(g,"ficheros_generados/" + file + ".gv",
				v->String.valueOf(v.getId()), //que etiqueta mostrar en vertices y aristas
				e->String.valueOf(e.getNColaboraciones()),
				v->GraphColors.color(Color.black), //color o estilo de vertices y aristas
				e->GraphColors.color(Color.black));
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
		/// === Apartado A === ///
		Graph<Investigador,Colaboracion> gA = Ejercicio3.getSubgraph_EJ3A(g);
		
		GraphColors.toDot(g,"ficheros_generados/PI2E3_ApartadoA.gv",
				v->String.valueOf(v.getId()), //que etiqueta mostrar en vertices y aristas
				e->String.valueOf(e.getNColaboraciones()),
				v->GraphColors.colorIf(Color.blue, gA.containsVertex(v)),
				e->GraphColors.colorIf(Color.black, gA.containsEdge(e)));
		
		System.out.println("ApartadoA OK");
	
	}
	
}
