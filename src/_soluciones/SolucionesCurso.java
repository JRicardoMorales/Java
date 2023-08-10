package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import _datos.DatosCursos;
import _datos.DatosCursos.Curso;

public class SolucionesCurso {

	public static SolucionesCurso of_Range(List<Integer> value) {
		return new SolucionesCurso(value);

	}

	private List<Curso> cursos;
	private Double precio;

	public SolucionesCurso() {
		cursos = new ArrayList<>();
		precio = 0.;

	}

	public SolucionesCurso(List<Integer> ls) {
		precio = 0.;
		cursos = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				precio += DatosCursos.getPrecioCurso(i);
				cursos.add(DatosCursos.cursos.get(i));
			}
		}

	}

	public static SolucionesCurso empty() {
		return new SolucionesCurso();

	}

	public String toString() {
		String s1 = cursos.stream().map(c -> "S" + c.id())
				.collect(Collectors.joining(", ", "Cursos seleccionados: {", "}\n"));
		return String.format("%sCoste total: %.1f", s1, precio);
	}

}
