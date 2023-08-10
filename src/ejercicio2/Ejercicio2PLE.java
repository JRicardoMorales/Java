package ejercicio2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import _datos.DatosCursos;
import _datos.DatosCursos.Curso;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {

	public static Integer maxCentros;
	public static List<Curso> cursos;

	public static Integer getNumeroCursos() {
		return cursos.size();

	}

	public static Integer getMaxCentros() {
		return maxCentros;

	}

	public static List<Integer> getTematicas() {
		Set<Integer> set1 = new HashSet<>();
		for (Curso c1 : cursos) {
			set1.addAll(c1.tematicas());

		}

		return new ArrayList<>(set1);

	}

	public static Integer getNumeroTematicas() {
		return getTematicas().size();

	}

	public static List<Integer> getTematicasCurso(Integer i) {
		return cursos.get(i).tematicas();

	}

	public static Integer getNumeroTematicasCurso(Integer i) {
		return getTematicasCurso(i).size();

	}

	public static Integer contieneTematica(Integer i, Integer j) {
		return cursos.get(i).tematicas().contains(getTematicas().get(j)) ? 1 : 0;

	}

	public static Integer getCentroCurso(Integer i) {
		return cursos.get(i).centro();

	}

	public static List<Integer> getCentros() {
		Set<Integer> set2 = new HashSet<>();
		for (Curso c2 : cursos) {
			set2.add(c2.centro());
		}

		return new ArrayList<>(set2);

	}

	public static Integer getNumeroCentros() {
		return getCentros().size();

	}

	public static Double getPrecioCurso(Integer i) {
		return cursos.get(i).precio();

	}

	public static Integer ofreceCurso(Integer i, Integer j) {
		return cursos.get(i).centro().equals(getCentros().get(j)) ? 1 : 0;

	}

	public static void Ejercicio2_model() throws IOException {
		for (int i = 0; i < 3; i++) {

			DatosCursos.iniDatos("ficheros/Ejercicio2DatosEntrada" + (i + 1) + ".txt");
			cursos = DatosCursos.cursos;
			maxCentros = DatosCursos.maxCentros;

			AuxGrammar.generate(Ejercicio2PLE.class, "lsi_models/Ejercicio2.lsi",
					"gurobi_models/Ejercicio2-" + (i + 1) + ".lp");
			GurobiSolution solucion = GurobiLp.gurobi("gurobi_models/Ejercicio2-" + (i + 1) + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solucion.toString((s, d) -> d > 0.));

		}
	}

	public static void main(String[] args) throws IOException {
		Ejercicio2_model();
	}

}
