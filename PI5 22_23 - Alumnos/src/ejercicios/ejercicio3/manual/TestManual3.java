package ejercicios.ejercicio3.manual;

import java.util.List;
import ejercicios.ejercicio3.DatosInvestigadores;
import _utils.TestsPI5;
import us.lsi.common.String2;

public class TestManual3 {
	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
			DatosInvestigadores.iniDatos("ficheros/Ejercicio3DatosEntrada"+num_test+".txt"); InvestigadorBT.search();
			InvestigadorBT.getSoluciones().forEach(s -> String2.toConsole("Solucion obtenida: %s\n", s)); TestsPI5.line("*");
		});
	}

}
