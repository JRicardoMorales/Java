package fp.paises;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class Pais{
	
	String codigoISO;
	String nombre;
	Long poblacion;
	Double area;
	String capital;
	Continente continente;
	String moneda;
	List<Idioma> idiomas;
	Set<String> vecinos;
	
	
	
	public Pais(String codigoISO, String nombre, Long poblacion, Double area, String capital, Continente continente,
		String moneda, List<Idioma> idiomas, Set<String> vecinos) {
		Checkers.check("El valor de la polacion debe ser igual o mayor que cero", poblacion >= 0);
		Checkers.check("El area debe ser mayor o igual que cero", area >= 0);
		if (poblacion > 0) {
			Checkers.checkNoNull("Si el pais tiene poblacion, debe tener capital", capital);
			Checkers.checkNoNull("Si el pais tiene poblacion, debe tener moneda", moneda);
		}
		
		this.codigoISO = codigoISO;
		this.nombre = nombre;
		this.poblacion = poblacion;
		this.area = area;
		this.capital = capital;
		this.continente = continente;
		this.moneda = moneda;
		this.idiomas = idiomas;
		this.vecinos = vecinos;
		
	}
	

	public Pais(Stream<Pais> paises) {
	}


	public String getCodigoISO() {
		return codigoISO;
	}

	public String getNombre() {
		return nombre;
	}

	public Long getPoblacion() {
		return poblacion;
	}

	public Double getArea() {
		return area;
	}

	public String getCapital() {
		return capital;
	}


	public Continente getContinente() {
		return continente;
	}


	public String getMoneda() {
		return moneda;
	}


	public List<Idioma> getIdiomas() {
		return idiomas;
	}


	public Set<String> getVecinos() {
		return vecinos;
	}
	
	public Double getDensidadDePoblacion() {
		return poblacion / area;
	}
	
	public Boolean getAislado() {
		Boolean res = Boolean.FALSE;
		if (vecinos.size() < 0) {
			res = Boolean.TRUE;
		}
		return res;
	}
	
	public Boolean sonTodosVecinos(Set<Pais> paises) {
		return paises.stream()
		.allMatch(p-> getVecinos().contains(p.getCodigoISO()));
		}


	public int hashCode() {
		return Objects.hash(codigoISO);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(codigoISO, other.codigoISO);
	}

	public String toString() {
		return "Pais [codigoISO=" + codigoISO + ", nombre=" + nombre + ", poblacion=" + poblacion + ", area=" + area
				+ ", capital=" + capital + ", continente=" + continente + ", moneda=" + moneda + ", idiomas=" + idiomas
				+ ", vecinos=" + vecinos + "]";
	}

	
	
	
	

	
	
	

}
