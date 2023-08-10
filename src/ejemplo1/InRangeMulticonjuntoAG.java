package ejemplo1;

import java.util.List;
import _datos.DatosMulticonjunto;
import _soluciones.SolucionMulticonjunto;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeMulticonjuntoAG implements ValuesInRangeData<Integer, SolucionMulticonjunto> {

	public InRangeMulticonjuntoAG(String linea) {
		DatosMulticonjunto.iniDatos(linea);
	}

	public Integer size() {
		return DatosMulticonjunto.getNumElementos();
	}

	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	public Double fitnessFunction(List<Integer> value) {
		double goal = 0;
		double suma = 0;
		double error = 0;
		for (int i = 0; i < size(); i++) {
			if (value.get(i) > 0) {
				goal += value.get(i);
				suma += DatosMulticonjunto.getElemento(i) * value.get(i);
			}
		}
		error += Math.abs(suma - DatosMulticonjunto.getSuma());
		return -goal - 1000 * error;
	}

	public SolucionMulticonjunto solucion(List<Integer> value) {
		return SolucionMulticonjunto.of_Range(value);
	}

	public Integer max(Integer i) {
		return DatosMulticonjunto.getMultiplicidad(i) + 1;
	}

	public Integer min(Integer i) {
		return 0;
	}

}
