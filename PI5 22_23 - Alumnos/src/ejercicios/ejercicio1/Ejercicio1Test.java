package ejercicios.ejercicio1;

import java.util.List;
import java.util.function.Predicate;
import _utils.GraphsPI5;
import _utils.TestsPI5;


public class Ejercicio1Test {

public static void main(String[] args) {
	List.of(1,2,3).forEach(num_test -> {
		TestsPI5.iniTest("Ejercicio1DatosEntrada", num_test, DatosCafes::iniDatos);

		CafeVertex v_inicial = CafeVertex.initial();
		Predicate<CafeVertex> es_terminal = CafeVertex.goal();

		var gp = TestsPI5.testGreedy(GraphsPI5.greedyCafesGraph(v_inicial, es_terminal)); TestsPI5.toConsole("Voraz", gp, SolucionCafes::of);

		var path = TestsPI5.testAStar(GraphsPI5.cafesGraph(v_inicial, es_terminal), gp); TestsPI5.toConsole("A*", path, SolucionCafes::of);

		path = TestsPI5.testPDR(GraphsPI5.cafesGraph(v_inicial, es_terminal), gp); TestsPI5.toConsole("PDR", path, SolucionCafes::of);

		path = TestsPI5.testBT(GraphsPI5.cafesGraph(v_inicial, es_terminal), gp); TestsPI5.toConsole("BT", path, SolucionCafes::of);

		TestsPI5.line("*");
		});

	}

}

