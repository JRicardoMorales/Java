package ejercicios.ejercicio4;

import java.util.List;
import java.util.function.Predicate;
import _utils.GraphsPI5;
import _utils.TestsPI5;


public class Ejercicio4Test {

public static void main(String[] args) {
	List.of(1,2).forEach(num_test -> {
		TestsPI5.iniTest("Ejercicio4DatosEntrada", num_test, DatosClientes::iniDatos);

		ClientesVertex v_inicial = ClientesVertex.initial();
		Predicate<ClientesVertex> es_terminal = ClientesVertex.goal();

		var gp = TestsPI5.testGreedy(GraphsPI5.greedyClientesGraph(v_inicial, es_terminal));
		TestsPI5.toConsole("Voraz", gp, SolucionClientes::of);


		var path = TestsPI5.testAStar(GraphsPI5.clientesGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("A*", path, SolucionClientes::of);

		path = TestsPI5.testPDR(GraphsPI5.clientesGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("PDR", path, SolucionClientes::of);

		path = TestsPI5.testBT(GraphsPI5.clientesGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("BT", path, SolucionClientes::of);

		TestsPI5.line("*");
		});
	
	}

}
