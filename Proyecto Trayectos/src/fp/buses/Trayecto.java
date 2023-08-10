package fp.buses;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class Trayecto implements Comparable <Trayecto> {
		
	LocalDateTime fechaHora;
	String nombre;
	Empresa empresa;
	Integer usuarios;
	String paradaInicial;
	List<Etapa> recorrido;
	
	
public Trayecto(LocalDateTime fechaHora, String nombre, Empresa empresa, Integer usuarios, String paradaInicial, List<Etapa> recorrido) {
	Checkers.check("Debe haber como minimo una parada en el recorrido", recorrido.size() > 0);
	Checkers.checkNoNull("La primera parada no puede ser nula", paradaInicial);
	
	this.fechaHora = fechaHora;
	this.nombre = nombre;
	this.empresa = empresa;
	this.usuarios = usuarios;
	this.paradaInicial = paradaInicial;
	this.recorrido = recorrido;
	
}


public Trayecto(Stream<Trayecto> trayecto) {
	// TODO Auto-generated constructor stub
}


public LocalDateTime getFechaHora() {
	return fechaHora;
}


public String getNombre() {
	return nombre;
}


public Empresa getEmpresa() {
	return empresa;
}


public Integer getUsuarios() {
	return usuarios;
}


public String getParadaInicial() {
	return paradaInicial;
}


public List<Etapa> getRecorrido() {
	return recorrido;
}


public Integer getNumeroDeParadas() {
	return recorrido.size() + 1;
}

public LocalTime getHoraLlegada() {
	return getHoraLlegadaParada(recorrido.size() - 1);
}

public LocalTime getHoraLlegadaParada(int ind) {
	Checkers.check("Índice fuera de rango", ind >= 0 && ind < recorrido.size());
	LocalDateTime res = getFechaHora();
	for (int i = 0; i <= ind; i++) {
		Etapa etapa = recorrido.get(i);
		res = res.plusMinutes(etapa.minutos());
	}
	return res.toLocalTime();
}

public List<String> getParadas() {
	List<String> res = new ArrayList<String>();
	res.add(paradaInicial);
	for (Etapa etapa: recorrido) {
		res.add(etapa.parada());
	}
	return res;
}

public int compareTo(Trayecto t) {
	int res = getFechaHora().compareTo(t.getFechaHora());
	if (res == 0) {
		res= getNombre().compareTo(t.getNombre());
	}
	return res;
}


public int hashCode() {
	return Objects.hash(fechaHora, nombre);
}


public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Trayecto other = (Trayecto) obj;
	return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(nombre, other.nombre);
}


public String toString() {
	return "Trayecto [fechaHora=" + fechaHora + ", nombre=" + nombre + ", empresa=" + empresa + ", usuarios=" + usuarios
			+ ", paradaInicial=" + paradaInicial + ", recorrido=" + recorrido + ", getNumeroDeParadas()="
			+ getNumeroDeParadas() + ", getHoraLlegada()=" + getHoraLlegada() + ", getParadas()=" + getParadas() + "]";
}



public Integer getTiempoRecorrido() {
	return recorrido.stream()
			.mapToInt(Etapa::minutos)
			.sum();
	
}




	
	
	
	
	
	

}
