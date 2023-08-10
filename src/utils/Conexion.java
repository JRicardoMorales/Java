package utils;

public record Conexion(int id, Double dis) {
	public static int contador;

	public static Conexion of(Double dis) {
		Integer id = contador;
		contador++;
		return new Conexion(id, dis);
	}

	public static Conexion ofFormat(String[] formato) {
		Double distancia = Double.valueOf(formato[2].trim());
		return of(distancia);

	}

	public String toString() {
		return "id: " + this.id() + "; distancia: " + this.dis();
	}

}
