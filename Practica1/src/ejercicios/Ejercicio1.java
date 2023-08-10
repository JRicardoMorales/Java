package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.EnteroCadena;

public class Ejercicio1 {

		public static Map<Integer, List<String>> ejercicio1Iterativo(Integer vA, String vB, Integer vC, String vD, Integer vE) {
			
			EnteroCadena ec1 = EnteroCadena.of(vA, vB);
			Map<Integer, List<String>> acumulador = new HashMap<>();
			List<String> list = new ArrayList<>();
			String str;
			Integer i;
			
			while (ec1.a() < vC) {
				list.clear();
				str = ec1.s() + vD;
				list.add(str);
				
			if (str.length()< vE) {
			if (acumulador.containsKey(str.length())) {
				acumulador.get(str.length()).add(str);
			} else {
				acumulador.put(str.length(), new ArrayList<>(list));
			}
			}
			i = ec1.a()+2;
			if (ec1.a()%3 == 0) {
				str = ec1.s()+ec1.a().toString();
			} else {
				str = ec1.s().substring(ec1.a()%ec1.s().length());			
			}
			ec1 = EnteroCadena.of(i,str);
			}
			return acumulador;
		}
		
		public static Map<Integer, List<String>> ejercicio1RecursivaFinal(Integer vA, String vB, Integer vC, String vD, Integer vE) {
			return recursivaFinal(EnteroCadena.of(vA, vB), vC, vD, vE, new HashMap<>());
		}
	
		private static Map<Integer, List<String>> recursivaFinal (EnteroCadena ec2, Integer vC, String vD, Integer vE, Map<Integer, List<String>> acum) {
			List<String> list2 = new ArrayList<>();
			String st;
			Integer i;
			
			if (ec2.a()<vC) {
				list2.clear();
				st = ec2.s() + vD;
				list2.add(st);
			
			
			if (st.length()< vE) {
			if (acum.containsKey(st.length())) {
				acum.get(st.length()).add(st);
			} else {
				acum.put(st.length(), new ArrayList<>(list2));
			}
			}
			
			i = ec2.a() + 2;
			if (ec2.a()%3 == 0) {
				st = ec2.s()+ec2.a().toString();
			} else {
				st= ec2.s().substring(ec2.a()%ec2.s().length());	
			}
			acum = recursivaFinal(EnteroCadena.of(i, st), vC, vD, vE, acum);
			}
		return acum;
		}
   
	
	 
}

