package tests;

import org.jgrapht.graph.SimpleWeightedGraph;

import ejercicio4.Calle;
import ejercicio4.Ejercicio4;
import ejercicio4.Interseccion;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class TestEjercicio4 {

	public static void main(String[] args) {
		test("PI2E4_DatosEntrada");
		

	}
	
	private static void test(String fichero) {
		SimpleWeightedGraph<Interseccion,Calle> g =  
				GraphsReader.newGraph("ficheros/" + fichero + ".txt",
						Interseccion::ofFormat, 
						Calle::ofFormat,
						Graphs2::simpleWeightedGraph);

		System.out.println("Archivo de entrada " + fichero + ".txt \n" + "Datos de entrada: " + g);

		GraphColors.toDot(g,"ficheros_generados/" + fichero + ".gv",
				//v->v.hasMonumento()?String.valueOf("INT-" + v.getId() + ", m-"+ v.getRelevancia()):String.valueOf("INT-" + v.getId()), //que etiqueta mostrar en vertices y aristas
				v->""+v.getId(),
				//e->String.valueOf("Duracion: " + (int) e.getDuracion() + " - Esfuerzo: " + (int) e.getEsfuerzo()),
				e->""+e.getDuracion(),
				v->GraphColors.color(Color.black), //color o estilo de vertices y aristas
				e->GraphColors.color(Color.black));
		
		System.out.println("Apartado A):");
		Ejercicio4.getSubgraph_EJ4A("2", "5", g, "");
		
		System.out.println("Apartado B):");
		//Ejemplo4.apartadoB(g, fichero);
	}
	

	
}
