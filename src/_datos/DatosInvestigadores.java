package _datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import us.lsi.common.Files2;

public class DatosInvestigadores {
	public static List<Trabajo> trabajos;
	public static List<Investigador> investigadores;

	public record Trabajo(Integer id, Integer quality, List<Integer> days) {

		public static int contador;
		public static Trabajo create(String s2) {
			String[] t = s2.split(";");
			Integer c = Integer.parseInt(t[0].trim().split("=")[1].trim());
			String[] r = t[1].trim().split("=")[1].trim().split(",");
			List<Integer> days = new ArrayList<>();
			for (String s : r) {
				s = s.replace("(", "").replace(")", "").trim();
				days.add(Integer.parseInt(s.split(":")[1].trim()));
			}

			return new Trabajo(contador++, c, days);

		}
	}

	public record Investigador(Integer id, Integer capacity, Integer especiality) {

		public static int contador;
		public static Investigador create(String s1) {
			String[] i = s1.split(";");
			Integer c = Integer.parseInt(i[0].trim().split("=")[1].trim());
			Integer e = Integer.parseInt(i[1].trim().split("=")[1].trim());
			return new Investigador(contador++, c, e);
		}
	}

	public static void iniDatos(String ss3) {

		Trabajo.contador = 0;
		Investigador.contador = 0;
		trabajos = new ArrayList<>();
		investigadores = new ArrayList<>();

		List<String> list1 = Files2.linesFromFile(ss3);
		List<String> i1 = list1.subList(1, list1.indexOf("// TRABAJOS"));
		List<String> t1 = list1.subList(list1.indexOf("// TRABAJOS")+1, list1.size());

		for (String t : t1) {
			trabajos.add(Trabajo.create(t));

		}

		for (String i : i1) {
			investigadores.add(Investigador.create(i));

		}

		toConsole();

	}

	public static Integer getNumeroInvestigadores() {
		return investigadores.size();

	}

	public static Integer getNumeroTrabajos() {
		return trabajos.size();

	}

	public static Integer getNumeroEspecialidades() {
		return trabajos.get(0).days().size();

	}

	public static Integer trabajadorEspecialidad(Integer i, Integer j) {
		return investigadores.get(i).especiality().equals(j) ? 1 : 0;

	}

	public static Integer getCalidad(Integer j) {
		return trabajos.get(j).quality();

	}

	public static Integer diasDisponibles(Integer i) {
		return investigadores.get(i).capacity();

	}

	public static Integer diasNecesarios(Integer j, Integer i) {
		return trabajos.get(j).days().get(i);

	}

	public static Integer getMaxCapacity() {
		return investigadores.stream().map(i -> i.capacity()).max(Comparator.naturalOrder()).get() + 1;

	}

	private static void toConsole() {
		System.out.println(trabajos);
		System.out.println(investigadores);

	}

	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
	}

}
