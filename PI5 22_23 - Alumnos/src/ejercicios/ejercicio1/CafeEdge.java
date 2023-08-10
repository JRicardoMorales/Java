package ejercicios.ejercicio1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CafeEdge(CafeVertex source, CafeVertex target, Integer action, Double weight)
	implements SimpleEdgeAction<CafeVertex,Integer> {

public static CafeEdge of(CafeVertex s, CafeVertex t, Integer a) {  // Calculo del peso como el beneficio de la variedad por el precio de dicha variedad
	Double peso = a*DatosCafes.getBeneficioVariedad(s.index()).doubleValue();
	return new CafeEdge(s, t, a, peso);
	
}

@Override
public String toString() {
	return String.format("%d; %.1f", action, weight);
	
	}

}