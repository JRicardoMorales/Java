package ejercicio3;

import java.util.List;
import _datos.DatosInvestigadores;
import _soluciones.SolucionesInvestigador;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InvestigadoresInRangeAG implements ValuesInRangeData<Integer, SolucionesInvestigador> {

	public InvestigadoresInRangeAG(String fichero) {
		DatosInvestigadores.iniDatos(fichero);
	}

	public Integer size() {
		return DatosInvestigadores.getNumeroTrabajos() * DatosInvestigadores.getNumeroInvestigadores();

	}

	public ChromosomeType type() {
		return ChromosomeType.Range;

	}

	public Double fitnessFunction(List<Integer> value) {
		double goal = 0;
		double error = 0;
		double k2 = 0;
		double capacidad = 0;

		Integer numeroT = DatosInvestigadores.getNumeroTrabajos();
		Integer numeroI = DatosInvestigadores.getNumeroInvestigadores();
		Integer numeroE = DatosInvestigadores.getNumeroEspecialidades();

		for (int j = 0; j < numeroT; j++) {

			Integer j2 = j * numeroI;
			List<Integer> t = value.subList(j2, j2 + numeroI);
			Boolean hace = true;
			for (int k = 0; k < numeroE; k++) {

				Integer suma = 0;

				for (int i = 0; i < numeroI; i++) {
					suma += t.get(i) * DatosInvestigadores.trabajadorEspecialidad(i, k);

				}

				if (suma != DatosInvestigadores.diasNecesarios(j, k)) {
					hace = false;
					error += Math.abs(suma - DatosInvestigadores.diasNecesarios(j, k));
				}
			}

			if (hace) {
				goal += DatosInvestigadores.getCalidad(j);
			}
		}

		for (int i = 0; i < numeroI; i++) {

			capacidad = 0;

			for (int i2 = i; i2 < value.size(); i2 += numeroI) {
				capacidad += value.get(i2);
			}

			if (capacidad > DatosInvestigadores.diasDisponibles(i)) {
				error += capacidad - DatosInvestigadores.diasDisponibles(i);
			}
		}

		Integer suma = 0;
		for (int j = 0; j < numeroT; j++) {
			suma += DatosInvestigadores.getCalidad(j);
		}

		k2 = Math.pow(suma, 2);
		return goal - k2 * error;
	}

	public SolucionesInvestigador solucion(List<Integer> value) {
		System.out.println(value);
		return SolucionesInvestigador.of_Range(value);

	}

	public Integer max(Integer i) {
		Integer i2 = i % DatosInvestigadores.getNumeroInvestigadores();
		return DatosInvestigadores.diasDisponibles(i2) + 1;

	}

	public Integer min(Integer i) {
		return 0;

	}

}
