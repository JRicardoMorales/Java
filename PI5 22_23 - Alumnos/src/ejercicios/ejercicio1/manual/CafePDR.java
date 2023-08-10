package ejercicios.ejercicio1.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ejercicios.ejercicio1.DatosCafes;
import ejercicios.ejercicio1.SolucionCafes;

public class CafePDR {
	public static record Spm(Integer a, Double weight)
	implements Comparable<Spm> {

public static Spm of(Integer a, Double w) {
	return new Spm(a, w);
	
}

@Override
public int compareTo(Spm o) {
	return this.weight().compareTo(o.weight());
	
	}
}

	public static CafeProblem start;
	public static Integer maxValue;
	public static Map<CafeProblem,Spm> memory;
	public static SolucionCafes pd(List<Double> initialRem) {
		CafePDR.maxValue = Integer.MIN_VALUE;
		CafePDR.start = CafeProblem.of(0, initialRem);
		CafePDR.memory = new HashMap<>();
		pd(start, 0, memory);
		return CafePDR.solucion();
		
}
	
	
public static Spm pd(CafeProblem vert, Integer acum, Map<CafeProblem,Spm> mem) {
	Spm r;
	Boolean goal = vert.index() == DatosCafes.getNumeroVariedades();
	Boolean hasSolution = vert.remaining().stream()
			.allMatch(i -> i>=0);

	if(mem.containsKey(vert)) { //	Si ya hemos calculado esta solución antes, la obtenemos de la memoria
		r = mem.get(vert);
	} else if(goal && hasSolution) { //	Si es un vértice final y válido, guardamos la solución en memoria y modificamos el valor base
		r = Spm.of(null, 0.);
		mem.put(vert, r);
		if(acum > CafePDR.maxValue) {
			CafePDR.maxValue = acum;
			
		}
	} else { //En otro caso, calculamos la solución parcial mediante llamadas recursivas
		List<Spm> soluciones = new ArrayList<>();

		for(Integer a: vert.actions()) {
			Double cota = acotar(acum, vert, a);
			if(cota <= CafePDR.maxValue) {
				continue;
				
}
			
			Spm s = pd(vert.neighbor(a), acum + a*DatosCafes.getBeneficioVariedad(vert.index()), mem);
			if(s != null) {
				Spm sp = Spm.of(a, s.weight()+(a*DatosCafes.getBeneficioVariedad(vert.index())));
				soluciones.add(sp);
		}
}
		
		r = soluciones.stream()
				.max(Comparator.naturalOrder())
				.orElse(null);
		if(r != null) {
			mem.put(vert, r);
			
			}
		
	}
	
return r;

}


public static Double acotar(Integer acum, CafeProblem vertice, Integer a) {
	return acum + a*DatosCafes.getBeneficioVariedad(vertice.index()) + vertice.neighbor(a).heuristic();
	
}

public static SolucionCafes solucion() {
	List<Integer> acc = new ArrayList<>();
	CafeProblem v = CafePDR.start;
	Spm s = CafePDR.memory.get(v);
	while(s.a() != null) {
		acc.add(s.a());
		v = v.neighbor(s.a());
		s = CafePDR.memory.get(v);
		
	}
	
return SolucionCafes.of_Range(acc);

}






}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		