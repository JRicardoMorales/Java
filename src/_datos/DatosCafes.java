package _datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import us.lsi.common.Files2;

public class DatosCafes {

	public static List<Variedad> variedades;
	public static List<Integer> tipos;

	public record Variedad(int id, Integer beneficio, List<Double> composicion) {

		public static int contador;

		public static Variedad create(String ss) {
			List<Double> c1 = new ArrayList<>();
			for (int j = 0; j < tipos.size(); j++) {
				c1.add(0.);
			}

			String[] var = ss.split(";");
			Integer ben = Integer.parseInt(var[0].split("=")[1].replace(";", "").trim());
			String[] composiciones = var[1].split("=")[1].trim().split(",");
			for (int j = 0; j < composiciones.length; j++) {
				String[] porcent = composiciones[j].replace("(C", "").replace(")", "").split(":");
				Integer tipo = Integer.parseInt(porcent[0].trim()) - 1;
				Double porcentaje = Double.parseDouble(porcent[1].trim());
				c1.set(tipo, porcentaje);
			}
			return new Variedad(contador++, ben, new ArrayList<>(c1));
		}
	}

	public static void iniDatos(String ss2) {
		Variedad.contador = 0;
		List<String> list1 = Files2.linesFromFile(ss2);
		int pos = list1.indexOf("// VARIEDADES");
		List<String> tipoCafe = list1.subList(1, pos);
		List<String> variedadCafe = list1.subList(pos + 1, list1.size());

		List<Integer> list_aux = new ArrayList<>();
		for (int i = 0; i < tipoCafe.size(); i++) {
			Integer valor = Integer.parseInt(tipoCafe.get(i).split("=")[1].replace(";", "").trim());
			list_aux.add(valor);
		}

		tipos = new ArrayList<>(list_aux);
		variedades = new ArrayList<>();

		for (int i = 0; i < variedadCafe.size(); i++) {
			variedades.add(Variedad.create(variedadCafe.get(i)));
		}

		toConsole();

	}

	public static Integer getNumeroTipos() {
		return tipos.size();
	}

	public static List<Variedad> getVariedades() {
		return new ArrayList<>(variedades);

	}

	public static Integer getNumeroVariedades() {
		return variedades.size();
	}

	public static Integer getKilosTipo(Integer j) {
		return tipos.get(j);

	}

	public static Double getKilosTipoVariedad(Integer j, Integer i) {
		return variedades.get(i).composicion().get(j);

	}

	public static Integer getBeneficiosVariedad(Integer i) {
		return variedades.get(i).beneficio();

	}

	public static Integer getMaxKilosVariedad(Integer i) {
		List<Double> listaMax = new ArrayList<>();
		for (int j = 0; j < tipos.size(); j++) {
			listaMax.add(getKilosTipo(j) / getKilosTipoVariedad(j, i));
		}

		listaMax.sort(Comparator.naturalOrder());
		return listaMax.get(0).intValue();

	}

	private static void toConsole() {
		System.out.println("Kilos disponibles de cada tipo: " + tipos + "\nVariedades : " + variedades);

	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			System.out.println("DATOS DE ENTRADA " + (i + 1) + " ");
			String fichero = "ficheros/Ejercicio1DatosEntrada" + String.valueOf(i + 1) + ".txt";
			iniDatos(fichero);
			System.out.println(" ");
			System.out.println(" ");
		}
	}
}
