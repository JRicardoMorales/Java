package ejercicios.ejercicio4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record ClientesEdge(ClientesVertex source, ClientesVertex target, Integer action, Double weight)
	implements SimpleEdgeAction<ClientesVertex, Integer> {

public static ClientesEdge of(ClientesVertex s, ClientesVertex t, Integer a) { // Calculamos el peso como el beneficio del vertice t menos los kms recorridos hasta t
	Double w = DatosClientes.getBeneficio(a) - t.kms();
	return new ClientesEdge(s, t, a, w);
}


@Override
public String toString() {
	return String.format("%d; %.1f", action, weight);
	}

}
