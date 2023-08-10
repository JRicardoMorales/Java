package fp.paises;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaisesDelMundo {
	
	SortedSet<Pais> paises;
	LocalDateTime fechaHoraActualizacion;
	
	
	public PaisesDelMundo(Stream<Pais> paises, LocalDateTime fechaHoraActualizacion) {
		this.paises = (SortedSet<Pais>) paises.collect(Collectors.toSet());
		this.fechaHoraActualizacion = fechaHoraActualizacion;
		
	}
	
	
	public PaisesDelMundo(SortedSet<Pais> paises) {
		this.fechaHoraActualizacion = LocalDateTime.now();
		this.paises = paises;
	}
	

	public SortedSet<Pais> getPaises() {
		return paises;
	}
	public LocalDateTime getFechaHoraActualizacion() {
		return fechaHoraActualizacion;
	}
	
	public Integer getNumeroPaises() {
		return paises.size();
	}


	public int hashCode() {
		return Objects.hash(fechaHoraActualizacion, paises);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaisesDelMundo other = (PaisesDelMundo) obj;
		return Objects.equals(fechaHoraActualizacion, other.fechaHoraActualizacion)
				&& Objects.equals(paises, other.paises);
	}


	public String toString() {
		return "PaisesDelMundo [paises=" + paises + ", fechaHoraActualizacion=" + fechaHoraActualizacion + "]";
	}
	
	
public Double porcentajeAreaDeContinente(Continente continente) {
		Double areaMundo = paises.stream()
			.mapToDouble(Pais::getArea)
			.sum();
		Double areaContinente = paises.stream()
			.filter(pais -> pais.getContinente().equals(continente))
			.mapToDouble(Pais::getArea)
			.sum();
		return areaContinente / areaMundo;
	
	}

public List<String> nombresDeVecinos(String pais) {
	Map<String, String> paisesPorISO = paises.stream()
			.collect(Collectors.toMap(Pais::getCodigoISO, Pais::getNombre));
	
	Pais paisBuscado = paises.stream()
			.filter(p -> p.getNombre().equals(pais))
			.findFirst()
			.get();
	
	return paisBuscado.getVecinos().stream()
			.map(isoVecino -> paisesPorISO.get(isoVecino))
			.sorted()
			.toList();
}


public SortedMap<String, List<String>> paisesPorIdiomas() {
	SortedMap<String, List<String>> res = new TreeMap<>();
	for (Pais pais: paises) {
		for (Idioma idioma: pais.getIdiomas()) {
			String clave = idioma.nombre();
			if (res.containsKey(clave)) {
				res.get(clave).add(pais.getNombre());
			} else {
				List<String> paises = new ArrayList<String>();
				paises.add(pais.getNombre());
				res.put(clave, paises);
			}
		}
	}
	for (List<String> paises: res.values()) {
		Collections.sort(paises);
	}
	return res;
}


public SortedMap<String, String> paisMayorPoblacionPorMoneda() {
	Comparator<Pais> c = Comparator.comparing(Pais::getPoblacion)
			.thenComparing(Pais::getDensidadDePoblacion);
	
	return paises.stream()
			.collect(Collectors.groupingBy(
					Pais::getMoneda, TreeMap::new,
					Collectors.collectingAndThen(Collectors.maxBy(c), optPais -> optPais.get().getNombre())));
	
}

public List<String> vecinosMasFrecuentes(Integer n) {
	Comparator<Pais> cmp = Comparator.comparing((Pais p) -> p.getVecinos().size()).reversed();
	
	return paises.stream()
			.sorted(cmp)
			.limit(n)
			.map(p -> p.getNombre())
			.sorted()
			.collect(Collectors.toList());
	
}



	
	
	
	
	

}
