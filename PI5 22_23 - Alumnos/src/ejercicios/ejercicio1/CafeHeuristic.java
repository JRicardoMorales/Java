package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

 public class CafeHeuristic {
	 public static Double heuristic(CafeVertex v1, Predicate<CafeVertex> goal, CafeVertex v2) {
		 Double res = 0.;  // Establecemos 0 como valor predeterminado 
		 if(!v1.remaining().stream().allMatch(i -> i==0)) { // Si el remaining no es cero calculamos la cantidad maxima posible de cada variedad
			 List<Integer> ls = IntStream.range(v1.index(), DatosCafes.getNumeroVariedades())
					 .map(i -> CafeVertex.maxKilosVar(v1.remaining(), i).stream()
					 .min(Comparator.naturalOrder()).get().intValue())
					 .boxed()
					 .toList();
			 
			 List<Integer> benef = new ArrayList<>(); //Obtenemos el beneficio de cada variedad
			 for(int i=0; i<ls.size(); i++) {
				 Integer var = i + v1.index();
				 benef.add(ls.get(i)*DatosCafes.getBeneficioVariedad(var));
				 
}
			 res += benef.stream() //suma de los beneficios
					 .reduce(Integer::sum) 
					 .orElse(0); //	Si la suma es nula que devuelva un 0
		 }
		 
		 return res;
	 }
}
