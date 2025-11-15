package ejercicio1;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.TEmpty;
import us.lsi.tiposrecursivos.TLeaf;
import us.lsi.tiposrecursivos.TNary;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio1 {	
	
	// Pair<List<Integer>, Integer> Donde la lista es el camino que llevo y el entero es su multiplicación
	
	public static List<Integer> caminoMaximo (BinaryTree<Integer> tree) {
		Pair<List<Integer>, Integer> pairBase = Pair.of(new ArrayList<Integer>(), 1);
		return caminoMaximo(tree, pairBase, pairBase).first();
	}
	
	private static Pair<List<Integer>, Integer> caminoMaximo(BinaryTree<Integer> tree, Pair<List<Integer>, Integer> par, Pair<List<Integer>, Integer> max) {
		
		/* "aux" es un par auxiliar, que voy a usar para el caso de las hojas. El objetivo de "aux" es almacenar un par auxiliar, que podría ser candidato 
		 * para convertirse en el nuevo "max".
		 */
		
		Pair<List<Integer>, Integer> aux = null;
		
		switch (tree) {
			case BEmpty<Integer> t -> {;}
			case BLeaf<Integer> t -> {
				
				List<Integer> lista = par.first();
				lista.add(t.label());
				aux = Pair.of(lista, par.second()*t.label());
				if (aux.second() > max.second()) max = aux;
				
			}
			case BTree<Integer> t -> {
				
				/* Para afrontar este problema, he creado dos listas auxiliares, una para la rama izquierda y otra para la derecha, cada una llamará recursivamente
				 * a la función, hasta llegar a una hoja, donde resolveremos el problema.
				 */
				
				List<Integer> lista2i = new ArrayList<Integer>(par.first());
				lista2i.add(t.label());
				max = caminoMaximo(t.left(), Pair.of(lista2i, par.second()*t.label()), max);
				List<Integer> lista2d = new ArrayList<Integer>(par.first());
				lista2d.add(t.label());
				max = caminoMaximo(t.right(), Pair.of(lista2d, par.second()*t.label()), max);
				
			}
		}
		
		return max;
	}

	public static List<Integer> caminoMaximo (Tree<Integer> tree) {
		Pair<List<Integer>, Integer> pairBase = Pair.of(new ArrayList<Integer>(), 1);
		return caminoMaximo(tree, pairBase, pairBase, 0).first();
	}
	
	private static Pair<List<Integer>, Integer> caminoMaximo(Tree<Integer> tree, Pair<List<Integer>, Integer> par, Pair<List<Integer>, Integer> max, Integer i) {
		
		/* "aux" es un par auxiliar, que voy a usar para el caso de las hojas. El objetivo de "aux" es almacenar un par auxiliar, que podría ser candidato 
		 * para convertirse en el nuevo "max".
		 */
		
		Pair<List<Integer>, Integer> aux = null;
		
		switch (tree) {
			case TEmpty<Integer> t -> {;}
			case TLeaf<Integer> t -> {
				
				List<Integer> lista = par.first();
				lista.add(t.label());
				aux = Pair.of(lista, par.second()*t.label());
				if (aux.second() > max.second()) max = aux;
				
			}
			case TNary<Integer> t -> {
				
				for (Tree<Integer> tt : t.children()) {
					List<Integer> auxChd = new ArrayList<Integer>(par.first());
					auxChd.add(t.label());
					max = caminoMaximo(tt, Pair.of(auxChd, par.second()*t.label()), max, i+1);
				}
			}
		}
		
		return max;
		
	}
	
}
