package monstres;

import java.io.Serializable;

public class Golem extends Monstre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2640368477524746523L;

	public Golem() {
		super("Golem", 1, 450, 8, 5, 6, 2, 2, 2, 10, 3, 3);
	}

	// Methode de génération d'un enemi de Type GallinetteCendrée de niveau n.
	public static Golem générationGolemNiveau(int n) {
		Golem golem = new Golem();
		for (int i = 0; i < n - 1; i++)
			golem.montéeNiveauMonstre();
		return golem;
	}
}
