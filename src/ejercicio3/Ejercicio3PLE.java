package ejercicio3;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import _datos.DatosInvestigadores;
import _datos.DatosInvestigadores.Investigador;
import _datos.DatosInvestigadores.Trabajo;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {

	public static List<Trabajo> trabajos;
	public static List<Investigador> investigadores;

	public static Integer getNumeroInvestigadores() {
		return investigadores.size();

	}

	public static Integer getNumeroTrabajos() {
		return trabajos.size();

	}

	public static Integer getNumeroEspecialidades() {
		return trabajos.get(0).days().size();

	}

	public static Integer trabajadorEspecialidad(Integer i, Integer j) {
		return investigadores.get(i).especiality().equals(j) ? 1 : 0;

	}

	public static Integer getCalidad(Integer j) {
		return trabajos.get(j).quality();

	}

	public static Integer diasDisponibles(Integer i) {
		return investigadores.get(i).capacity();

	}

	public static Integer diasNecesarios(Integer j, Integer i) {
		return trabajos.get(j).days().get(i);

	}

	public static Integer getMaxCapacity() {
		return investigadores.stream().map(i -> i.capacity()).max(Comparator.naturalOrder()).get() + 1;

	}

	public static void Ejercicio3_model() throws IOException {
		for (int i = 0; i < 3; i++) {
			DatosInvestigadores.iniDatos("ficheros/Ejercicio3DatosEntrada" + (i + 1) + ".txt");
			investigadores = DatosInvestigadores.investigadores;
			trabajos = DatosInvestigadores.trabajos;

			AuxGrammar.generate(Ejercicio3PLE.class, "lsi_models/Ejercicio3.lsi",
					"gurobi_models/Ejercicio3-" + (i + 1) + ".lp");
			GurobiSolution solucion = GurobiLp.gurobi("gurobi_models/Ejercicio3-" + (i + 1) + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solucion.toString((s, d) -> d > 0.));

		}
	}

	public static void main(String[] args) throws IOException {
		Ejercicio3_model();
	}

}
