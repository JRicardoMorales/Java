package ejercicios.ejercicio2;

import java.util.List;
import java.util.function.Predicate;
import _utils.GraphsPI5;
import _utils.TestsPI5;

public class Ejercicio2Test {
public static void main(String[] args) {
	List.of(1,2,3).forEach(num_test -> {
		TestsPI5.iniTest("Ejercicio2DatosEntrada", num_test, DatosCursos::iniDatos);

		CursosVertex v_inicial = CursosVertex.initial();
		Predicate<CursosVertex> es_terminal = CursosVertex.goal();

		var gp = TestsPI5.testGreedy(GraphsPI5.greedyCursosGraph(v_inicial, es_terminal));
		TestsPI5.toConsole("Voraz", gp, SolucionCursos::of);

		var path = TestsPI5.testAStar(GraphsPI5.cursosGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("A*", path, SolucionCursos::of);

		path = TestsPI5.testPDR(GraphsPI5.cursosGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("PDR", path, SolucionCursos::of);

		path = TestsPI5.testBT(GraphsPI5.cursosGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("BT", path, SolucionCursos::of);

		TestsPI5.line("*");
		});
	}

}
