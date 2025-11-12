package ejercicio1;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio1 {	
	
	// Pair<List<Integer>, Integer> Donde la lista es el camino que llevo y el entero es su multiplicación
	
	public static List<Integer> caminoMaximo (BinaryTree<Integer> tree) {
		Pair<List<Integer>, Integer> par = Pair.of(new ArrayList<Integer>(), 1);
		return caminoMaximo(tree, par, par, 0).first();
	}
	
	private static Pair<List<Integer>, Integer> caminoMaximo(BinaryTree<Integer> tree, Pair<List<Integer>, Integer> par, Pair<List<Integer>, Integer> maximo
, Integer i) {
		Pair<List<Integer>, Integer> aux = null;
		switch (tree) {
		case BEmpty():
			break;
		case BLeaf(var lb): 
			List<Integer> lista = new ArrayList<Integer>(par.first());
			lista.add(lb);
			aux = Pair.of(lista, par.second()*lb);
			if (aux.second() > maximo.second()) {
				maximo = aux;
			}
			break;
		case BTree(var lb, var lt, var rt):
			List<Integer> lista2i = new ArrayList<Integer>(par.first());
			lista2i.add(lb);
			maximo = caminoMaximo(lt, Pair.of(lista2i, par.second()*lb), maximo, i+1);
			List<Integer> lista2d = new ArrayList<Integer>(par.first());
			lista2d.add(lb);
			maximo = caminoMaximo(rt, Pair.of(lista2d, par.second()*lb), maximo, i+1);
			break;
		}
		
		return maximo;
	}

	public static List<Integer> caminoMaximo (Tree<Integer> tree) {
		return null;
	}
	
}
