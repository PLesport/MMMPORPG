package basic;

public class MethodesUtiles {

	// G�n�rateur bool�en al�atoire.
	public static boolean randBool() {
		double x = Math.random();
		boolean y = true;
		if (x < 0.5) {
			y = false;
			return y;
		} else
			return y;
	}

	// G�n�rateur nombre al�atoire entier.
	public static int randInt(int min, int max) {
		return (int) ((Math.random() * (max - min + 1) + min));
	}
}
