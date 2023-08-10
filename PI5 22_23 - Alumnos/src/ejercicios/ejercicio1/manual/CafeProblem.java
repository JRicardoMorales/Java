package ejercicios.ejercicio1.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import ejercicios.ejercicio1.DatosCafes;

public record CafeProblem(Integer index, List<Double> remaining) {

public static CafeProblem intial() {
	return of(0, DatosCafes.tipos.stream()
			.map(i -> i.doubleValue())
			.toList());
	
}

public static CafeProblem of(Integer i, List<Double> rem) {
	return new CafeProblem(i, rem);
	
}

// Como en CafeVertex
public List<Integer> actions() {
	List<Integer> acc = new ArrayList<>();
	if(this.index()<DatosCafes.getNumeroVariedades()) {
		List<Double> listaMax = maxKilosVar(this.remaining(), this.index());
		Integer maxKg = listaMax.stream()
				.min(Comparator.naturalOrder())
				.get()
				.intValue();
		if(maxKg<0) {
			
			maxKg=0;
			
}

		for(int i=0; i<=maxKg; i++) {
			acc.add(i);
			
			}
	}
	
	return acc;
	
}


private static List<Double> maxKilosVar(List<Double> rem, Integer indice) {
	List<Double> res = new ArrayList<>();

	for (int j = 0; j < rem.size(); j++) {
		res.add(rem.get(j) / DatosCafes.getKilosTipoVariedad(j, indice));
		
	}
	
	res.removeIf(n -> n.equals(Double.POSITIVE_INFINITY));
	return res;
	
}

public CafeProblem neighbor(Integer a) {
	List<Double> proporcion = DatosCafes.getVariedades().get(index).composicion();
			List<Double> rest = new ArrayList<>();
			
			for(int i=0; i<remaining.size(); i++) {
				Double restante = remaining.get(i)-a*proporcion.get(i);
				rest.add(restante);
				
			}
			
			return of(index+1, rest);

}

// Como CafeHeuristic
public Double heuristic() {
	Double res = 0.;
	if(!this.remaining().stream().allMatch(i -> i==0)) {
		List<Integer> ls = IntStream.range(this.index(), DatosCafes.getNumeroVariedades())
				.map(i -> maxKilosVar(this.remaining(), i)
				.stream().min(Comparator.naturalOrder()).get().intValue())
				.boxed()
				.toList();

		List<Integer> benef = new ArrayList<>();
		for(int i=0; i<ls.size(); i++) {
			Integer var = i + this.index();
			benef.add(ls.get(i)*DatosCafes.getBeneficioVariedad(var));
			
}

		res += benef.stream()
				.reduce(Integer::sum)
				.orElse(0);
		
		}
	
	return res;
	
	}

}














