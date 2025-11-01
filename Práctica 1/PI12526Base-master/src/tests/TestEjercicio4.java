package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio4;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio4 {
	
	private static Integer nMin 				= 100;
	private static Integer nMax 				= 20000;
	private static Integer razonDouble			= 250;
	private static Integer razonBigInteger		= 800;
	private static Integer nIter = 300;
	private static Integer nIterWarmup = 500;
	
	private static String file1 = "resources/datos/tmp/RecDouble.txt";
	private static String file2 = "resources/datos/tmp/RecBigInteger.txt";
	private static String file3 = "resources/datos/tmp/ItDouble.txt";
	private static String file4 = "resources/datos/tmp/ItBigInteger.txt";
	
	
	// Funciones	
	public static void genData (Consumer<Integer> algorithm, String file, Integer razon) {
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
				List.of(file1,file2,file3,file4), 
				List.of("RecDouble","RecBigInteger","ItDouble","ItBigInteger"));
	}
	
	public static void main(String[] args) {
		
		genData(t -> Ejercicio4.funcRecDouble(t),file1,razonDouble);
		genData(t -> Ejercicio4.funcRecBig(t),file2,razonBigInteger);
		genData(t -> Ejercicio4.funcItDouble(t),file3,razonDouble);
		genData(t -> Ejercicio4.funcItBig(t),file4,razonBigInteger);
		
		show(PowerLog.of(List.of(Pair.of(1, 1.),Pair.of(2,0.),Pair.of(3, 0.))),file1,"RecDouble");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),file2,"RecBigInteger");
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(3, 0.))),file3,"ItDouble");
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(3, 0.))),file4,"ItBigInteger");
		
		showCombined();
	}
	
}
