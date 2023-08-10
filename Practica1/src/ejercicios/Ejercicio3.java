package ejercicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import test.Test.TuplaEjercicio3;
import us.lsi.geometria.Punto2D.Cuadrante;
import us.lsi.geometria.Punto2D;
import us.lsi.streams.Stream2;

public class Ejercicio3 {
	public static List<Punto2D> ejercicio3Iterativo(String s, String r){
		List<Punto2D> l1 = new ArrayList<>();
		Iterator<Punto2D> i1 = Stream2.file(s)
				.map(a -> TuplaEjercicio3.parse(a))
				.iterator();
		Iterator<Punto2D> i2 = Stream2.file(r)
				.map(a -> TuplaEjercicio3.parse(a))
				.iterator();
		
		Punto2D p1 = (i1.hasNext()?i1.next():null);
		Punto2D p2 = (i2.hasNext()?i2.next():null);
		
		while (p1 != null && p2 != null ) {
			if(p2.compareTo(p1)>0) {
				if(p1.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p1.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
					l1.add(p1);
				}
			      if (i1.hasNext()) {
				  p1 = i1.next();
			       } else {
				  p1 = null;
			       }
			} else {
				if(p2.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p2.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
					l1.add(p2);
				}
				if (i2.hasNext()) {
					  p2 = i2.next();
				       } else {
					  p2 = null;
				       }
				   }
		}
		while (p1!=null) {
			if(p1.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p1.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
				l1.add(p1);
			}
		      if (i1.hasNext()) {
			  p1 = i1.next();
		       } else {
			  p1 = null;
		       }
		      }
		
		while (p2!= null) {
			if(p2.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p2.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
				l1.add(p2);
			}
			if (i2.hasNext()) {
				  p2 = i2.next();
			       } else {
				  p2 = null;
			       }
			
		     
		
			}
		return l1;
			
		}
		
	
	
	public static List<Punto2D> ejercicio3RecursivoFinal(String s, String r){
		Iterator<Punto2D> i1 = Stream2.file(s)
				.map(a->TuplaEjercicio3.parse(a))
				.iterator();
		Iterator<Punto2D> i2 = Stream2.file(r)
				.map(a->TuplaEjercicio3.parse(a))
				.iterator();
		return recursivoFinal(new ArrayList<Punto2D>(), i1, i2, i1.hasNext()?i1.next():null, i2.hasNext()?i2.next():null);
	}
	
	public static List<Punto2D>recursivoFinal(List<Punto2D> acumulador, Iterator<Punto2D> i1, Iterator<Punto2D> i2, Punto2D p1, Punto2D p2){
		if (p2!= null && p1!=null) {
			if (p1.compareTo(p2) <0) {
				if (p1.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p1.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
					acumulador.add(p1);
				}
				if (i1.hasNext()) {
					p1 = i1.next();
				} else {
					p1 = null;
				}
				recursivoFinal(acumulador, i1, i2, p1, p2);
			} else {
				if (p2.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p2.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
					acumulador.add(p2);
			}
				if (i2.hasNext()) {
					p2 = i2.next();
				} else {
					p2 = null;
				}
				recursivoFinal(acumulador, i1, i2, p1, p2);
			} 
		} else if (p1!= null) {
			if (p1.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p1.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
				acumulador.add(p1);
			}
			if (i1.hasNext()) {
				p1 = i1.next();
			} else {
				p1 = null;
			}
			recursivoFinal(acumulador, i1, i2, p1, p2);
			
		} else if(p2!= null) {
			if (p2.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || p2.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
				acumulador.add(p2);
			}
			if (i2.hasNext()) {
				p2 = i2.next();
			} else {
				p2 = null;
			}
			recursivoFinal(acumulador, i1, i2, p1, p2);
			
		}
		return acumulador;
	}
	
	public static List<Punto2D> ejercicio3Funcional(String s, String r){
		Iterator<Punto2D> i1 = Stream2.file(s)
				.map(a->TuplaEjercicio3.parse(a))
				.iterator();
		Iterator<Punto2D> i2 = Stream2.file(r)
				.map(a->TuplaEjercicio3.parse(a))
				.iterator();
		
		return Stream.iterate(Tupla1.primero(i1,i2),t->t.nexo())
				.takeWhile(t->t.p2()!=null && t.p1() !=null)
				.map(m->(m.p2()!=null && m.p1()!=null)?m.p1().compareTo(m.p2())<0?m.p1():m.p2():m.p1()!=null?m.p1():m.p2())
				.filter(p->p!=null)
				.filter(c->c.getCuadrante().equals(Cuadrante.TERCER_CUADRANTE) || c.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE))
				.collect(Collectors.toList());
	}
	
	public record Tupla1(Iterator<Punto2D> i1, Iterator<Punto2D> i2, Punto2D p1, Punto2D p2) {
		private static Tupla1 of(Iterator<Punto2D> i1, Iterator<Punto2D> i2, Punto2D p1, Punto2D p2) {
			return new Tupla1(i1, i2, p1, p2);
		}
	
	private static Tupla1 primero (Iterator<Punto2D> i1, Iterator<Punto2D> i2) {
		return of(i1, i2, i1.hasNext()?i1.next():null, i2.hasNext()?i1.next():null);
	}
	
	private Tupla1 nexo() {
		Tupla1 r = this;
		
		if(p2!=null && p1!=null) {
			if (p2.compareTo(p1)>0) {
				r = Tupla1.of(i1, i2, i1.hasNext()?i1.next():null,p2);
			} else {
				r = Tupla1.of(i1, i2, p1, i2.hasNext()?i2.next():null);
			}
			
		} else if (p1 != null){
			    r = Tupla1.of(i1, i2, i1.hasNext()?i1.next():null,p2);
			
		} else if(p2 != null) {
			    r = Tupla1.of(i1, i2, p1, i2.hasNext()?i2.next():null);
		}
		return r;
	}
		
	}
	
			
	


	
	
}
