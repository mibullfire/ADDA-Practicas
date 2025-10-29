package tests;

import java.util.List;
import java.util.function.Consumer;
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
	private static Integer nMin = 10;
	private static Integer nMax = 1000;
	private static Integer razon = 100;
	private static Integer nIter = 5;
	private static Integer nIterWarmup = 100;

	
	// Datos!!
	/*
	private static Integer nMin 				= 100;
	private static Integer nMax 				= 20000;
	private static Integer razon 				= 250;
	private static Integer razonBigInteger		= 800;
	private static Integer nDouble 			= 300;
	private static Integer nIter = 10;
	private static Integer nIterWarmup = 500;
	
	private static Integer tWarmup 		= 1;
	private static Integer tWarmupDouble = 4;
	
	
	private static String file1 = "resources/datos/tmp/RecDouble.txt";
	private static String file2 = "resources/datos/tmp/RecBigInteger.txt";
	private static String file3 = "resources/datos/tmp/ItDouble.txt";
	private static String file4 = "resources/datos/tmp/ItBigInteger.txt";
	*/
	
	// Funciones
	
	private static Integer a = 3;
	
	public static void genData (Consumer<Integer> algorithm, String file) {
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
				List.of("resources/funcItBig.txt","resources/funcItDouble.txt","resources/funcRecBig.txt", "resources/funcRecDouble.txt"), 
				List.of("funcItBig","funcItDouble","funcRecBig", "funcRecDouble"));
	}
	

	public static void main(String[] args) {
		genData(t -> Ejercicio4.funcItBig(t),"resources/funcItBig.txt");
		genData(t -> Ejercicio4.funcItDouble(t),"resources/funcItDouble.txt");
		genData(t -> Ejercicio4.funcRecBig(t),"resources/funcRecBig.txt");
		genData(t -> Ejercicio4.funcRecDouble(t),"resources/funcRecDouble.txt");
		
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "resources/funcItBig.txt","funcItBig");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),"resources/funcItDouble.txt","funcItDouble");
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(2, 1.),Pair.of(3, 0.))),"resources/funcRecBig.txt","funcRecBig");
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(2, 1.),Pair.of(3, 0.))),"resources/funcRecDouble.txt","funcRecDouble");
		
		showCombined();
	}
	
}
