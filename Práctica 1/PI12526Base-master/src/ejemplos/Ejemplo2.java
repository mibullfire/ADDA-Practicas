package ejemplos;

public class Ejemplo2 {
	
	public String iterativa(Integer a, Integer b) {
		String ac = "";
		while(!(a < 5 || b < 5)) {
			ac = String.format("%s%d", ac, a+b);
			a /= 2;
			b -= 2;
		}
		return String.format("%s(%d)", ac, a*b);
	}
	
	public String recursivaF(Integer a, Integer b) {
		return recFinal("", a, b);
	}
	
	private String recFinal(String ac, Integer a, Integer b) {
		String r = null;
		if (a < 5 || b < 5) r = ac + String.format("(%d)", a*b);
		else r = recFinal(String.format("%s%d", ac, a+b), a/2, b-2);
		return r;
	}
	
	public String recursivaNF(Integer a, Integer b) {
		String r = null;
		if (a < 5 || b < 5) r = String.format("(%d)", a*b);
		else r = String.format("%d", a+b) + recursivaNF(a/2, b-2);
		return r;
	}
}
