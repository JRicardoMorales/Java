package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.jgrapht.Graphs;
 import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record ClientesVertex(Integer index, Set<Integer> pendientes, List<Integer> visitados, Integer kms)
	implements VirtualVertex<ClientesVertex, ClientesEdge, Integer> {

public static ClientesVertex of(Integer i, Set<Integer> pend, List<Integer> visit, Integer kms) {
	return new ClientesVertex(i, pend, visit, kms);
	
}

public static ClientesVertex initial() {
	Set<Integer> remaining = DatosClientes.grafo.vertexSet().stream()
			.map(c -> c.id())
			.collect(Collectors.toSet());
	List<Integer> visit = new ArrayList<>();
	visit.add(0);
	return of(0, remaining, visit, 0);
	
}

// Es un vértice final si no quedan vértices por visitar
public static Predicate<ClientesVertex> goal(){
	return v -> v.pendientes().isEmpty();
	
}


// Es una solución valida si el último vértice de la lista de visitados es 0
public static Predicate<ClientesVertex> goalHasSolution(){
	return v -> v.visitados().get(v.visitados().size()-1) == 0;
	
}


@Override
public List<Integer> actions() {
	List<Integer> acc = new ArrayList<>();
	Cliente actual = DatosClientes.getCliente(this.visitados().get(this.visitados().size()-1));
	if(this.index()<=DatosClientes.getNumeroVertices()) { //Obtenemos los vecinos del vértice del grafo de reparto correspondiente
		List<Integer> conexos = Graphs.neighborListOf(DatosClientes.grafo, actual)
				.stream()
				.map(c -> c.id())
				.toList(); //	Nos quedamos con los que no hayamos visitado todavía
		acc.addAll(List2.intersection(this.pendientes(), conexos));
	}
	
return acc;

}


@Override
public ClientesVertex neighbor(Integer a) { // Calculamos la distancia recorrida sumando el peso de la arista a los kilometros actuales
	Integer distancia = this.kms() + DatosClientes.getPeso(this.visitados().get(this.visitados().size()-1), a).intValue(); // Eliminamos el vértice escogido de los pendietnes y lo añadimos a la lista de visitados
	Set<Integer> pend = new HashSet<>(this.pendientes());
	pend.remove(a); List<Integer> vis = new ArrayList<>(this.visitados()); vis.add(a);
	return of(this.index()+1, pend, vis, distancia);
	
}


@Override
public ClientesEdge edge(Integer a) {
	return ClientesEdge.of(this, this.neighbor(a), a);
	
}


public ClientesEdge greedyEdge() {
	ClientesEdge res = null;
	List<Integer> posibles = this.actions(); // Por defecto escogemos el primer vértice de las acciones disponibles
	res = edge(posibles.get(0));
	Double benef = 0.;
	
	for(int i=0; i<posibles.size(); i++) { //	Por cada acción posible:
		if(DatosClientes.getBeneficio(posibles.get(i)) > benef) { //	Si el beneficio obtenido por esa eleeción es mayor que el que tenemos calculado, nos quedamos con esa arista y ese beneficio
			benef = DatosClientes.getBeneficio(posibles.get(i));
			res = edge(posibles.get(i));
		}
	}
	
	return res;

}

@Override
public String toString() {
	return String.format("%d; %s; %s; %d", this.index(), this.pendientes(), this.visitados(), this.kms());

	}
}

