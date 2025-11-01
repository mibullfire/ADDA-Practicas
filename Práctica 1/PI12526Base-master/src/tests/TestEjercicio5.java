package tests;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio4;
import ejercicios.Ejercicio5;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio5 {
		
	// Datos
	private static Integer tMin 				= 50;
	private static Integer tMax 				= 5125;
	private static Integer razon 				= 175;
	private static Integer nIterDouble 			= 2000;
	private static Integer nIterBigInteger 		= 400;
	
	private static Integer tWarmupDouble 		= 10000;
	private static Integer tWarmupBigInteger 	= 200;
	
	private static String fileRecDouble			= "resources/fileRecDouble.txt";
	private static String fileRecBigInteger		= "resources/fileRecBigInteger.txt";
	private static String fileIterDouble		= "resources/fileIterDouble.txt";
	private static String fileIterBigInteger	= "resources/fileIterBigInteger.txt";
	
	public static void main(String[] args) {
		
		Integer n = 15;
		Double res = Ejercicio5.ejercicio5ItDouble(n);
		Double res2 = Ejercicio5.ejercicio5RecDouble(n);
		BigInteger res3 = Ejercicio5.ejercicio5RecBigInteger(n);
		BigInteger res4 = Ejercicio5.ejercicio5ItBigInteger(n);
		System.out.println(res);
		System.out.println(res2);
		System.out.println(res3);
		System.out.println(res4);
		
		genData(t -> Ejercicio5.ejercicio5RecDouble(t),fileRecDouble, tMin, tMax, nIterDouble, tWarmupDouble);
		genData(t -> Ejercicio5.ejercicio5RecBigInteger(t),fileRecBigInteger, tMin, tMax, nIterBigInteger, tWarmupBigInteger);
		genData(t -> Ejercicio5.ejercicio5ItDouble(t),fileIterDouble, tMin, tMax, nIterDouble, tWarmupDouble);
		genData(t -> Ejercicio5.ejercicio5ItBigInteger(t),fileIterBigInteger, tMin, tMax, nIterBigInteger, tWarmupBigInteger);
		
		show(PowerLog.of(List.of(Pair.of(1, 1.),Pair.of(2,0.),Pair.of(3, 0.))), fileRecDouble,"ejercicio5RecDouble"); // Lineal
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),fileRecBigInteger,"ejercicio5RecBigInteger"); // Exponencial
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(3, 0.))),fileIterDouble,"ejercicio5ItDouble"); // Log negativo
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(3, 0.))),fileIterBigInteger,"ejercicio5ItBigInteger"); // Exponencial
		
		showCombined();

	}
	
	// Funciones del Test Ejercicio 4 (Ejemplo 4)
	
	public static void genData (Consumer<Integer> algorithm, String file, Integer nMin, Integer nMax, Integer nIter, Integer nIterWarmup) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,razon,nIter,nIterWarmup);

	}
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}
	
	
	         
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of(fileRecDouble,fileRecBigInteger,fileIterDouble, fileIterBigInteger), 
				List.of("fileRecDouble","fileRecBigInteger","fileIterDouble", "fileIterBigInteger"));
	}
}