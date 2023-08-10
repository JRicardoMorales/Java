package _soluciones;

import java.util.ArrayList;
import java.util.List;
import _datos.DatosClientes;
import utils.Cliente;

public class SolucionesCliente {

	public static SolucionesCliente of_Range(List<Integer> value) {
		return new SolucionesCliente(value);
	}

	private Double ben;
	private Double kms;
	private List<Cliente> clientes;

	private SolucionesCliente() {
		ben = 0.;
		kms = 0.;
		clientes = new ArrayList<>();
		Cliente c0 = DatosClientes.getCliente(0);
		clientes.add(c0);

	}

	private SolucionesCliente(List<Integer> value) {
		ben = 0.;
		kms = 0.;
		clientes = new ArrayList<>();
		Cliente c0 = DatosClientes.getCliente(0);
		clientes.add(c0);

		for (int i = 0; i < value.size(); i++) {
			Cliente c4 = DatosClientes.getCliente(value.get(i));
			clientes.add(c4);

			if (i != 0) {
				if (DatosClientes.existeArista(value.get(i - 1), value.get(i))) {
					kms += DatosClientes.getPeso(value.get(i - 1), value.get(i));
					ben += DatosClientes.getBeneficio(value.get(i)) - kms;
				}

			} else {
				if (DatosClientes.existeArista(0, value.get(i))) {
					kms += DatosClientes.getPeso(0, value.get(i));
					ben += DatosClientes.getBeneficio(value.get(i)) - kms;
				}
			}
		}
	}

	public static SolucionesCliente empty() {
		return new SolucionesCliente();

	}

	public String toString() {
		List<Integer> list4 = clientes.stream().map(c -> c.id()).toList();
		return "Camino seguido:\n" + list4 + "\nBeneficio: " + ben + "\nDistancia: " + kms;
	}

}
