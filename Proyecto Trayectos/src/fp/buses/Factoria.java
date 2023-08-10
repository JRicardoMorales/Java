package fp.buses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fp.utiles.Checkers;


public class Factoria {
	
	public static Trayecto leerTrayecto(String fichero) {
		Trayecto res = null;

		try {
			Stream<Trayecto> trayecto =
					Files.lines(Paths.get(fichero))
					.skip(1)
					.map(Factoria::parsearTrayecto);
			res = new Trayecto (trayecto) ;
		
		
		} catch (IOException e) {
			System.out.println("No se ha encontrado el fichero " + fichero);
		
		}



		return res;
	}
	
	
	public static Trayecto parsearTrayecto(String s) {
		Checkers.checkNoNull(s);
		String[] splits = s.split(";");
		String msg = String.format("Formato no valido <%s>", s);
		Checkers.check(msg, splits.length == 6);
		LocalDateTime fechaHora = LocalDateTime.parse(splits[0].trim(), DateTimeFormatter.ofPattern("d/M/y H:m"));
		String linea = splits[1].trim();
		Empresa empresa = Empresa.valueOf(splits[2].trim());
		Integer usuarios = Integer.valueOf(splits[3].trim());
		String paradaInicial = splits[4].trim();
		List<Etapa> recorrido = parseaRecorrido(splits[5].trim());
		
		return new Trayecto(fechaHora, linea, empresa, usuarios, paradaInicial, recorrido);
		
		}
	
	
	private static List<Etapa> parseaRecorrido(String cad) {
		Checkers.checkNoNull(cad);
		String limpia = cad.replace("[", "").replace("]", "").trim();
		String [] splits = limpia.split(",");
		List<Etapa> lg = new ArrayList<>();
		for (String s: splits) {
		lg.add(parseaEtapa(s));
		}
		return lg;
		}
	
	
	private static Etapa parseaEtapa(String cad) {
		Checkers.checkNoNull(cad);
		String [] splits = cad.split("-");
		String msg = String.format("Formato resultado no valido <%s>", cad);
		Checkers.check(msg, splits.length == 2);
		String parada = splits[1].trim();
		Integer minutos = Integer.valueOf(splits[0].trim());
		return new Etapa(parada, minutos);
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
