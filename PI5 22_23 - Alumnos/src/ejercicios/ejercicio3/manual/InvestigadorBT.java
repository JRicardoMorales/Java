package ejercicios.ejercicio3.manual;

import java.util.Set;
import java.util.TreeSet;
import ejercicios.ejercicio3.SolucionesInvestigador;

public class InvestigadorBT {
	private static Integer maxValue;
	private static Set<SolucionesInvestigador> soluciones;
	private static InvestigadorState estado;

public static void search() {
	soluciones = new TreeSet<>();
	maxValue = Integer.MIN_VALUE;
	estado = InvestigadorState.initial();
	bt_search();
	
}

private static void bt_search() {
	if(estado.hasSolution()) { //Si es un vértice final válido, calculamos su solución y la añadimos al conjunto de soluciones, modificando el valor de maxValue
		Integer valor = estado.getAcum();
		if(valor > maxValue) {
			maxValue = valor;
			soluciones.add(estado.getSolucion());
		}
	} else if(!estado.goal()) { //Si no es un vértice final, avanzamos por el grafo y obtenemos soluciones de forma recursiva
		for(Integer a: estado.actions()) {
			Double cota = estado.cota(a);
			if(cota >= maxValue) {
				estado.forward(a);
				bt_search();
				estado.back();
			}
		}
	}
}

public static Set<SolucionesInvestigador> getSoluciones() {
	return soluciones;
	}

}
