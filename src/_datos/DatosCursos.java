package _datos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import us.lsi.common.Files2;

public class DatosCursos {

	public static List<Curso> cursos;
	public static Integer maxCentros;

	public record Curso(Integer id, List<Integer> tematicas, Double precio, Integer centro) {

		public static int contador;
		public static Curso create(String s1) {
			List<Integer> a1 = new ArrayList<>();
			String[] s2 = s1.split(":");
			String[] s3 = s2[0].substring(1, s2[0].length() - 1).split(",");
			for (String s4 : s3) {
				a1.add(Integer.parseInt(s4.trim()));

			}

			return new Curso(contador++, new ArrayList<>(a1), Double.parseDouble(s2[1].trim()),
					Integer.parseInt(s2[2].trim()));

		}
	}

	public static void iniDatos(String s5) {
		List<Curso> a2 = new ArrayList<>();
		Curso.contador = 0;

		List<String> a3 = Files2.linesFromFile(s5);
		maxCentros = Integer.parseInt(a3.get(0).split("=")[1].trim());

		for (String s6 : a3.subList(1, a3.size())) {
			a2.add(Curso.create(s6));

		}

		cursos = new ArrayList<>(a2);
		toConsole();

	}

	public static Integer getNumeroCursos() {
		return cursos.size();

	}

	public static Integer getMaxCentros() {
		return maxCentros;

	}

	public static List<Integer> getTematicas() {
		Set<Integer> s7 = new HashSet<>();
		for (Curso c1 : cursos) {
			s7.addAll(c1.tematicas());

		}

		return new ArrayList<>(s7);

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

	public static Double getPrecioCurso(Integer i) {
		return cursos.get(i).precio();

	}

	public static List<Integer> getCentros() {
		Set<Integer> s8 = new HashSet<>();
		for (Curso c2 : cursos) {
			s8.add(c2.centro());

		}

		return new ArrayList<>(s8);

	}

	public static Integer ofreceCurso(Integer i, Integer j) {
		return cursos.get(i).centro().equals(getCentros().get(j)) ? 1 : 0;

	}

	public static Integer getNumeroCentros() {
		return getCentros().size();

	}

	public static void toConsole() {
		System.out.println("Numero maximo de centros seleccionados: " + maxCentros + "\nCursos: " + cursos);

	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			System.out.println("DATOS DE ENTRADA " + (i + 1) + " ");
			String fichero = "ficheros/Ejercicio2DatosEntrada" + String.valueOf(i + 1) + ".txt";
			iniDatos(fichero);
			System.out.println(" ");
			System.out.println(" ");

		}
	}

}
