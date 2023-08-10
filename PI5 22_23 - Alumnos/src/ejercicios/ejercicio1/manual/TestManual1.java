package ejercicios.ejercicio1.manual;

import java.util.List;
import ejercicios.ejercicio1.DatosCafes;
import _utils.TestsPI5;
import us.lsi.common.String2;


public class TestManual1 {
	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
			DatosCafes.iniDatos("ficheros/Ejercicio1DatosEntrada"+num_test+".txt"); String2.toConsole("\nSolucion obtenida: %s\n",
					CafePDR.pd(DatosCafes.tipos.stream().map(i -> i.doubleValue()).toList())); TestsPI5.line("*");
			});
	}
}