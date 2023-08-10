package _datos;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import utils.Cliente;
import utils.Conexion;

public class DatosClientes {
	@SuppressWarnings("exports")

	public static Graph<Cliente, Conexion> graph;

	public static void iniDatos(String ss4) {
		graph = GraphsReader.newGraph(ss4, Cliente::ofFormat, Conexion::ofFormat, Graphs2::simpleWeightedGraph);
		toConsole();

	}

	public static Integer getNumeroVertices() {
		return graph.vertexSet().size();

	}

	@SuppressWarnings("exports")

	public static Cliente getCliente(Integer i) {
		Cliente c4 = null;
		List<Cliente> vertices = new ArrayList<>(graph.vertexSet());
		for (int j = 0; j < vertices.size(); j++) {

			if (vertices.get(j).id() == i) {
				c4 = vertices.get(j);
			}
		}

		return c4;

	}

	public static Double getBeneficio(Integer i) {
		Cliente c4 = getCliente(i);
		return c4.benef();

	}

	public static Double getPeso(Integer i, Integer j) {
		Cliente c41 = getCliente(i);
		Cliente c42 = getCliente(j);
		return graph.getEdge(c41, c42).dis();

	}

	public static Boolean existeArista(Integer i, Integer j) {
		Cliente c41 = getCliente(i);
		Cliente c42 = getCliente(j);
		return graph.containsEdge(c41, c42);

	}

	private static void toConsole() {
		System.out.println("Recuento de vertices: " + graph.vertexSet().size() + "\n\tVértices: " + graph.vertexSet()
				+ "\nRecuento de aristas: " + graph.edgeSet().size() + "\n\tAristas: " + graph.edgeSet());

	}

	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio4DatosEntrada1.txt");
		System.out.println(getPeso(2, 4));
	}
}
