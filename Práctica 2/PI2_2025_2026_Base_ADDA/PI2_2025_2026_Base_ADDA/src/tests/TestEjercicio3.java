package tests;

import java.util.List;
import java.util.Map;

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
		System.out.println("Revisar las cosas en:\n				-> https://graph.flyte.org/ <-");
		/// === Apartado A === ///
		Graph<Investigador,Colaboracion> gA = Ejercicio3.getSubgraph_EJ3A(g);
		
		GraphColors.toDot(g,"ficheros_generados/PI2E3_ApartadoA.gv",
				v->String.valueOf("INV-"+v.getId()+" "+v.getFNacimiento()), //que etiqueta mostrar en vertices y aristas
				e->String.valueOf(e.getNColaboraciones().intValue()),
				v->GraphColors.colorIf(Color.blue, gA.containsVertex(v)),
				e->GraphColors.colorIf(Color.blue, gA.containsEdge(e)));
		
		System.out.println("\n	- ApartadoA OK! Revisar archivo ficheros_generados/PI2E3_ApartadoA.gv");
		
		/// === Apartado B === ///
		List<Investigador> lB = Ejercicio3.getMayoresColaboradores_E3B(g);
		GraphColors.toDot(g,"ficheros_generados/PI2E3_ApartadoB.gv",
				v->String.valueOf("INV-"+v.getId()), //que etiqueta mostrar en vertices y aristas
				e->String.valueOf(e.getNColaboraciones().intValue()),
				v->GraphColors.colorIf(Color.blue, Color.green, lB.contains(v)),
				e->GraphColors.color(Color.green));
		
		System.out.println("\n	- ApartadoB OK! Revisar archivo ficheros_generados/PI2E3_ApartadoB.gv");
		
		/// === Apartado C === ///
		Map<Investigador, List<Investigador>> mC = Ejercicio3.getMapListaColabroradores_E3C(g);
		
		System.out.println(mC); // TODO: falta pintar el grafo con las aristas.
		
	}
	
}
