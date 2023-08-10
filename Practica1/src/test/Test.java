package test;

import java.util.List;
import java.util.stream.Collectors;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import us.lsi.common.Files2;
import us.lsi.geometria.Punto2D;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("EJERCICIO 1");
		Ejercicio1Test();
		System.out.println(" ");
		System.out.println("EJERCICIO 2");
		Ejercicio2Test();
		System.out.println(" ");
		System.out.println("EJERCICIO 3");
		Ejercicio3Test();
		System.out.println(" ");
		System.out.println("EJERCICIO 4");
		Ejercicio4Test();
		
		

	}
	
	private static void Ejercicio1Test() {
		String fichero = "ficheros/PI1Ej1DatosEntrada.txt";
		List<TuplaEjercicio1> lineas = Files2.streamFromFile(fichero)
				.map(linea -> TuplaEjercicio1.parse(linea))
				.collect(Collectors.toList());
	
	System.out.println(" ");
	System.out.println("SOLUCION ITERATIVA");
	System.out.println(" ");
	for (TuplaEjercicio1 t1: lineas) {
		System.out.println(Ejercicio1.ejercicio1Iterativo(t1.a(),t1.s1(),t1.b(),t1.s2(),t1.c()));
	}
	
	System.out.println(" ");
	System.out.println("SOLUCION RECURSIVA FINAL");
	System.out.println(" ");
	for (TuplaEjercicio1 t1: lineas) {
		System.out.println(Ejercicio1.ejercicio1RecursivaFinal(t1.a(),t1.s1(),t1.b(),t1.s2(),t1.c()));
	}
	
	}
	
	private static void Ejercicio2Test() {
		String fichero = "ficheros/PI1Ej2DatosEntrada.txt";
		List<TuplaEjercicio2> lineas = Files2.streamFromFile(fichero)
				.map(linea -> TuplaEjercicio2.parse(linea))
				.collect(Collectors.toList());
		
	System.out.println(" ");	
	System.out.println("SOLUCION ITERATIVA");
	System.out.println(" ");
	for (TuplaEjercicio2 t2: lineas) {
		System.out.println(Ejercicio2.ejercicio2Iterativo(t2.a(),t2.b(),t2.s()));
	}
	
	System.out.println(" ");
	System.out.println("SOLUCION RECURSIVA NO FINAL");
	System.out.println(" ");
	for (TuplaEjercicio2 t2: lineas) {
		System.out.println(Ejercicio2.ejercicio2RecursivoNoFinal(t2.a(),t2.b(),t2.s()));
	}
	System.out.println(" ");
	System.out.println("SOLUCION RECURSIVA FINAL");
	System.out.println(" ");
	for (TuplaEjercicio2 t2: lineas) {
		System.out.println(Ejercicio2.ejercicio2RecursivoFinal(t2.a(),t2.b(),t2.s()));
	}
	System.out.println(" ");
	System.out.println("SOLUCION FUNCIONAL");
	System.out.println(" ");
	for (TuplaEjercicio2 t2: lineas) {
		System.out.println(Ejercicio2.ejercicio2Funcional(t2.a(),t2.b(),t2.s()));
		
	}
	}
	
	private static void Ejercicio3Test() {
		
		String fichero1 = "ficheros/PI1Ej3DatosEntrada1A.txt";
		String fichero2 = "ficheros/PI1Ej3DatosEntrada1B.txt";
		String fichero3 = "ficheros/PI1Ej3DatosEntrada2A.txt";
		String fichero4 = "ficheros/PI1Ej3DatosEntrada2B.txt";
		String fichero5 = "ficheros/PI1Ej3DatosEntrada3A.txt";
		String fichero6 = "ficheros/PI1Ej3DatosEntrada3B.txt";
		
		System.out.println(" ");
        System.out.println("SOLUCION ITERATIVA");
        System.out.println(" ");
        System.out.println(Ejercicio3.ejercicio3Iterativo(fichero1, fichero2));
        System.out.println(Ejercicio3.ejercicio3Iterativo(fichero3, fichero4));
        System.out.println(Ejercicio3.ejercicio3Iterativo(fichero5, fichero6));
        System.out.println(" ");
        System.out.println("SOLUCION RECURSIVA FINAL");
        System.out.println(" ");
        System.out.println(Ejercicio3.ejercicio3RecursivoFinal(fichero1, fichero2));
        System.out.println(Ejercicio3.ejercicio3RecursivoFinal(fichero3, fichero4));
        System.out.println(Ejercicio3.ejercicio3RecursivoFinal(fichero5, fichero6));
        System.out.println(" ");
        System.out.println("SOLUCION FUNCIONAL");
        System.out.println(" ");
        System.out.println(Ejercicio3.ejercicio3Funcional(fichero1, fichero2));
        System.out.println(Ejercicio3.ejercicio3Funcional(fichero3, fichero4));
        System.out.println(Ejercicio3.ejercicio3Funcional(fichero5, fichero6));
	}
	
	private static void Ejercicio4Test() {
		String fichero = "ficheros/PI1Ej4DatosEntrada.txt";
		List<TuplaEjercicio4> lineas = Files2.streamFromFile(fichero)
				.map(linea -> TuplaEjercicio4.parse(linea))
				.collect(Collectors.toList());
		
		System.out.println(" ");
		System.out.println("SOLUCION RECURSIVA SIN MEMORIA");
		System.out.println(" ");
		for (TuplaEjercicio4 t4: lineas) {
			System.out.println(Ejercicio4.ejercicio4RecursivoSinMemoria(t4.a(),t4.b(),t4.c()));
			
		}
		System.out.println(" ");
		System.out.println("SOLUCION RECURSIVA CON MEMORIA");
		System.out.println(" ");
		for (TuplaEjercicio4 t4: lineas) {
			System.out.println(Ejercicio4.ejercicio4RecursivoConMemoria(t4.a(),t4.b(),t4.c()));
		
		}
		System.out.println(" ");
		System.out.println("SOLUCION ITERATIVA");
		System.out.println(" ");
		for (TuplaEjercicio4 t4: lineas) {
			System.out.println(Ejercicio4.ejercicio4Iterativo(t4.a(),t4.b(),t4.c()));
		}
	}
	
	
	
	public record TuplaEjercicio1 (Integer a, String s1, Integer b, String s2, Integer c) {
		public static TuplaEjercicio1 parse(String s3) {
			String[] s4 = s3.split(",");
			
			Integer a = Integer.parseInt(s4[0].trim());
			String s1 = s4[1].trim();
			Integer b = Integer.parseInt(s4[2].trim());
			String s2 = s4[3].trim();
			Integer c = Integer.parseInt(s4[4].trim());
			
			return new TuplaEjercicio1 (a,s1,b,s2,c);
		}
	}
	
	
	public record TuplaEjercicio2 (Integer a, Integer b, String s) {
		public static TuplaEjercicio2 parse(String s2) {
			String[]s1 = s2.split(",");
			Integer a = Integer.parseInt(s1[0].trim());
			Integer b = Integer.parseInt(s1[1].trim());
			String s = s1[2].trim();
			
			return new TuplaEjercicio2 (a,b,s);
		}
	}
	
	public record TuplaEjercicio3 (Double x, Double y) {
		public static Punto2D parse(String s3) {
			String[]s1 = s3.split(",");
			Double x = Double.parseDouble(s1[0].trim());
			Double y = Double.parseDouble(s1[1].trim());
			
			return Punto2D.of (x,y);
		}
	}
	
	public record TuplaEjercicio4 (Integer a, Integer b, Integer c) {
		public static TuplaEjercicio4 parse(String s4) {
			String[]s1 = s4.split(",");
			Integer a = Integer.parseInt(s1[0].trim());
			Integer b = Integer.parseInt(s1[1].trim());
			Integer c = Integer.parseInt(s1[2].trim());
			
			return new TuplaEjercicio4 (a,b,c);
		}
	}
	
	
	
	
	
	
	
	
	

}
