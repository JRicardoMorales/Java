package ejercicios.ejercicio2.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ejercicios.ejercicio2.DatosCursos;
import ejercicios.ejercicio2.SolucionCursos;

public class CursosPDR {
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

	public static CursosProblem start;
	public static Integer minValue;
	public static Map<CursosProblem,Spm> memory;
	
public static SolucionCursos pd(Set<Integer> initialRem) {
	CursosPDR.minValue = Integer.MAX_VALUE;
	CursosPDR.start = CursosProblem.of(0, initialRem, new HashSet<>());
	CursosPDR.memory = new HashMap<>();
	pd(start, 0, memory);
	return CursosPDR.solucion();
	
}


//Igual que CafePDR
public static Spm pd(CursosProblem vert, Integer acum, Map<CursosProblem,Spm> mem) {
	Spm r;
	Boolean goal = vert.index() == DatosCursos.getNumeroCursos();
	Boolean hasSolution = vert.remaining().isEmpty();
	Boolean esValido = vert.centers().size()<=DatosCursos.maxCentros;

	if(mem.containsKey(vert)) {
		r = mem.get(vert);
	} else if(goal && hasSolution && esValido) {
		r = Spm.of(null, 0.);
		mem.put(vert, r);
		if(acum < CursosPDR.minValue) { CursosPDR.minValue = acum;
		}
	} else {
		List<Spm> soluciones = new ArrayList<>();
		
		for(Integer a: vert.actions()) {
			Double cota = acotar(acum, vert, a);
			if(cota > CursosPDR.minValue) {
				continue;
				
			}
			
			Spm s = pd(vert.neighbor(a), acum + a*DatosCursos.getPrecioCurso(vert.index()).intValue(), mem);
		if(s != null) {
		Spm sp = Spm.of(a, s.weight()+(a*DatosCursos.getPrecioCurso(vert.index())));
		soluciones.add(sp);
		
	}
}
		
		r = soluciones.stream()
		.min(Comparator.naturalOrder())
		.orElse(null); if(r != null) {
		mem.put(vert, r);
	
		}
	}
	
	return r;
	
}


public static Double acotar(Integer acum, CursosProblem vertice, Integer a) {
	return acum + a*DatosCursos.getPrecioCurso(vertice.index()) + vertice.neighbor(a).heuristic();
	
}

public static SolucionCursos solucion() {
	List<Integer> acc = new ArrayList<>();
	CursosProblem v = CursosPDR.start;
	Spm s = CursosPDR.memory.get(v);
	while(s.a() != null) {
		acc.add(s.a());
		v = v.neighbor(s.a());
		s = CursosPDR.memory.get(v);
		
		}
	
	return SolucionCursos.of_Range(acc);

	}



}



