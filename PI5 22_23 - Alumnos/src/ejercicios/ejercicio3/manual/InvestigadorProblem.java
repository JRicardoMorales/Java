package ejercicios.ejercicio3.manual;

import java.util.ArrayList; 
import java.util.List;
import java.util.stream.IntStream;
import ejercicios.ejercicio3.DatosInvestigadores;

public record InvestigadorProblem(Integer z, Integer i, Integer j, List<Integer> days, List<List<Integer>> distribution) {

public static InvestigadorProblem initial() {
	List<Integer> dias = DatosInvestigadores.investigadores.stream()
			.map(in -> in.capacidad())
			.toList();
	List<List<Integer>> dist = DatosInvestigadores.trabajos.stream()
			.map(t -> t.dias())
			.toList();
	return of(0, dias, dist);
	
}

public static InvestigadorProblem of(Integer z, List<Integer> days, List<List<Integer>> distribution) {
	Integer ii = z/DatosInvestigadores.getNumeroTrabajos();
	Integer jj = z%DatosInvestigadores.getNumeroTrabajos();
	return new InvestigadorProblem(z, ii, jj, days, distribution);
	
}

// Como en InvestigadorVertex
public List<Integer> actions() {
	List<Integer> acc = new ArrayList<>();
	if(this.z() < (DatosInvestigadores.getNumeroInvestigadores() * DatosInvestigadores.getNumeroTrabajos())) {
		acc.add(0); Integer neces = this.distribution().get(j).get(DatosInvestigadores.getEspecialidadInvestigador(i));
		Integer dias = this.days().get(i);
		if(neces>0 && dias>0) {
			Integer posible = Math.min(neces, dias) +1;
			acc.addAll(IntStream.range(1, posible)
					.boxed()
					.toList());
		}
	}
	
return acc;

}


public InvestigadorProblem neighbor(Integer a) {
	List<Integer> dias = new ArrayList<>(this.days());
	List<List<Integer>> dist= new ArrayList<>();

	for(int i=0; i<this.distribution().size(); i++) {
		dist.add(new ArrayList<>(this.distribution().get(i)));
	}
	
	Integer e = DatosInvestigadores.getEspecialidadInvestigador(i);
	dias.set(i, dias.get(i)-a);
	dist.get(j).set(e, dist.get(j).get(e)-a);
	return of(this.z()+1, dias, dist);
	
}

// Como InvestigadorHeuristic
public Double heuristic() {
	Double res = 0.;
	if(!this.days().stream().allMatch(h -> h==0)) {
	res += IntStream.range(this.z(), DatosInvestigadores.getNumeroInvestigadores() * DatosInvestigadores.getNumeroTrabajos())
	.filter(z -> this.days().get(this.i())>0 && this.distribution().get(this.j())
	.get(DatosInvestigadores.getEspecialidadInvestigador(this.i()))>0 && this.days().get(this.i()) >=
	this.distribution().get(this.j()).get(DatosInvestigadores.getEspecialidadInvestigador(this.i())))
	.map(z -> DatosInvestigadores.getCalidad(this.j()))
	.sum();
		}
	return res;
	}

}
