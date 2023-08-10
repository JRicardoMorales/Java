package ejercicios.ejercicio3;

import java.util.List;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record InvestigadorEdge(InvestigadorVertex source, InvestigadorVertex target, Integer action, Double weight)
	implements SimpleEdgeAction<InvestigadorVertex, Integer> {

public static InvestigadorEdge of(InvestigadorVertex s, InvestigadorVertex t, Integer a) {
	Double w = 0.; // De manera predeterminada el peso de la arista es 0
	List<Integer> NecS = s.distribution().get(s.j());
	List<Integer> NecT = t.distribution().get(s.j());

	if(!NecS.stream().allMatch(h -> h==0) && NecT.stream().allMatch(h -> h==0)) { //Si la lista de dias necesarios del trabajo j es 0 en el siguiente vertice y no lo era antes, significa que hemos realizado ese trabajo, por lo que sumamos su calidad
		w += DatosInvestigadores.getCalidad(s.j());
		
	}
	
	return new InvestigadorEdge(s, t, a, w);
	
}

@Override
public String toString() {
	return String.format("%d; %.1f", action, weight);
	
	}

}