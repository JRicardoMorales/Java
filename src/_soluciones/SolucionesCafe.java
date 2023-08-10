package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import _datos.DatosCafes;
import _datos.DatosCafes.Variedad;

public class SolucionesCafe {

	public static SolucionesCafe of_Range(List<Integer> value) {
		return new SolucionesCafe(value);

	}

	private List<Integer> solucion2;
	private List<Variedad> solucion1;
	private Double beneficios;

	private SolucionesCafe() {
		beneficios = 0.;
		solucion1 = new ArrayList<>();
		solucion2 = new ArrayList<>();
	}

	private SolucionesCafe(List<Integer> ls) {
		beneficios = 0.;
		solucion1 = new ArrayList<>();
		solucion2 = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {

			if (ls.get(i) > 0) {
				Integer kilos = ls.get(i);
				Integer bf = DatosCafes.getBeneficiosVariedad(i) * kilos;
				solucion1.add(DatosCafes.getVariedades().get(i));
				solucion2.add(ls.get(i));
				beneficios += bf;
			}
		}
	}

	public static SolucionesCafe empty() {
		return new SolucionesCafe();

	}

	public String toString() {
		String s1 = solucion1.stream().map(a -> "P" + (a.id() + 1) + ": " + solucion2.get(solucion1.indexOf(a)))
				.collect(Collectors.joining(" Kilos\n", "Kilos de cada variedad:\n", " Kilos\n"));
		return String.format("%sBeneficio obtenido: %.1f", s1, beneficios);
	}

}