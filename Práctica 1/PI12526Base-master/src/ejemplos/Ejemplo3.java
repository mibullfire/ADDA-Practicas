package ejemplos;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.IntPair;

public class Ejemplo3 {
	public static Integer solucionRecursivaConMemoria(Integer a, Integer b) {
		return gRecConMem(a, b, new HashMap<IntPair, Integer>());
	}
	private static Integer gRecConMem(Integer a, Integer b, Map<IntPair, Integer> m) {
		Integer r = null;
		return r;
	}
}
