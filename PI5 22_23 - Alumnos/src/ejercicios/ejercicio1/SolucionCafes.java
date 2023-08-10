package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import ejercicios.ejercicio1.DatosCafes.Variedad; 

public class SolucionCafes {

public static SolucionCafes of_Range(List<Integer> value) {
	return new SolucionCafes(value);
	
}

public static SolucionCafes of(GraphPath<CafeVertex,CafeEdge> path) {
	List<Integer> ls = path.getEdgeList().stream()
			.map(e -> e.action())
			.toList();
	SolucionCafes res = of_Range(ls);
	res.path = ls;
	return res;
	
}

	private Double beneficio;
	private List<Integer> solucion;
	private List<Variedad> soluciones;
	private List<Integer> path;

	private SolucionCafes() {
		beneficio = 0.;
		solucion = new ArrayList<>();
		soluciones = new ArrayList<>();
	
}

	
private SolucionCafes(List<Integer> ls) {
	beneficio = 0.;
	solucion = new ArrayList<>();
	soluciones = new ArrayList<>();
	for (int i = 0; i < ls.size(); i++) {
		if (ls.get(i) > 0) {
			Integer kilos = ls.get(i);
			Integer bv = DatosCafes.getBeneficioVariedad(i) * kilos; soluciones.add(DatosCafes.getVariedades().get(i));
			solucion.add(ls.get(i));
			beneficio += bv;
			}
	}
}


public static SolucionCafes empty() {
	return new SolucionCafes();
	
}


public String toString() {
	String s = soluciones.stream()
			.map(v -> "P" + (v.id() + 1) + ": " + solucion.get(soluciones.indexOf(v)))
			.collect(Collectors.joining(" Kilos\n", "Kilos producidos de cada variedad:\n", " Kilos\n"));
		String res = String.format("%sBeneficio obtenido: %.1f", s, beneficio);
		return path==null? res:String.format("%s\nPath de la solucion: %s", res, path);
	}
}

