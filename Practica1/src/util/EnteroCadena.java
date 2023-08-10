package util;

public record EnteroCadena(Integer a, String s) {
	public static EnteroCadena of (Integer vA, String vB) {
		return new EnteroCadena(vA, vB);
	}
}
