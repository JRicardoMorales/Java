package ejercicios;

import java.util.stream.Stream;

public class Ejercicio2 {
	public static Integer ejercicio2Iterativo(Integer a, Integer b, String s) {
		Integer c = 0;
		
		while (!(b<2 || a<2) && s.length()!=0) {
			if (b%s.length() > a%s.length()) {
				c = a + b + c;
				s = s.substring(a%s.length(),b%s.length());
				b = b/2;
				a = a-1;
			} else {
				c = c + a * b ;
				s = s.substring(b%s.length(),a%s.length());
				b = b-1;
				a = a/2;
			}
		}
			if (s.length()==0) {
				c =  a*a + c + b*b ;
			} else {
				c = a + b + s.length() + c;
			}
			
		    
		    return c;
	}
	
	
	public static Integer ejercicio2RecursivoNoFinal(Integer a, Integer b, String s) {
		Integer c;
		
		if (b<2 || a<2) {
			c = a + b + s.length();
	   }else if(s.length()== 0) {
		   c = a * a + b * b;
	   }else if(b%s.length() > a%s.length()) {
		   c = ejercicio2RecursivoNoFinal(a-1,b/2,s.substring(a%s.length(),b%s.length())) + a + b;
	   }else {
		   c = ejercicio2RecursivoNoFinal(a/2,b-1,s.substring(b%s.length(),a%s.length())) + a * b;
	   }
	   return c;
	}
	
	public static Integer ejercicio2RecursivoFinal(Integer a, Integer b, String s) {
	    return recursivoFinal(a,b,s,0);
	}
	private static Integer recursivoFinal(Integer a, Integer b, String s, Integer acumulador) {
		Integer c = 0;
		
		if (a<2 || b<2) {
			c = acumulador + a + b + s.length();
	   }else if(s.length()== 0) {
		   c = acumulador + a * a + b * b;
	   }else if(b%s.length() > a%s.length()) {
		   c = acumulador + recursivoFinal(a-1,b/2,s.substring(a%s.length(),b%s.length()), + a + b);
	   }else {
		   c = acumulador + recursivoFinal(a/2,b-1,s.substring(b%s.length(),a%s.length()), + a * b);
	   }
	   return c;
		
	}
	
	public static Integer ejercicio2Funcional(Integer a, Integer b, String s) {
		Tupla1 t1 = Stream.iterate(Tupla1.primero(a,b,s),e ->e.nexo())
				.filter((e->e.s().length()==0 || e.b()<2 || e.a()<2))
				.findFirst()
				.get();
		
	    Integer r;
	    r = t1.s().length()==0 ? t1.acum()+t1.a()*t1.a() + t1.b()*t1.b(): t1.acum() + t1.s().length() + t1.b() + t1.a();
	    return r;
	}
	
	public record Tupla1 (Integer acum, Integer a, Integer b, String s) {
		public static Tupla1 of(Integer acum, Integer a, Integer b, String s) {
			return new Tupla1 (acum, a, b, s);
		}
	
	public static Tupla1 primero (Integer a, Integer b, String s) {
		return of(0, a,b ,s);
	}
	public Tupla1 nexo() {
		Tupla1 res;
		 res = b%s.length() >a%s.length()  ? of(a + b + acum, a-1, b/2,s.substring(a%s.length(),b%s.length())):of(a * b + acum, a/2, b-1,s.substring(b%s.length(),a%s.length()));
		 return res;
	}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}