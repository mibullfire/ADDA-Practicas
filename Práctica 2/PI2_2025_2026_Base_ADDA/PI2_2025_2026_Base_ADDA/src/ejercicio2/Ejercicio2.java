package ejercicio2;

import us.lsi.tiposrecursivos.BinaryTree;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.TEmpty;
import us.lsi.tiposrecursivos.TLeaf;
import us.lsi.tiposrecursivos.TNary;
import us.lsi.tiposrecursivos.Tree;



public class Ejercicio2 {
	
	public static Boolean solucion_recursiva(BinaryTree<String> tree) {
		return solucion_recursiva(tree, 0, null).ok;
	}
	
	private static Res solucion_recursiva(BinaryTree<String> tree, Integer cuenta, Integer objetivo) {
				
		switch(tree) {
			
			case BEmpty<String> t -> {
				return new Res(true, 0);
			}
			case BLeaf<String> t -> {
				int total = cuenta + numVc(t.label());
				if (objetivo==null)
					objetivo = total;
				 return new Res(objetivo==total, numVc(t.label()));
							
			}
			case BTree<String> t -> {
				
				int vocalesNodo = numVc(t.label());
	
		        // subárbol izquierdo
		        Res izq = solucion_recursiva(t.left(), cuenta + vocalesNodo, objetivo);
	
		        // subárbol derecho
		        Res der = solucion_recursiva(t.right(), cuenta + vocalesNodo, objetivo);
	
		        // condición a comprobar en este nodo
		        boolean ok = izq.ok && der.ok && (izq.vocales == der.vocales);
	
		        return new Res(ok, izq.vocales + der.vocales + vocalesNodo);
				
				
			}
		}
		
	}

	public static Boolean solucion_recursiva(Tree<String> tree) {
		return solucion_recursiva(tree, 0, null).ok;
	}
	
	private static Res solucion_recursiva(Tree<String> tree, Integer cuenta, Integer objetivo) {
		
		switch(tree) {
		case TEmpty<String> t -> {
			return new Res(true, 0);
		}
		case TLeaf<String> t -> {
			int total = cuenta + numVc(t.label());
			if (objetivo==null) objetivo = total;
			return new Res(objetivo==total, numVc(t.label()));
		}
		case TNary<String> t -> {
			
			//// NOTA: todo este trozo es una chatgepeteada
			
			int vocalesNodo = numVc(t.label());
			
		    // Procesar todos los hijos
		    List<Res> resultados = new ArrayList<>();
		    for (Tree<String> hijo : t.children()) {
		        Res resHijo = solucion_recursiva(hijo, cuenta + vocalesNodo, objetivo);
		        resultados.add(resHijo);
		    }
		    
		    // Verificar que todos los hijos tengan el mismo número de vocales
		    boolean todosOk = true;
		    int vocalesPrimerHijo = resultados.get(0).vocales;
		    int totalVocalesHijos = 0;
		    
		    for (Res res : resultados) {
		        todosOk = todosOk && res.ok && (res.vocales == vocalesPrimerHijo);
		        totalVocalesHijos += res.vocales;
		    }
		    
		    return new Res(todosOk, totalVocalesHijos + vocalesNodo);
			
		}
		
		}
	}
	
	private record Res(Boolean ok, Integer vocales) {}
	
	private static Integer numVc(String palabra) {
		int count = 0;
		palabra = palabra.toLowerCase();  // Para evitar problemas con mayúsculas

        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        
        return count;
	}
	
}
