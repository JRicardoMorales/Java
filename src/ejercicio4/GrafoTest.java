package ejercicio4;

import java.util.List;
import java.util.Locale;
import _soluciones.SolucionesCliente;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class GrafoTest {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));

		AlgoritmoAG.ELITISM_RATE = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;

		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;

		for (int i = 0; i < 2; i++) {

			GrafoAG p4 = new GrafoAG("ficheros/Ejercicio4DatosEntrada" + (i + 1) + ".txt");

			AlgoritmoAG<List<Integer>, SolucionesCliente> ap4 = AlgoritmoAG.of(p4);
			ap4.ejecuta();

			System.out.println("================================");
			System.out.println(ap4.bestSolution());
			System.out.println("================================\n");
		}
	}

}
