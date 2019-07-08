package basic;

public class MethodesUtiles {

	// Générateur booléen aléatoire.
	public static boolean randBool() {
		double x = Math.random();
		boolean y = true;
		if (x < 0.5) {
			y = false;
			return y;
		} else
			return y;
	}

	// Générateur nombre aléatoire entier.
	public static int randInt(int min, int max) {
		return (int) ((Math.random() * (max - min + 1) + min));
	}
}
