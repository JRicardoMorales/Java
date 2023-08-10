package ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import _datos.DatosCursos;
import _soluciones.SolucionesCurso;
import us.lsi.ag.BinaryData;

public class BinarioCursoAG implements BinaryData<SolucionesCurso> {

	public BinarioCursoAG(String s1) {
		DatosCursos.iniDatos(s1);
	}

	public Integer size() {
		return DatosCursos.getNumeroCursos();

	}

	public Double fitnessFunction(List<Integer> value) {
		double goal = 0;
		double error = 0;
		double k = 0;
		double suma = 0;

		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0) {
				goal += DatosCursos.getPrecioCurso(i);

			}
		}

		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0) {
				set1.add(DatosCursos.getCentroCurso(i));
				set2.addAll(DatosCursos.getTematicasCurso(i));
			}
		}

		Integer m = DatosCursos.getNumeroTematicas();
		Integer n2 = DatosCursos.maxCentros;
		if (set2.size() < m) {
			error += m - set2.size();
		}

		if (set1.size() > n2) {
			error += set1.size() - n2;
		}

		for (int i = 0; i < value.size(); i++) {
			suma += DatosCursos.getPrecioCurso(i);
		}

		k += Math.pow(suma, 2);
		return -goal - k * error;

	}

	public SolucionesCurso solucion(List<Integer> value) {
		return SolucionesCurso.of_Range(value);
	}

}