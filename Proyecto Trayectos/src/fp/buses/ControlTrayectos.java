package fp.buses;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControlTrayectos {
	
	List<Trayecto> trayectos;

	public ControlTrayectos(Stream<Trayecto> trayectos) {
		this.trayectos = trayectos.collect(Collectors.toList());
		
	}

	public List<Trayecto> getTrayectos() {
		return trayectos;
	}
	
	public Integer getNumTrayectos() {
		return trayectos.size();
	}

	public int hashCode() {
		return Objects.hash(trayectos);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControlTrayectos other = (ControlTrayectos) obj;
		return Objects.equals(trayectos, other.trayectos);
	}

	public String toString() {
		return "ControlTrayectos [trayectos=" + trayectos + ", getNumTrayectos()=" + getNumTrayectos() + "]";
	}
	
	
	public List<String> getParadasEmpresas(Set<Empresa> empresas, Character c) {
		return trayectos.stream()
				.filter(x -> empresas.contains(x.getEmpresa()))
				.flatMap(x -> x.getParadas().stream())
				.filter(x -> c.equals(x.charAt(0)))
				.collect(Collectors.toList());
		
		}

	
	public SortedSet<Trayecto> getTrayectosMasUsuarios(Integer n) {
		Comparator<Trayecto> cmp = Comparator.comparing(Trayecto::getFechaHora)
				.thenComparing(Comparator.naturalOrder());
			return trayectos.stream()
					.sorted(Comparator.comparing(Trayecto::getUsuarios).reversed())
					.limit(n)
					.collect(Collectors.toCollection(() -> new TreeSet<>(cmp)));
			
		}
	
	
	public String getLineaMasUsuarios(Integer mes) {
		Map<String, Integer> m = trayectos.stream().
				filter(x -> mes.equals(x.getFechaHora().getMonthValue())).
				collect(Collectors.groupingBy(Trayecto::getNombre,
						Collectors.summingInt(Trayecto::getUsuarios)));
		
		return m.entrySet().stream()
				.max(Comparator.comparing(Map.Entry::getValue))
				.map(Map.Entry::getKey)
				.orElse(null);
		}
	
	
	public Map<Empresa, Trayecto> getTrayectoMasTiempoRecorridoPorEmpresa() {
		Comparator<Trayecto> cmp = Comparator.comparing(Trayecto::getTiempoRecorrido);
		
		return trayectos.stream()
				.collect(Collectors.groupingBy(
						Trayecto::getEmpresa,
						Collectors.collectingAndThen(
								Collectors.maxBy(cmp), Optional::get)));
		}

	
	public Integer getTotalUsuarios(String parada, LocalTime hora) {
		Integer numUsuarios = 0;
		for (Trayecto t: trayectos) {
			LocalTime horaLlegada = null;
			int ind = t.getParadas().indexOf(parada);
			if (ind == 0) {
				horaLlegada = t.getFechaHora().toLocalTime();
			} else if (ind > 0) {
				horaLlegada = t.getHoraLlegadaParada(ind - 1);
			}
			if (horaLlegada != null && hora.isBefore(horaLlegada)) {
				numUsuarios += t.getUsuarios();
				}
			}
		
			return numUsuarios;
			
		}
	
	

}
