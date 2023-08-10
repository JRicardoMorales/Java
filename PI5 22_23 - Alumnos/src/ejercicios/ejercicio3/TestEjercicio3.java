package ejercicios.ejercicio3;

import java.util.List;
import java.util.function.Predicate;
import _utils.GraphsPI5;
import _utils.TestsPI5;

public class TestEjercicio3 {
	
public static void main(String[] args) {
	List.of(1,2,3).forEach(num_test -> {
		TestsPI5.iniTest("Ejercicio3DatosEntrada", num_test, DatosInvestigadores::iniDatos);

		InvestigadorVertex v_inicial = InvestigadorVertex.initial();
		Predicate<InvestigadorVertex> es_terminal = InvestigadorVertex.goal();

		var gp = TestsPI5.testGreedy(GraphsPI5.greedyInvestigadorGraph(v_inicial, es_terminal));
		TestsPI5.toConsole("Voraz", gp, SolucionesInvestigador::of);

		var path = TestsPI5.testAStar(GraphsPI5.investigadorGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("A*", path, SolucionesInvestigador::of);

		path = TestsPI5.testPDR(GraphsPI5.investigadorGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("PDR", path, SolucionesInvestigador::of);

		path = TestsPI5.testBT(GraphsPI5.investigadorGraph(v_inicial, es_terminal), gp);
		TestsPI5.toConsole("BT", path, SolucionesInvestigador::of);

		TestsPI5.line("*");
		});
	}

}
