package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class DatosClientes {

public static Graph<Cliente, Conexion> grafo;
	
public static void iniDatos(String fichero) {
	grafo = GraphsReader.newGraph(fichero, Cliente::ofFormat, Conexion::ofFormat, Graphs2::simpleWeightedGraph);
	toConsole();
	
}

public static Integer getNumeroVertices() {
	return grafo.vertexSet().size();
	
}

public static Cliente getCliente(Integer i) {
	Cliente c = null;
	List<Cliente> vertices = new ArrayList<>(grafo.vertexSet());
	for (int k = 0; k < vertices.size(); k++) {
		if (vertices.get(k).id() == i) {
			c = vertices.get(k);
		}
	}
	
	return c;
	
}


public static Double getBeneficio(Integer i) {
	Cliente c = getCliente(i);
	return c.beneficio();
	
}

public static Boolean existeArista(Integer i, Integer j) {
	Cliente c1 = getCliente(i);
	Cliente c2 = getCliente(j);
	return grafo.containsEdge(c1, c2);
	
}

public static Double getPeso(Integer i, Integer j) {
	Cliente c1 = getCliente(i);
	Cliente c2 = getCliente(j);
	return grafo.getEdge(c1, c2).distancia();
	
}


private static void toConsole() {
	System.out.println("Número de vértices: " + grafo.vertexSet().size() + "\n\tVértices: " + grafo.vertexSet()
	+ "\nNúmero de aristas: " + grafo.edgeSet().size() + "\n\tAristas: " + grafo.edgeSet());
	
}

public static void main(String[] args) {
	iniDatos("ficheros/Ejercicio4DatosEntrada1.txt"); System.out.println(getPeso(2, 4));
	}

}
