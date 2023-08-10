package ejercicios;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ejercicio4 {
	
	public static String ejercicio4RecursivoSinMemoria(Integer a, Integer b, Integer c) {
		String s = "";
		
		if (b<=2 && a<2 || c<2) {
			s = String.format("(%d+%d+%d)",a,b,c);
	  } else if (a<3 || c<=3 && b<3) {
	 	    s = String.format("(%d-%d-%d)",c,b,a);
	  } else if ((b%3==0 || a%2==0) && b%a==0) {
 	        s = String.format("(%s*%s)",ejercicio4RecursivoSinMemoria(a-1,b/a,c-1),ejercicio4RecursivoSinMemoria(a-2,b/2,c/2));
	  } else {
 	        s = String.format("(%s/%s)",ejercicio4RecursivoSinMemoria(a/2,b-2,c/2),ejercicio4RecursivoSinMemoria(a/3,b-1,c/3));
	}
		return s;
	}
	
	public static String ejercicio4RecursivoConMemoria(Integer a, Integer b, Integer c) {
		Map<List<Integer>, String> acum = new HashMap<>();
		return recursivaConMemoria (a, b, c, acum);
	}
	private static String recursivaConMemoria(Integer a, Integer b, Integer c, Map<List<Integer>,String> acum) {
		String s;
		List<Integer> l1 = new ArrayList<>();
		l1.add(c);
		l1.add(b);
		l1.add(a);
		
		if (acum.containsKey(l1)) {
			s = acum.get(l1);
	  } else if (b<=2 && a<2 || c<2) {
		    s = String.format("(%d+%d+%d)",a,b,c);
		    acum.put(l1,s);
	  } else if (a<3 || c<=3 && b<3) {
		    s = String.format("(%d-%d-%d)",c,b,a);
		    acum.put(l1,s);
	  } else if ((b%3==0 || a%2==0) && b%a==0) {
		    s = String.format("(%s*%s)",recursivaConMemoria(a-1,b/a,c-1, acum),recursivaConMemoria(a-2,b/2,c/2,acum));
		    acum.put(l1,s);
	  } else {
		    s = String.format("(%s/%s)",recursivaConMemoria(a/2,b-2,c/2,acum),recursivaConMemoria(a/3,b-1,c/3,acum));
		    acum.put(l1,s);
	  }
		return s;
	}
	
	public static String ejercicio4Iterativo(Integer a, Integer b, Integer c) {
        String s;
        Map<List<Integer>, String > m1 = new HashMap<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> l3 = new ArrayList<>();
        
        for (int e = 0;
        		e <= a;
        			e ++) {
            for (int f = 0;
            		f <= b;
            			f ++) {
                for (int g = 0;
                		g <= c;
                	        g ++) {
                	l1.clear();
                	l2.clear();
                	l3.clear();
                	l1.add(e);
                	l1.add(f);
                	l1.add(g);
                	
                	if (f<=2 && e<2 || g<2) {
                		s = String.format("(%d+%d+%d)", e, f, g);
              	  } else if (e<3 || g<=3 && f<3) {
          	 	        s = String.format("(%d-%d-%d)",g,f,e);
              	  } else if ((g%3==0 || e%2==0) && f%e==0) {
            		    l2.add(e-1);
            		    l2.add(f/e);
            		    l2.add(g-1);
              		    l3.add(e-2);
              			l3.add(f/2);
              			l3.add(g/2);
              		    s = String.format("(%s*%s)", m1.get(l2), m1.get(l3));
              	  } else {
              		  	l2.add(e/2);
              		  	l2.add(f-2);
              		  	l2.add(g/2);
            			l3.add(e/3);
            			l3.add(f-1);
            			l3.add(g/3);
            		    s = String.format("(%s/%s)", m1.get(l2), m1.get(l3));
              		  
              	  }
                m1.put(new ArrayList<>(l1),s);	
                }
                	
            }
            	
        }
        return m1.get(l1);
	}
	
	
	
	
	
	
}

