package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.GraphPath;
import ejercicios.ejercicio3.DatosInvestigadores.Investigador;


public class SolucionesInvestigador implements Comparable<SolucionesInvestigador> {

public static SolucionesInvestigador of_Range(List<Integer> value) {
	return new SolucionesInvestigador(value);
	
}


public static SolucionesInvestigador of(GraphPath<InvestigadorVertex,InvestigadorEdge> path) {
	List<Integer> ls = path.getEdgeList().stream()
			.map(e -> e.action())
			.toList();
	SolucionesInvestigador res = of_Range(ls);
	res.path = ls;
	return res;
	
}

	
	private List<List<Integer>> horas;
	private Integer calidad;
	private List<Investigador> investigadores;
	private List<Integer> path;

private SolucionesInvestigador() {
	calidad = 0;
	investigadores = new ArrayList<>();
	horas = new ArrayList<>();
	
}

private SolucionesInvestigador(List<Integer> ls) {
	Integer n = DatosInvestigadores.getNumeroInvestigadores();
	Integer m = DatosInvestigadores.getNumeroTrabajos();
	Integer e = DatosInvestigadores.getNumeroEspecialidades();
	calidad = 0;
	horas = new ArrayList<>();
	investigadores = new ArrayList<>();
	investigadores.addAll(DatosInvestigadores.investigadores);
	
	// Añadimos una lista por cada investigador
	for(int i=0; i<n; i++) {
		horas.add(new ArrayList<>());
		
	}

	for(int z=0; z<ls.size(); z++) {
		Integer i = z/m; horas.get(i).add(ls.get(z));
		
	}

	for(int j=0; j<m; j++) {
		Boolean realiza = true;
		for(int k=0; k<e; k++) {
			Integer suma=0;
			
			for(int i=0; i<n; i++) {
				suma += horas.get(i).get(j) * DatosInvestigadores.trabajadorEspecialidad(i, k);
				
			}
			
			if(suma<DatosInvestigadores.diasNecesarios(j, k)) {
				realiza=false;
				k=e;
				
			}
		}
		
		if(realiza) {
			calidad += DatosInvestigadores.getCalidad(j);
		}
	}

}

public static SolucionesInvestigador empty() {
	return new SolucionesInvestigador();
	
}

public String toString() {
	String s = investigadores.stream()
		.map(i -> "INV" + (i.id() + 1) + ": " + horas.get(i.id()))
		.collect(Collectors.joining("\n", "Reparto de horas:\n", "\n"));
	String res = String.format("%sSuma de las calidades de los trabajos realizados: %d", s, calidad);
	return path==null? res: String.format("%s\nPath de la solucion: %s", res, path);
	
}


public Integer getCalidad() {
	return this.calidad;
	


}
	@Override
public int compareTo(SolucionesInvestigador o) {
	return this.getCalidad().compareTo(o.getCalidad());
	
	}
}

