package ejemplos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class Ejemplo1Test {
    public static void main(String[] args) {
        
        // Creamos algunos puntos de prueba
        List<Punto2D> puntos = Arrays.asList(
            Punto2D.of(2.0, 3.0),   // Cuadrante I
            Punto2D.of(-4.0, 5.0),  // Cuadrante II
            Punto2D.of(-1.0, -2.0), // Cuadrante III
            Punto2D.of(3.0, -4.0),  // Cuadrante IV
            Punto2D.of(5.0, 6.0),   // Cuadrante I
            Punto2D.of(-2.0, 7.0)   // Cuadrante II
        );
        
        // Probamos solucionFuncional
        Map<Cuadrante, Double> r1 = Ejemplo1.solucionFuncional(puntos);
        System.out.println("Resultado Funcional:");
        System.out.println(r1);
        
        // Probamos solucionIterativa
        Map<Cuadrante, Double> r2 = Ejemplo1.solucionIterativa(puntos);
        System.out.println("Resultado Iterativo:");
        System.out.println(r2);
        
        // Probamos solucionRecursivaFinal
        Map<Cuadrante, Double> r3 = Ejemplo1.solucionRecursivaFinal(puntos);
        System.out.println("Resultado Recursivo Final:");
        System.out.println(r3);
    }
}