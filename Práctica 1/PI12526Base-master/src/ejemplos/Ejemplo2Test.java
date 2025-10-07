package ejemplos;

public class Ejemplo2Test {

    public static void main(String[] args) {
        Ejemplo2 e = new Ejemplo2();

        Integer a = 10;
        Integer b = 14;

        System.out.println("== Pruebas con a = " + a + " y b = " + b + " ==");

        String resultadoIterativa = e.iterativa(a, b);
        System.out.println("Método iterativa: " + resultadoIterativa);

        String resultadoRecF = e.recursivaF(a, b);
        System.out.println("Método recursiva final: " + resultadoRecF);

        String resultadoRecNF = e.recursivaNF(a, b);
        System.out.println("Método recursiva no final: " + resultadoRecNF);
    }
}
