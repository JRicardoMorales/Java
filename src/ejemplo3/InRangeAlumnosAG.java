package ejemplo3;

import java.util.List;

import _datos.DatosAlumnos;
import _soluciones.SolucionAlumnos;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.hypergraphs2.Datos;

public class InRangeAlumnosAG implements ValuesInRangeData<Integer, SolucionAlumnos> {
	public InRangeAlumnosAG(String fichero) {
		DatosAlumnos.iniDatos(fichero);
	}

	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	public Integer size() {
		return DatosAlumnos.getNumAlumnos();
	}

	public Integer min(Integer i) {
		return 0;
	}

	public Integer max(Integer i) {
		return DatosAlumnos.getNumGrupos();
	}

	public SolucionAlumnos solucion(List<Integer> ls) {
		SolucionAlumnos res = SolucionAlumnos.empty();
		for (int i = 0; i < ls.size(); i++) {
			res.add(i, ls.get(i));
		}
		return res;
	}

	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0;
		int[] v = new int[DatosAlumnos.getNumGrupos()];
		for (int i = 0; i < ls.size(); i++) {
			v[ls.get(i)]++;
			Integer a = DatosAlumnos.getAfinidad(i, ls.get(i));
			if (a > 0)
				goal += a;
			else
				error++;
		}
		for (int e : v) {
			error += e != DatosAlumnos.getTamGrupo() ? 1 : 0;
		}
		return -goal - 10000 * error;
	}
}
