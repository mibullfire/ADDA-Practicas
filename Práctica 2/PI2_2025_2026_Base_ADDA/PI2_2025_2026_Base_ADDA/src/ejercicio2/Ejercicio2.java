package ejercicio2;

import us.lsi.tiposrecursivos.BinaryTree;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.TEmpty;
import us.lsi.tiposrecursivos.TLeaf;
import us.lsi.tiposrecursivos.TNary;
import us.lsi.tiposrecursivos.Tree;



public class Ejercicio2 {
	
	public static Boolean solucion_recursiva(BinaryTree<String> tree) {
		return sol(tree).first();
	}
	
	private static Pair<Boolean, Integer> sol(BinaryTree<String> tree) {
		switch(tree) {
		/*
		 * Por defecto, cuento estemos en un árbol vacío o en una hoja, devolveremos "true" y su número de vocales.
		 * Y cuando estemos en un nodo, hacemos la comprobación de si en el par de sus hijos, ambos son verdaderos, y
		 * que ambos tengan el mismo número de vocales.
		 * Si cualquiera de las condiciones anteriormente mencionadas es "false", lo arrastrará indistintivamente hasta
		 * la solución final, devolviendo el "false" que esperamos por solución.
		 * Una propuesta para optimizar el código, sería que en el momento que encuentre un "false" en cualquier punto,
		 * el programa se detenga y devuelva "false".
		 * 
		 * Vemos seguidamente todo lo que he comentado en los casos posibles:
		 */
			case BEmpty<String> t -> {
				return Pair.of(true, 0);
			}
			case BLeaf<String> t -> {
				return Pair.of(true, numVc(t.label()));
			}
			case BTree<String> t -> {
				int vocalesNodo = numVc(t.label());
		        // subárbol izquierdo
		        Pair<Boolean, Integer> izq = sol(t.left());
		        // subárbol derecho
		        Pair<Boolean, Integer> der = sol(t.right());
		        // condición a comprobar en este nodo
		        boolean ok = izq.first() && der.first() && (izq.second() == der.second());
		        return Pair.of(ok, izq.second()+der.second()+vocalesNodo);
			}
		}
	}

	public static Boolean solucion_recursiva(Tree<String> tree) {
		return sol(tree).first();
	}
	
	private static Pair<Boolean, Integer> sol(Tree<String> tree) {
		switch(tree) {
		/*
		 * La premisa es la misma que para los árboles binarios, solo que ahora la comprobación es algo más
		 * compleja, pues tenemos que comprobar para todos los hijos de cada nodo. Siendo el número de hijos
		 * un valor n entero, que va cambiando depende del nodo.
		 * La lógica de las comprobaciones es la misma.
		 * 
		 * Veáse el siguiente código:
		 */
		case TEmpty<String> t -> {
			return Pair.of(true, 0);
		}
		case TLeaf<String> t -> {
			return Pair.of(true, numVc(t.label()));
		}
		case TNary<String> t -> {
			int vocalesNodo = numVc(t.label());
		    // Procesar todos los hijos
		    List<Pair<Boolean, Integer>> resultados = new ArrayList<>();
		    for (Tree<String> hijo : t.children()) {
		    	Pair<Boolean, Integer> resHijo = sol(hijo);
		        resultados.add(resHijo);
		    }
		    // Verificar que todos los hijos tengan el mismo número de vocales
		    boolean todosOk = true;
		    int vocalesPrimerHijo = resultados.get(0).second();
		    int totalVocalesHijos = 0;
		    for (Pair<Boolean, Integer> res : resultados) {
		        todosOk = todosOk && res.first() && (res.second() == vocalesPrimerHijo);
		        totalVocalesHijos += res.second();
		    }
		    return Pair.of(todosOk, totalVocalesHijos + vocalesNodo);	
		}
		}
	}
	// ===FUNCION CUENTA VOCALES STRING===	
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