package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import us.lsi.graphs.virtual.VirtualVertex;

public record InvestigadorVertex (Integer z, Integer i, Integer j, List<Integer> days, List<List<Integer>> distribution)
	implements VirtualVertex<InvestigadorVertex, InvestigadorEdge, Integer> {

public static InvestigadorVertex of(Integer z, List<Integer> days, List<List<Integer>> distribution) { // Calculamos la i y la j de cada vértice en función de z
	Integer ii = z/DatosInvestigadores.getNumeroTrabajos();
	Integer jj = z%DatosInvestigadores.getNumeroTrabajos();
	return new InvestigadorVertex(z, ii, jj, days, distribution);
	
}


public static InvestigadorVertex initial() {
	List<Integer> dias = DatosInvestigadores.investigadores.stream()
			.map(in -> in.capacidad())
			.toList();
	List<List<Integer>> dist = DatosInvestigadores.trabajos.stream()
			.map(t -> t.dias())
			.toList();
	return of(0, dias, dist);
	
}

//Vertice objetivo si hemos visto todas las opciones investigador-trabajo
public static Predicate<InvestigadorVertex> goal() {
	return v -> v.z() == DatosInvestigadores.getNumeroInvestigadores() * DatosInvestigadores.getNumeroTrabajos();
	
}


// En este caso todo vértice final es válido
public static Predicate<InvestigadorVertex> goalHasSolution() {
	return v -> true;
	
}


@Override
public List<Integer> actions() {
	List<Integer> acc = new ArrayList<>();
	if(this.z() < (DatosInvestigadores.getNumeroInvestigadores() * DatosInvestigadores.getNumeroTrabajos())) { 
		acc.add(0); //Añadimos 0 como acción trivial  
		Integer neces = this.distribution().get(j).get(DatosInvestigadores.getEspecialidadInvestigador(i)); //Calculamos los días disponibles del investigador i y los días necesarios de la especialidad del trabajador i en el trabajo j
		Integer dias = this.days().get(i);

		if(neces>0 && dias>0) { //	Si ambos son mayores que 0 obtenemos el minimo entre ambos y añadimos a las acciones desde 1 hasta dicho valor
			Integer posible = Math.min(neces, dias) +1;
			acc.addAll(IntStream.range(1, posible)
					.boxed()
					.toList());
		}
	}
	
	return acc;

}


@Override
public InvestigadorVertex neighbor(Integer a) { // Hacemos una copia de todas las listas del vértice
	List<Integer> dias = new ArrayList<>(this.days());
	List<List<Integer>> dist= new ArrayList<>();
	for(int i=0; i<this.distribution().size(); i++) { //Al ser una lista de listas, hay que hacer una copia de cada una de ellas
		dist.add(new ArrayList<>(this.distribution().get(i)));

}

	Integer e = DatosInvestigadores.getEspecialidadInvestigador(i); 
	dias.set(i, dias.get(i)-a); // Restamos los días escogidos de las horas del investigador i y de los días necesarios del trabajador j para la especialidad e
	dist.get(j).set(e, dist.get(j).get(e)-a);
	return of(this.z()+1, dias, dist);
	
}


@Override
public InvestigadorEdge edge(Integer a) {
	return InvestigadorEdge.of(this, neighbor(a), a);
	
}

public InvestigadorEdge greedyEdge() { // Devolvemos la opción máxima de las acciones disponibles
	Integer res = this.actions().stream()
			.max(Comparator.naturalOrder())
			.get();
	return edge(res);
	
}


@Override
public String toString() {
	return String.format("%d; %s; %s", z, days, distribution);
		
	}

}
