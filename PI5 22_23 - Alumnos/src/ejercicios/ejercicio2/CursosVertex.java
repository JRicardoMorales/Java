package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import us.lsi.graphs.virtual.VirtualVertex;

public record CursosVertex(Integer index, Set<Integer> remaining, Set<Integer> centers)
	implements VirtualVertex<CursosVertex, CursosEdge, Integer> {

public static CursosVertex of(Integer i, Set<Integer> rest, Set<Integer> cent) {
	return new CursosVertex(i, rest, cent);
	
}


public static CursosVertex initial() {
	return of(0,new HashSet<>(DatosCursos.getTematicas()), new HashSet<>());
	
}


//Vertice objetivo una vez que ya hemos recorrido todos los cursos
public static Predicate<CursosVertex> goal(){ 
	return v-> v.index()==DatosCursos.getNumeroCursos();
	
}


// Solucion valida si hemos recorrido todas las tematicas
public static Predicate<CursosVertex> goalHasSolution(){ 
	return v -> v.remaining().isEmpty();
	
}


@Override
public List<Integer> actions() {
	List<Integer> acc = new ArrayList<>();
	if(this.index()<DatosCursos.getNumeroCursos()) { //	Primero miramos que por este camino es posible cubrir todas las temáticas restantes	
	Set<Integer> posibles = new HashSet<>();
	List<List<Integer>> tematicas = IntStream.range(this.index(), DatosCursos.getNumeroCursos())
			.boxed()
			.map(i -> DatosCursos.getTematicasCurso(i))
			.toList();
	tematicas.forEach(ls -> posibles.addAll(ls));

	if(posibles.containsAll(this.remaining())) { 
		acc.add(0);  //	Si es posible, añadimos 0 de manera predeterminada
		Set<Integer> centros = new HashSet<>(this.centers());  //Calculamos el número de centros si añadimos el curso dado
		centros.add(DatosCursos.getCentroCurso(this.index()));
		if(!this.remaining().isEmpty() && centros.size()<=DatosCursos.maxCentros) { //Si se cumple la restricción, añadimos la opción de coger este curso
			acc.add(1);
			}
		}
	}
	
return acc;

}


@Override
public CursosVertex neighbor(Integer a) { // Copiamos el remaining y los centros escogidos
	Set<Integer> tematicas = new HashSet<>(remaining);
	Set<Integer> centros = new HashSet<>(centers);
	if(a==1) { //Si escogemos este curso, eliminamos las temáticas del remaining y añadimos el centro al conjunto
		tematicas.removeAll(DatosCursos.getTematicasCurso(index));
		centros.add(DatosCursos.getCentroCurso(index));
		
		}
	
		return of(index+1, tematicas, centros);
		
	}

		@Override
public CursosEdge edge(Integer a) {
	return CursosEdge.of(this, neighbor(a), a);
		
}

public CursosEdge greedyEdge() { //De manera predeterminada decidimos no escoger el curso estudiado
	CursosEdge res = edge(0);
	if(this.actions().size()>1) { //Si es posible escogerlo, vemos si escogiéndolo eliminamos alguna temática del remaining
	CursosVertex sig = this.neighbor(1);
	res = sig.remaining().size()<this.remaining().size()? edge(1):
	edge(0);
	}
		
	return res;
		
}

		@Override
public String toString() {
	return String.format("%d; %s; %s", index, remaining, centers);
	
	}

}
			
			
			
			
			
			
			
			
			
			

