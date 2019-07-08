package monstres;

import java.io.Serializable;

public class Lag extends Monstre implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6244696037669138866L;

	public Lag() {
		super("Lag", 1, 250, 4, 4, 4, 4, 4, 4, 4, 4, 4);

	}

	// Methode de g�n�ration d'un enemi de Type Lag de niveau n.
	public static Lag g�n�rationLagNiveau(int n) {
		Lag lag = new Lag();
		for (int i = 0; i < n - 1; i++)
			lag.mont�eNiveauMonstre();
		return lag;
	}
}