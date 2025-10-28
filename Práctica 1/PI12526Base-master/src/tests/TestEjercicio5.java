package tests;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

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
	
	private static String fileRecDouble			= "resources/datos/";
	private static String fileRecBigInteger		= "";
	private static String fileIterDouble		= "";
	private static String fileIterBigInteger	= "";
	
	public static void main(String[] args) {
	
	}
}