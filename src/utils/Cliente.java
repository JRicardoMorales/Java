package utils;

public record Cliente(int id, Double benef) {
	public static Cliente of(int id, Double benef) {
		return new Cliente(id, benef);

	}

	public static Cliente ofFormat(String[] f) {
		Integer id = Integer.valueOf(f[0].trim());
		Double ben = Double.valueOf(f[1].trim());
		return of(id, ben);

	}

	public String toString() {
		return String.valueOf(this.id());

	}
}
