package tests;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio4;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio4 {
	
	// Datos!!
	private static Integer tMin 				= 0;
	private static Integer tMax 				= 0;
	private static Integer razonDouble 				= 0;
	private static Integer razonBigInteger		= 0;
	private static Integer nDouble 			= 0;
	private static Integer nBigInteger 		= 0;
	
	private static Integer tWarmup 		= 0;
	
	
	private static String file1 = "resources/datos/tmp/RecDouble.txt";
	private static String file2 = "resources/datos/tmp/RecBigInteger.txt";
	private static String file3 = "resources/datos/tmp/ItDouble.txt";
	private static String file4 = "resources/datos/tmp/ItBigInteger.txt";
	
	public static void main(String[] args) {
		
		// Llamada a las funciones de abajo con los datos de arriba, por rellenar.
		
		genDataRecDouble();
		genDataItDouble();
		genDataRecBig();
		genDataItBig();
		
		showRecBig();
		showItBig();
		
		showCombined();
		
		}
	
	// Funciones
	
	private static Integer warmup(Function<Integer, Long> ft, Integer t) {
		Long ns = ft.apply(t);
		Double niw = tWarmup * Math.pow(10., 9) / ns;
		return Math.max(5, niw.intValue());
	}
	
	private static void genDataRecDouble() {
		Function<Integer, Long> ft = GenData.time(t-> Ejercicio4.funcRecDouble(t));
		Function<Integer, Integer> fw = t -> warmup(ft, t);
		GenData.tiemposEjecucionAritmetica(ft, file1, tMin, tMax, razonDouble, nDouble, fw);
		String2.toConsole("Se ha creado el fichero: %s\n\n", file1); // linea(".")??
		System.gc();
		
	}
	private static void genDataItDouble() {
		Function<Integer, Long> ft = GenData.time(t-> Ejercicio4.funcItDouble(t));
		Function<Integer, Integer> fw = t -> warmup(ft, t);
		GenData.tiemposEjecucionAritmetica(ft, file2, tMin, tMax, razonDouble, nDouble, fw);
		String2.toConsole("Se ha creado el fichero: %s\n\n", file2);
		System.gc();
		
	}
	private static void genDataRecBig() {
		Function<Integer, Long> ft = GenData.time(t-> Ejercicio4.funcRecDouble(t));
		Function<Integer, Integer> fw = t -> warmup(ft, t);
		GenData.tiemposEjecucionAritmetica(ft, file3, tMin, tMax, razonBigInteger, nBigInteger, fw);
		String2.toConsole("Se ha creado el fichero: %s\n\n", file3);
		System.gc();
		
	}
	private static void genDataItBig() {
		Function<Integer, Long> ft = GenData.time(t-> Ejercicio4.funcRecDouble(t));
		Function<Integer, Integer> fw = t -> warmup(ft, t);
		GenData.tiemposEjecucionAritmetica(ft, file4, tMin, tMax, razonBigInteger, nBigInteger, fw);
		String2.toConsole("Se ha creado el fichero: %s\n\n", file4);
		System.gc();
		
	}
	
	private static void showRecDouble() {
		List<WeightedObservedPoint> datos = DataFile.points(file1);
		var params = List.of(Pair.of(1, 2.), Pair.of(2, 0.), Pair.of(3, 0.));
		Fit ajuste = PowerLog.of(params);
		double[] v = ajuste.fit(datos);
		String exp = String.format("%.2f n^2", v[0]);
		
		String2.toConsole("Curva obtenida: %s", ajuste.getExpression());
		String2.toConsole("Error en el ajuste (min. cuad.): %s", ajuste.getEvaluation());
		MatPlotLib.show(file1,  ajuste.getFunction(), "Recursiva-Double: " +exp);
	}
	
	private static void showItDouble() {
		List<WeightedObservedPoint> datos = DataFile.points(file2);
		var params = List.of(Pair.of(1, 2.), Pair.of(2, 0.), Pair.of(3, 0.));
		Fit ajuste = PowerLog.of(params);
		double[] v = ajuste.fit(datos);
		String exp = String.format("%.2f n^2", v[0]);
		
		String2.toConsole("Curva obtenida: %s", ajuste.getExpression());
		String2.toConsole("Error en el ajuste (min. cuad.): %s", ajuste.getEvaluation());
		MatPlotLib.show(file2,  ajuste.getFunction(), "Iterativa-Double: " +exp);
	}
	
	private static void showRecBig() {
		List<WeightedObservedPoint> datos = DataFile.points(file3);
		var params = List.of(Pair.of(1, 2.), Pair.of(2, 0.), Pair.of(3, 0.));
		Fit ajuste = PowerLog.of(params);
		double[] v = ajuste.fit(datos);
		String exp = String.format("%.2f n^2", v[0]);
		
		String2.toConsole("Curva obtenida: %s", ajuste.getExpression());
		String2.toConsole("Error en el ajuste (min. cuad.): %s", ajuste.getEvaluation());
		MatPlotLib.show(file3,  ajuste.getFunction(), "Recursiva-BigInteger: " +exp);
	}
	
	private static void showItBig() {
		List<WeightedObservedPoint> datos = DataFile.points(file4);
		var params = List.of(Pair.of(1, 2.), Pair.of(2, 0.), Pair.of(3, 0.));
		Fit ajuste = PowerLog.of(params);
		double[] v = ajuste.fit(datos);
		String exp = String.format("%.2f n^2", v[0]);
		
		String2.toConsole("Curva obtenida: %s", ajuste.getExpression());
		String2.toConsole("Error en el ajuste (min. cuad.): %s", ajuste.getEvaluation());
		MatPlotLib.show(file4,  ajuste.getFunction(), "Iterativa-BigInteger: " +exp);
	}
	
	private static void showCombined() {
		MatPlotLib.showCombined("Tiempos", List.of(file1, file2, file3, file4), 
				List.of("Recursiva-Double", "Iterativa-Double", "Recursiva-BigInteger", "Iterativa-BigInteger"));
	}

	
}