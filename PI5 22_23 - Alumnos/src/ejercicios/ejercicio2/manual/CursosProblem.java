package ejercicios.ejercicio2.manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import ejercicios.ejercicio2.DatosCursos;
import us.lsi.common.List2;
import us.lsi.common.Set2;


public record CursosProblem(Integer index, Set<Integer> remaining, Set<Integer> centers) {
	public static CursosProblem intial() {
		return of(0,new HashSet<>(DatosCursos.getTematicas()), new HashSet<>());
		
}

public static CursosProblem of(Integer i, Set<Integer> rem, Set<Integer> c) {
	return new CursosProblem(i, rem, c);
	
}


//Como CursosVertex
public List<Integer> actions() {
	List<Integer> acc = new ArrayList<>();
	if(this.index()<DatosCursos.getNumeroCursos()) {
		Set<Integer> posibles = new HashSet<>();
		List<List<Integer>> tematicas = IntStream.range(this.index(), DatosCursos.getNumeroCursos())
			.boxed()
			.map(i -> DatosCursos.getTematicasCurso(i))
			.toList();
		tematicas.forEach(ls -> posibles.addAll(ls));

		if(posibles.containsAll(this.remaining())) {
			acc.add(0);
			Set<Integer> centros = new HashSet<>(this.centers());
			centros.add(DatosCursos.getCentroCurso(this.index()));

			if(!this.remaining().isEmpty() && centros.size()<=DatosCursos.maxCentros) {
				acc.add(1);
			}
		}
	}
	
return acc;

}

public CursosProblem neighbor(Integer a) {
	Set<Integer> tematicas = new HashSet<>(remaining);
	Set<Integer> centros = new HashSet<>(centers);

	if(a==1) {
		tematicas.removeAll(DatosCursos.getTematicasCurso(index));
		centros.add(DatosCursos.getCentroCurso(index));
		
}
	
	return of(index+1, tematicas, centros);
	
}

//Como CursosHeuristic
public Double heuristic() {
	Double res = 0.;

	if(!this.remaining().isEmpty()) {
		res += IntStream.range(this.index(), DatosCursos.getNumeroCursos())
				.filter(i -> !List2.intersection(this.remaining(), DatosCursos.getTematicasCurso(i)).isEmpty())
				.filter(i -> {Set<Integer> centros2 = Set2.copy(this.centers());
				centros2.add(DatosCursos.getCentroCurso(i)); return centros2.size()<=DatosCursos.maxCentros;
})
				
				.mapToDouble(i -> DatosCursos.getPrecioCurso(i))
				.min()
				.orElse(100);
		
		}
	
	return res;
	}

}
