package ejemplos.ejemplo2.manual;

import java.util.Set;

import _soluciones.SolucionSubconjuntos;
import us.lsi.common.Set2;

public class SubconjuntosBT {

	private static Double mejorValor;
	private static SubconjuntosState estado;
	private static Set<SolucionSubconjuntos> soluciones;
	
	public static void search() {
		soluciones = Set2.newTreeSet();
		mejorValor = Double.MAX_VALUE; // Estamos minimizando
		
		// explicacion = el mejor valor es el mejor valor encontrado que intento minimzar
		// si lo que tengo que minimizar es el peso, el mejor Valor será encontrar 
		// la suma mínima del peso de los subconjuntos
		
		// por eso, cada vez que elija un subconjunto, le añado el peso al acumulado
		
		estado = SubconjuntosState.initial();
		bt_search();
	}

	private static void bt_search() {
		
		// el objetivo es generar un conjunto de soluciones a partir de estados válidos
		if (estado.esSolucion()) {
			Double valorObtenido = estado.acumulado;
			
			// si estado es solucion, ¿entonces me quedo con ella del tirón?
			// depende, si el valor acumulado (estado.acumulado) es MENOR que mi 
			// mejorValor, entonces sí
		
			if (valorObtenido < mejorValor) {  // Estamos minimizando
				mejorValor = valorObtenido;
				soluciones.add(estado.getSolucion());
			}
		} else if(!estado.esTerminal()){
			// siempre y cuando la rama no haya terminado, estudio las alternativas
			// de cada estado 
			for (Integer a: estado.alternativas()) {
				
				// ¿me interesa ir por esa rama?
				// depende, si la cota (lo que tengo + lo que sumo + lo que me queda) es
				// MENOR que mi mejor valor, entonces sí, porque estoy minimizando
				if (estado.cota(a) <= mejorValor) {  // Estamos minimizando
					estado.forward(a);
					bt_search();
					estado.back();
					
					// explicación: un algoritmo BT explora todas los estados
					// desde un estado concreto. Entonces, primero voy siempre
					// hacia delante (forward), llamo recursivamente al propio algoritmo
					// para seguir avanzando hacia delante (bt_search) y cuando terminen
					// todas las recursiones, vuelvo hacia atrás (back)
					
				}
			}
		}
	}

	public static Set<SolucionSubconjuntos> getSoluciones() {
		return soluciones;
	}

}
