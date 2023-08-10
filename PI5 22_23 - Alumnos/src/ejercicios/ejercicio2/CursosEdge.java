package ejercicios.ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CursosEdge(CursosVertex source, CursosVertex target, Integer action, Double weight)
	implements SimpleEdgeAction<CursosVertex, Integer> {

public static CursosEdge of(CursosVertex s, CursosVertex t, Integer a) { // Calculamos el peso de la arista añadiendo el precio de los cursos escogidos
	Double peso = a*DatosCursos.getPrecioCurso(s.index());
	return new CursosEdge(s, t, a, peso);
	
}

@Override
public String toString() {
	return String.format("%d; %.1f", action, weight);
	
	}

}
