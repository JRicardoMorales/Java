package ejercicios.ejercicio2;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class CursosHeuristic {

public static Double heuristic(CursosVertex v1, Predicate<CursosVertex> goal, CursosVertex v2) { 
	Double res = 0.; //Establecemos 0 como valor predeterminado; 
	if(!v1.remaining().isEmpty()) { // Si el remaining no esta vacio
		res += IntStream.range(v1.index(), DatosCursos.getNumeroCursos()) 
				.filter(i -> !List2.intersection(v1.remaining(), DatosCursos.getTematicasCurso(i)).isEmpty()) //Filtramos los cursos que nos eliminen alguna temática del remaining
				.filter(i -> { //Filtramos los que no sobrepasen la restricción de maxCentros
					Set<Integer> centros2 = Set2.copy(v1.centers());
					centros2.add(DatosCursos.getCentroCurso(i));
					return centros2.size()<=DatosCursos.maxCentros;
					
})
				.mapToDouble(i -> DatosCursos.getPrecioCurso(i)) //	Obtenemos el precio de cada uno y nos quedamos con el mínimo
				.min() 
				.orElse(100); //Si no se encuentra el mínimo, sumamos un valor elevado para descartarlo
		
		}
	
	return res;
	}

}
