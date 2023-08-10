package ejercicios.ejercicio3;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class InvestigadorHeuristic {
	
public static Double heuristic(InvestigadorVertex v1, Predicate<InvestigadorVertex> goal, InvestigadorVertex v2) { 
	Double res = 0.; // Establecemos 0 como valor predeterminado
	if(!v1.days().stream().allMatch(h -> h==0)) { // Si algun investigador tiene hora sin escoger
		res += IntStream.range(v1.z(), DatosInvestigadores.getNumeroInvestigadores() * DatosInvestigadores.getNumeroTrabajos())
				.filter(z -> v1.days().get(v1.i())>0 &&  v1.distribution().get(v1.j()) //	Filtramos de forma que el investigador i tenga horas disponibles //	que el trabajo j tenga horas necesarias en la especialidad e
				.get(DatosInvestigadores.getEspecialidadInvestigador(v1.i()))>0 && v1.days().get(v1.i()) >= //	y que las horas disponibles sean mayores que las horas necesarias
				v1.distribution().get(v1.j()).get(DatosInvestigadores.getEspecialidadInvestigador(v1.i())))
				.map(z -> DatosInvestigadores.getCalidad(v1.j())) //Obtenemos el beneficio de cada trabajo y los sumamos
				.sum();
		}
	
	return res;
	
	}

}
