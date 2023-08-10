package ejercicio1;

import java.util.List;
import _datos.DatosCafes;
import _soluciones.SolucionesCafe;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeCafeAG implements ValuesInRangeData<Integer, SolucionesCafe> {

	public InRangeCafeAG(String fichero) {
		DatosCafes.iniDatos(fichero);
	}

	public Integer size() {
		return DatosCafes.getNumeroVariedades();

	}

	public ChromosomeType type() {
		return ChromosomeType.Range;

	}

	public Double fitnessFunction(List<Integer> value) {
		double goal = 0;
		double error = 0;
		double dif = 0;
		double k = 0;
		for (int i = 0; i < size(); i++) {
			if (value.get(i) > 0) {
				goal += value.get(i) * DatosCafes.getBeneficiosVariedad(i);
			}
		}

		for (int j = 0; j < DatosCafes.getNumeroTipos(); j++) {

			dif = 0;
			for (int i = 0; i < size(); i++) {
				dif += value.get(i) * DatosCafes.getKilosTipoVariedad(j, i);
			}

			if (dif > DatosCafes.getKilosTipo(j)) {
				error += dif - DatosCafes.getKilosTipo(j);
			}
		}

		for (int i = 0; i < size(); i++) {
			k += Math.pow((DatosCafes.getMaxKilosVariedad(i) * DatosCafes.getBeneficiosVariedad(i)), 2);
		}

		return goal - k * error;
	}

	public Integer min(Integer i) {
		return 0;

	}

	public Integer max(Integer i) {
		return DatosCafes.getMaxKilosVariedad(i) + 1;

	}

	public SolucionesCafe solucion(List<Integer> value) {
		return SolucionesCafe.of_Range(value);

	}

}
