package ejercicio3;

import java.util.List;
import java.util.Locale;
import _soluciones.SolucionesInvestigador;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class InvestigadoresTest {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));

		AlgoritmoAG.ELITISM_RATE = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;

		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;

		for (int i = 0; i < 3; i++) {

			InvestigadoresInRangeAG p3 = new InvestigadoresInRangeAG(
					"ficheros/Ejercicio3DatosEntrada" + (i + 1) + ".txt");

			AlgoritmoAG<List<Integer>, SolucionesInvestigador> ap3 = AlgoritmoAG.of(p3);
			ap3.ejecuta();

			System.out.println("================================");
			System.out.println(ap3.bestSolution());
			System.out.println("================================\n");
		}
	}

}
