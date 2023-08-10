package ejemplos.tests;

import java.util.List;
import java.util.function.Predicate;

import _datos.DatosAlumnos;
import _datos.DatosMulticonjunto;
import _soluciones.SolucionAlumnos;
import _soluciones.SolucionMulticonjunto;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejemplos.ejemplo1.MulticonjuntoVertex;
import ejemplos.ejemplo3.AlumnosVertex;

//Clase para todos los tests del ejemplo 3 mediante Greedy, A*, PDR y BT 
public class TestsEjemplo3 {


	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
			TestsPI5.iniTest("Ejemplo3DatosEntrada", num_test, DatosAlumnos::iniDatos);		
			//TestsPI5.iniTest(
			//		AlumnosVertex.initial(),  
			//		AlumnosVertex.goal(),
			//		GraphsPI5::alumnosBuilder,
			//		AlumnosVertex::greedyEdge,
			//		SolucionAlumnos::of);

		});
	}

}