package ejercicios.ejercicio3.manual;

import java.util.ArrayList;
import java.util.List;
import ejercicios.ejercicio3.DatosInvestigadores;
import ejercicios.ejercicio3.SolucionesInvestigador;

public class InvestigadorState {
	InvestigadorProblem actual;
	Integer acumulado;
	List<Integer> acciones;
	List<InvestigadorProblem> vertices;

private InvestigadorState(InvestigadorProblem p, Integer a, List<Integer> la, List<InvestigadorProblem> lv) {
	actual = p;
	acumulado = a;
	acciones = la;
	vertices = lv;
	
}

public static InvestigadorState initial() {
	InvestigadorProblem v = InvestigadorProblem.initial();
	List<InvestigadorProblem> lp = new ArrayList<>();
	lp.add(v);
	return of(v, 0, new ArrayList<>(), lp);
	
}


public static InvestigadorState of(InvestigadorProblem p, Integer a, List<Integer> la, List<InvestigadorProblem> lv) {
	return new InvestigadorState(p, a, la, lv);
	
}

public InvestigadorProblem getActual() {
	return actual;
}


public Integer getAcum() {
	return acumulado;
	
}


public List<Integer> getAcciones() { 
	return acciones;
	
}


public List<InvestigadorProblem> getVertices() {
	return vertices;
	
}


public void forward(Integer a) {
	InvestigadorProblem act = this.getActual(); // Obtenemos el vértice actual y el siguiente
	InvestigadorProblem sig = act.neighbor(a);
	this.acciones.add(a); // Añadimos la acción y el vértice siguiente a sus respectivas listas
	this.vertices.add(sig); 
	if(!act.distribution().get(act.j()).stream().allMatch(h -> h==0) && sig.distribution().get(act.j()) // Sumamos la calidad igual que en InvestigadorEdge
			.stream().allMatch(h -> h==0)) {
		this.acumulado = this.getAcum() + DatosInvestigadores.getCalidad(act.j());
		
	}
	
	this.actual = sig; // Cambiamos el vértice actual al siguiente
}


public void back() {
	InvestigadorProblem act = this.getActual(); // Obtenemos el vértice actual
	this.vertices.remove(this.vertices.size()-1); // Eliminamos el vértice actual y su acción de sus listas y obtenemos el vértice anterior
	this.acciones.remove(this.acciones.size()-1);
	InvestigadorProblem ante = this.getVertices().get(this.getVertices().size()-1);
	if(act.distribution().get(act.j()).stream().allMatch(h -> h==0) && // Restamos la calidad igual que en InvestigadorEdge
			!ante.distribution().get(act.j()).stream().allMatch(h -> h==0)) {
		this.acumulado = this.getAcum() - DatosInvestigadores.getCalidad(act.j());
	}

	this.actual = ante; // Cambiamos el vértice actual al anterior
}

public List<Integer> actions() {
	return actual.actions();
	
}

public Double cota(Integer a) {
	InvestigadorProblem act = this.getActual();
	InvestigadorProblem sig = act.neighbor(a);
	Double w = 0.;
	if(!act.distribution().get(act.j()).stream().allMatch(h -> h==0) && sig.distribution().get(act.j())
			.stream().allMatch(h -> h==0)) {
		w += DatosInvestigadores.getCalidad(act.j());
	}
	
	return this.getAcum() + w + actual.neighbor(a).heuristic();

}

// Igual que InvsVertex
public Boolean goal() {
	return this.getActual().z() == DatosInvestigadores.getNumeroInvestigadores() * DatosInvestigadores.getNumeroTrabajos();
	
}


// Un vértice es válido si es un vértice final
public Boolean hasSolution() {
	return this.goal();
	
}

public SolucionesInvestigador getSolucion() {
	return SolucionesInvestigador.of_Range(acciones);
	
	}	

}
