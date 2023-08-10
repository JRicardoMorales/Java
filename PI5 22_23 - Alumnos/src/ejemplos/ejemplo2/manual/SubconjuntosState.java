package ejemplos.ejemplo2.manual;

import java.util.List;

import _datos.DatosSubconjuntos;
import _soluciones.SolucionSubconjuntos;
//import otros.ejemplo2.SubconjuntosProblem;
import us.lsi.common.List2;

public class SubconjuntosState {

	
	// esta clase modela únicamente las varianzas entre estados
	
	SubconjuntosProblem actual;
	Double acumulado;
	List<Integer> acciones;
	List<SubconjuntosProblem> anteriores;
	
	private SubconjuntosState(SubconjuntosProblem p, Double a, 
		List<Integer> ls1, List<SubconjuntosProblem> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}

	public static SubconjuntosState initial() {
		SubconjuntosProblem pi = SubconjuntosProblem.initial();
		return of(pi, 0., List2.empty(), List2.empty());
	}

	public static SubconjuntosState of(SubconjuntosProblem prob, Double acum, List<Integer> lsa,
			List<SubconjuntosProblem> lsp) {
		return new SubconjuntosState(prob, acum, lsa, lsp);
	}

	public void forward(Integer a) {
		// hacia delante
		// aumenta el acumulado
		acumulado += a * DatosSubconjuntos.getPeso(actual.index());
		
		// añado el paso por el que paso XD
		acciones.add(a);
		
		// añado el subproblema
		anteriores.add(actual);
		
		// obtengo el siguiente estado
		actual = actual.neighbor(a);
	}

	public void back() {
		// hacia atras
		// obtengo la última accion por la que he pasado
		int last = acciones.size() - 1;
		
		// obtengo el último subproblema por el que he pasado
		var prob_ant = anteriores.get(last);
		
		// deshago el acumulado
		acumulado -= acciones.get(last) * DatosSubconjuntos.getPeso(prob_ant.index());
		acciones.remove(last);
		anteriores.remove(last);
		
		// obtengo el estado anterior
		actual = prob_ant;
	}

	public List<Integer> alternativas() {
		// las alternativas se modelan en el propio problema
		return actual.actions();
	}

	public Double cota(Integer a) {
		Double weight = a > 0 ? DatosSubconjuntos.getPeso(actual.index()) : 0.;
		
		// LO QUE LLEVO + LO QUE VOY A AUMENTAR + LO QUE ME QUEDA
		return acumulado + weight + actual.neighbor(a).heuristic();
	}

	public Boolean esSolucion() {
		// devuelve true cuando la rama tenga solucion
		return actual.remaining().isEmpty();
	}

	public boolean esTerminal() {
		// devuelve true cuando se haya llegado al final de la rama
		return actual.index() == DatosSubconjuntos.getNumSubconjuntos();
	}

	public SolucionSubconjuntos getSolucion() {
		return SolucionSubconjuntos.of(acciones);
	}

}
