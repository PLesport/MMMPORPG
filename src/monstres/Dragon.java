package monstres;

import java.io.Serializable;

public class Dragon extends Monstre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -54473383258117192L;

	public Dragon() {
		super("Dragon", 1, 450, 8, 5, 6, 2, 2, 2, 10, 3, 3);
	}

	// Methode de génération d'un enemi de Type GallinetteCendrée de niveau n.
	public static Dragon générationDragonNiveau(int n) {
		Dragon dragon = new Dragon();
		for (int i = 0; i < n - 1; i++)
			dragon.montéeNiveauMonstre();
		return dragon;
	}
}
