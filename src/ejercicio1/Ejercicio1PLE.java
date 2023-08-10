package ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import _datos.DatosCafes;
import _datos.DatosCafes.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {

	public static List<Integer> tipos;
	public static List<Variedad> variedades;

	public static Integer getNumeroTipos() {
		return tipos.size();

	}

	public static Integer getKilosTipo(Integer j) {
		return tipos.get(j);

	}

	public static Integer getNumeroVariedades() {
		return variedades.size();

	}

	public static Integer getBeneficiosVariedad(Integer i) {
		return variedades.get(i).beneficio();

	}

	public static Double getKilosTipoVariedad(Integer j, Integer i) {
		return variedades.get(i).composicion().get(j);

	}

	public static void Ejercicio1_model() throws IOException {
		for (int i = 0; i < 3; i++) {
			DatosCafes.iniDatos("ficheros/Ejercicio1DatosEntrada" + (i + 1) + ".txt");
			tipos = DatosCafes.tipos;
			variedades = DatosCafes.variedades;

			AuxGrammar.generate(Ejercicio1PLE.class, "lsi_models/Ejercicio1.lsi",
					"gurobi_models/Ejercicio1-" + (i + 1) + ".lp");
			GurobiSolution solucion = GurobiLp.gurobi("gurobi_models/Ejercicio1-" + (i + 1) + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solucion.toString((s, d) -> d > 0.) + "\n\n");

		}
	}

	public static void main(String[] args) throws IOException {
		Ejercicio1_model();
	}

}
