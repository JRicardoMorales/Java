package ejercicio2;

import java.util.List;
import java.util.Locale;
import _soluciones.SolucionesCurso;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class CursoTest {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));

		AlgoritmoAG.ELITISM_RATE = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;

		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;

		for (int i = 0; i < 3; i++) {

			BinarioCursoAG p2 = new BinarioCursoAG("ficheros/Ejercicio2DatosEntrada" + (i + 1) + ".txt");

			AlgoritmoAG<List<Integer>, SolucionesCurso> ap2 = AlgoritmoAG.of(p2);
			ap2.ejecuta();

			System.out.println("================================");
			System.out.println(ap2.bestSolution());
			System.out.println("================================\n");
		}
	}

}