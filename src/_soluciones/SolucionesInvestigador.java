package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import _datos.DatosInvestigadores;
import _datos.DatosInvestigadores.Investigador;

public class SolucionesInvestigador {
	public static SolucionesInvestigador of_Range(List<Integer> value) {
		return new SolucionesInvestigador(value);
	}

	private Integer calidad;
	private List<List<Integer>> horas;
	private List<Investigador> investigadores;

	private SolucionesInvestigador() {
		calidad = 0;
		horas = new ArrayList<>();
		investigadores = new ArrayList<>();

	}

	private SolucionesInvestigador(List<Integer> list1) {
		Integer numeroT = DatosInvestigadores.getNumeroTrabajos();
		Integer numeroI = DatosInvestigadores.getNumeroInvestigadores();
		Integer numeroE = DatosInvestigadores.getNumeroEspecialidades();
		calidad = 0;
		investigadores = new ArrayList<>();
		horas = new ArrayList<>();
		investigadores.addAll(DatosInvestigadores.investigadores);

		for (int i = 0; i < numeroI; i++) {
			horas.add(new ArrayList<>());

		}

		for (int j = 0; j < numeroT; j++) {

			Integer j2 = j * numeroI;
			List<Integer> t = list1.subList(j2, j2 + numeroI);

			for (int i = 0; i < numeroI; i++) {
				horas.get(i).add(t.get(i));
			}

			Boolean hace = true;

			for (int k = 0; k < numeroE; k++) {

				Integer suma = 0;

				for (int i = 0; i < numeroI; i++) {

					suma += t.get(i) * DatosInvestigadores.trabajadorEspecialidad(i, k);

				}

				if (suma < DatosInvestigadores.diasNecesarios(j, k)) {
					hace = false;
					k = numeroE;
				}
			}

			if (hace) {
				calidad += DatosInvestigadores.getCalidad(j);
			}
		}
	}

	public static SolucionesInvestigador empty() {
		return new SolucionesInvestigador();

	}

	public String toString() {
		String s3 = investigadores.stream().map(i -> "INV" + (i.id() + 1) + ": " + horas.get(i.id()))
				.collect(Collectors.joining("\n", "Reparto de horas:\n", "\n"));
		return String.format("%sCalidades de los trabajos sumadas: %d", s3, calidad);
	}

}
