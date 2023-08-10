package ejercicios.ejercicio4;

import java.util.function.Predicate;


public class ClientesHeuristic {

public static Double heuristic(ClientesVertex v1, Predicate<ClientesVertex> goal, ClientesVertex v2) {
	Double res = 0.; // Establecemos 0 como valor por defecto y el vértice actual;
	ClientesVertex actual = v1;

	for(int ind=0; ind<=DatosClientes.getNumeroVertices(); ind++) { //	Para cada vértice disponible:
		Double benef = 0.;
		Integer op = 0;
		for(Integer a : actual.actions()) { //	Obtenemos la acción que nos da el mayor beneficio y lo sumamos
			if(DatosClientes.getBeneficio(a) > benef) {
				benef = DatosClientes.getBeneficio(a);
				op = a;
			}
		}
		res += benef;
		if(op!=0) { //	Si la alternativa es distinta a 0 (no es un vértice 'final')
			actual = actual.neighbor(op); //	Obtenemos el vecino por la arista de mayor beneficio
		} else { //	o rompemos el bucle (hemos llegado un vértice final)
			break;
		}
	}
	
	return res;
	
	}

}
