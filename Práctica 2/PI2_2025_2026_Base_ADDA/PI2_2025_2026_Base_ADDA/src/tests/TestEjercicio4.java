package tests;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;

import ejercicio4.Calle;
import ejercicio4.Ejercicio4;
import ejercicio4.Interseccion;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class TestEjercicio4 {

	public static void main(String[] args) {
		test("PI2E4_DatosEntrada");
	}
	
	private static void test(String fichero) {
		
		Graph<Interseccion,Calle> gDuracion =  
				GraphsReader.newGraph("ficheros/" + fichero + ".txt",
						Interseccion::ofFormat, 
						Calle::ofFormat,
						Graphs2::simpleWeightedGraph,
						Calle::getDuracion);
		
		Graph<Interseccion,Calle> gEsfuerzo =  
				GraphsReader.newGraph("ficheros/" + fichero + ".txt",
						Interseccion::ofFormat, 
						Calle::ofFormat,
						Graphs2::simpleWeightedGraph,
						Calle::getEsfuerzo);
		
		/// PRUEBAS
		/*
		System.out.println("Archivo de entrada " + fichero + ".txt \n" + "Datos de entrada: " + g);

		GraphColors.toDot(g,"ficheros_generados/" + fichero + ".gv",
				v->v.hasMonumento()?String.valueOf("INT-" + v.getId() + ", m-"+ v.getRelevancia()):String.valueOf("INT-" + v.getId()), //que etiqueta mostrar en vertices y aristas
				v->""+v.getId(),
				//e->String.valueOf("Duracion: " + (int) e.getDuracion() + " - Esfuerzo: " + (int) e.getEsfuerzo()),
				e->""+e.getDuracion(),
				v->GraphColors.color(Color.black), //color o estilo de vertices y aristas
				e->GraphColors.color(Color.black));
		*/
		///
		
		/// === Apartado A === ///
		System.out.println("Apartado A):");
		//Ejercicio4.getSubgraph_EJ4A("m7", "m3", gDuracion, "ficheros_generados/EJ4ApartadoA1.gv"); Da error, porque no existe m3
		Ejercicio4.getSubgraph_EJ4A("m4", "m9", gDuracion, "ficheros_generados/EJ4ApartadoA2.gv");
		
		System.out.println("Apartado B):");
		Ejercicio4.getRecorrido_E4B(gEsfuerzo);
		
		System.out.println("Apartado C):");
		Set<Calle> callesCortadas = new HashSet<Calle>(); 
		Calle calle0 = gDuracion.getEdge(getInterseccionId(gEsfuerzo, 1), getInterseccionId(gEsfuerzo, 6)); 
		Calle calle1 = gDuracion.getEdge(getInterseccionId(gEsfuerzo,4), getInterseccionId(gEsfuerzo, 7));
		Calle calle2 = gDuracion.getEdge(getInterseccionId(gEsfuerzo, 5), getInterseccionId(gEsfuerzo, 8));
		Calle calle3 = gDuracion.getEdge(getInterseccionId(gEsfuerzo, 4), getInterseccionId(gEsfuerzo, 6));
		Calle calle4 = gDuracion.getEdge(getInterseccionId(gEsfuerzo, 7), getInterseccionId(gEsfuerzo, 8));
		callesCortadas.add(calle0);
		callesCortadas.add(calle1);
		callesCortadas.add(calle2);
		callesCortadas.add(calle3);
		Ejercicio4.getRecorridoMaxRelevante_E4C(callesCortadas, gDuracion, "ficheros_generados/EJ4ApartadoC1.gv"); 
		
		callesCortadas.add(calle4);
		Ejercicio4.getRecorridoMaxRelevante_E4C(callesCortadas, gDuracion, "ficheros_generados/EJ4ApartadoC2.gv"); 
	}
	
	private static Interseccion getInterseccionId(Graph<Interseccion, Calle> g, Integer i) { 
		return g.vertexSet().stream().filter(inter-> inter.getId().equals(i))
			.toList().get(0); 
	}
	

	
}
