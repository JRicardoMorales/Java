package fp.paises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import fp.utiles.Checkers;


public class FactoriaPais {
	
public static Pais leerContenedorActores(String fichero) {
		Pais res = null;

		try {
			Stream<Pais> paises =
					Files.lines(Paths.get(fichero))
					.skip(1)
					.map(FactoriaPais::parsearPais);
			res = new Pais(paises);
		
		
		} catch (IOException e) {
			System.out.println("No se ha encontrado el fichero " + fichero);
		
		}

		


		return res;
	}
	

private static Pais parsearPais (String cadena) {
	String sp[] = cadena.split(";");
	Checkers.check("Cadena con formato no válido", sp.length == 9);
	
	Long poblacion = Long.valueOf(sp[2].trim());
	Double area = Double.valueOf(sp[3].trim());
	String capital = parseaCadenaNula(sp[4]);
	Continente continente = parseaContinente(sp[5].trim());
	String moneda = parseaCadenaNula(sp[6]);
	List<Idioma> idiomas = parseaIdiomas(sp[7].trim());
	Set<String> vecinos = parseaVecinos(sp[8]);
	
	return new Pais(sp[0].trim(), sp[1].trim(), poblacion, area, capital, continente, moneda, idiomas, vecinos);
	
}

private static Set<String> parseaVecinos(String cadena) {
	Set<String> res = new HashSet<>();
	if (!cadena.trim().isEmpty()) {
		String[] sp = cadena.split(",");
		for (String s: sp) {
			res.add(s.trim());
		}
	}
	
	return res;
}

private static String parseaCadenaNula(String cadena) {
	String res = null;
	if (!cadena.trim().isEmpty()) {
		res = cadena.trim();
	}
return res;

}

private static List<Idioma> parseaIdiomas(String cadena) {
	List<Idioma> res = new ArrayList<>();
	if (!cadena.trim().isEmpty()) {
		String[] sp = cadena.split(",");
		for (String s: sp) {
			res.add(parseaIdioma(s.trim()));
		}
	}
return res;

}

private static Idioma parseaIdioma(String cadena) {
	String[] sp = cadena.split(":");
	Checkers.check("Formato idioma no válido [" + cadena + "}]", sp.length == 2);
	Double porcentaje = Double.parseDouble(sp[1].trim());
	return new Idioma(sp[0].trim(), porcentaje);
	
}


private static Continente parseaContinente(String s) {
	switch(s) {
	case "AF": return Continente.AFRICA;
	case "AN": return Continente.ANTARCTICA;
	case "AS": return Continente.ASIA;
	case "EU": return Continente.EUROPE;
	case "NA": return Continente.NORTH_AMERICA;
	case "OC": return Continente.OCEANIA;
	case "SA": return Continente.SOUTH_AMERICA;
	default: return null;
	
	}
}





}
