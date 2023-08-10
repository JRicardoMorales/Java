package ejercicio4;

import java.util.List;
import _datos.DatosClientes;
import _soluciones.SolucionesCliente;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GrafoAG implements SeqNormalData<SolucionesCliente> {

	public GrafoAG(String fichero) {
		DatosClientes.iniDatos(fichero);

	}

	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	public Double fitnessFunction(List<Integer> value) {
		double goal = 0;
		double error = 0;
		double k4 = 0;
		double suma = 0;

		for (int i = 0; i < value.size(); i++) {
			if (i == 0) {

				if (DatosClientes.existeArista(0, value.get(i))) {
					suma += DatosClientes.getPeso(0, value.get(i));
					goal += DatosClientes.getBeneficio(value.get(i)) - suma;

				}

				else {
					error++;

				}

			} else {

				if (DatosClientes.existeArista(value.get(i - 1), value.get(i))) {
					suma += DatosClientes.getPeso(value.get(i - 1), value.get(i));
					goal += DatosClientes.getBeneficio(value.get(i)) - suma;

				} else {
					error++;

				}
			}
		}

		if (value.get(value.size() - 1) != 0) {
			if (error != 0) {
				error = error * 2;
			} else {
				error += 2;
			}
		}

		suma = 0.;
		for (int i = 0; i < value.size(); i++) {

			suma += DatosClientes.getBeneficio(value.get(i));
		}

		k4 = Math.pow(suma, 2);

		return goal - k4 * error;

	}

	public SolucionesCliente solucion(List<Integer> value) {
		return SolucionesCliente.of_Range(value);

	}

	public Integer itemsNumber() {
		return DatosClientes.getNumeroVertices();
	}
}
