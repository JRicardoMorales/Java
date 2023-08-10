package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import us.lsi.common.Files2;

public class DatosInvestigadores {	
	
	public static List<Trabajo> trabajos;
	public static List<Investigador> investigadores;
	
public record Investigador(Integer id, Integer capacidad, Integer especialdiad) {
	public static int contador;
	
public static Investigador create(String linea) {
	String[] inv = linea.split(";");
	Integer cap = Integer.parseInt(inv[0].trim().split("=")[1].trim());
	Integer esp = Integer.parseInt(inv[1].trim().split("=")[1].trim());
	return new Investigador(contador++, cap, esp);
	
	}
}


public record Trabajo(Integer id, Integer calidad, List<Integer> dias) {
	public static int contador;
	public static Trabajo create(String linea) {
		String[] trab = linea.split(";");
		Integer cal = Integer.parseInt(trab[0].trim().split("=")[1].trim());
		String[] rep = trab[1].trim().split("=")[1].trim().split(",");
		List<Integer> dias = new ArrayList<>();
		
		for (String s : rep) {
			s = s.replace("(", "").replace(")", "").trim();
			dias.add(Integer.parseInt(s.split(":")[1].trim()));
			
			}
		
		return new Trabajo(contador++, cal, dias);
	}
}

public static void iniDatos(String fichero) {

	Investigador.contador = 0;
	Trabajo.contador = 0;
	trabajos = new ArrayList<>();
	investigadores = new ArrayList<>();
	

	List<String> lineas = Files2.linesFromFile(fichero);
	List<String> invs = lineas.subList(1, lineas.indexOf("// TRABAJOS"));
	List<String> trabs = lineas.subList(lineas.indexOf("// TRABAJOS") + 1, lineas.size());

	for (String i : invs) {
		investigadores.add(Investigador.create(i));
		
	}

	for (String t : trabs) {
		trabajos.add(Trabajo.create(t));
		
	}

	toConsole();
	
}

public static Integer getNumeroTrabajos() {
	return trabajos.size();
	
}

public static Integer getNumeroInvestigadores() {
	return investigadores.size();
	
}

public static Integer getNumeroEspecialidades() {
	return trabajos.get(0).dias().size();
	
}


public static Integer trabajadorEspecialidad(Integer i, Integer k) {
	return investigadores.get(i).especialdiad().equals(k) ? 1 : 0;
	
}

public static Integer diasDisponibles(Integer i) {
	return investigadores.get(i).capacidad();
	
}

public static Integer diasNecesarios(Integer j, Integer k) {
	return trabajos.get(j).dias().get(k);
	
}

public static Integer getCalidad(Integer j) {
	return trabajos.get(j).calidad();
	
}

public static Integer getMM() {
	return investigadores.stream().map(i -> i.capacidad()).max(Comparator.naturalOrder()).get()+ 1;

}


public static Integer getEspecialidadInvestigador(Integer i) {
	return investigadores.get(i).especialdiad();
	
}

private static void toConsole() {
	System.out.println(investigadores);
	System.out.println(trabajos);
	
}

public static void main(String[] args) {
	iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
		
	}

}
