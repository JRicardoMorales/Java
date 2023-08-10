package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import ejercicios.ejercicio2.DatosCursos.Curso;

public class SolucionCursos {

public static SolucionCursos of_Range(List<Integer> value) {
	return new SolucionCursos(value);
	
}


public static SolucionCursos of(GraphPath<CursosVertex,CursosEdge> path) {
	List<Integer> ls = path.getEdgeList().stream()
			.map(e -> e.action())
			.toList();
	
	SolucionCursos res = of_Range(ls);
	res.path = ls;
	return res;
	
}

	private Double precio;
	private List<Integer> path;
	private List<Curso> cursos;

	public SolucionCursos() {
		precio = 0.;
		cursos = new ArrayList<>();
		
}

	
public SolucionCursos(List<Integer> ls) {
	precio = 0.;
	cursos = new ArrayList<>();	
	
	for (int i = 0; i < ls.size(); i++) {
		if (ls.get(i) > 0) {
			precio += DatosCursos.getPrecioCurso(i);
			cursos.add(DatosCursos.cursos.get(i));
			
		}
	}
}

public static SolucionCursos empty() {
	return new SolucionCursos();
	
}


@Override
public String toString() {
	String s = cursos.stream().map(c -> "S" + c.id())
			.collect(Collectors.joining(", ", "Cursos seleccionados: {", "}\n"));
	String res = String.format("%sCoste total: %.1f", s, precio);
	return path==null? res:
	String.format("%s\nPath de la solucion: %s", res, path);
	
	}

}


