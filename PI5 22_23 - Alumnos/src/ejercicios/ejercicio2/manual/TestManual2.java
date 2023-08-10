package ejercicios.ejercicio2.manual;

import java.util.HashSet;
import java.util.List;
import ejercicios.ejercicio2.DatosCursos;
import _utils.TestsPI5;
import us.lsi.common.String2;


public class TestManual2 {
	
	
public static void main(String[] args) {
	List.of(1,2,3).forEach(num_test -> {
		DatosCursos.iniDatos("ficheros/Ejercicio2DatosEntrada"+num_test+".txt"); String2.toConsole("\nSolucion obtenida: %s\n",
				CursosPDR.pd(new HashSet<> (DatosCursos.getTematicas()))); TestsPI5.line("*");
		});
	}
}
