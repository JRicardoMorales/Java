package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import us.lsi.graphs.virtual.VirtualVertex;

public record CafeVertex(Integer index, List<Double> remaining)
	implements VirtualVertex<CafeVertex,CafeEdge,Integer> {

public static CafeVertex of(Integer i, List<Double> rest) {
	return new CafeVertex(i, rest);
	
}


public static CafeVertex initial() {
	return of(0, DatosCafes.tipos.stream()
			.map(i -> i.doubleValue())
			.toList());
	
}


// Es el ultimo vertice accesible una vez que ya ha pasado por todas las variedades(objetivo)
public static Predicate<CafeVertex> goal(){
	return v-> v.index()==DatosCafes.getNumeroVariedades();
	
}


// Solucion valida si el remaining es un numero natural positivo
public static Predicate<CafeVertex> goalHasSolution(){
	return v -> v.remaining.stream()
			.allMatch(i -> i>=0);
	
}


@Override
public List<Integer> actions() {
	List<Integer> acc = new ArrayList<>();
	if(this.index()<DatosCafes.getNumeroVariedades()) { //Calculamos los kilos máximos que podemos coger de la variedad actual con el remaining actual
		List<Double> listaMax = maxKilosVar(this.remaining(), this.index());
		Integer maxKg = listaMax.stream()
				.min(Comparator.naturalOrder())
				.get()
				.intValue();
		if(maxKg<0) { //Si nos sale un resultado menor que 0, ponemos 0
			maxKg=0;
			
}
//	añadimos a la lista valores del 0 al numero correspondiente a maxKg
		for(int i=0; i<=maxKg; i++) {
			acc.add(i);
			
			}
	}
	
	return acc;

}


public static List<Double> maxKilosVar(List<Double> rem, Integer indice) {
	List<Double> res = new ArrayList<>();  // Calculamos, para cada valor del remaining, los kilos que nos permite hacer de la variedad estudiada

	for (int j = 0; j < rem.size(); j++) {
		res.add(rem.get(j) / DatosCafes.getKilosTipoVariedad(j, indice));
		
	}
	
// Eliminamos los resultantes de dividir por 0
	res.removeIf(n -> n.equals(Double.POSITIVE_INFINITY));
	return res;
	
}

@Override
public CafeVertex neighbor(Integer a) { 
	List<Double> proporcion = DatosCafes.getVariedades().get(index).composicion(); //Proporcion necesaria para la variedad dada
	List<Double> rest = new ArrayList<>();

	for(int i=0; i<remaining.size(); i++) {
		Double restante = remaining.get(i)-a*proporcion.get(i); // Por cada tipo, le restamos los kilos usados al coger 'a' kilos de la variedad dada
		rest.add(restante);
		
	}
	
return of(index+1, rest);

}

@Override
public CafeEdge edge(Integer a) {
	return CafeEdge.of(this, neighbor(a), a);
	
}

public CafeEdge greedyEdge() {
// Buscamos la arista con más kilos de la variedad dada
	Integer res = this.actions().stream()
			.max(Comparator.naturalOrder())
			.get();
	return edge(res);
	
}

@Override
public String toString() {
	return String.format("%d; %s", index, remaining);
	
	}

}