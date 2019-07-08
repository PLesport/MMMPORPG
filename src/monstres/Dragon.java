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

	// Methode de g�n�ration d'un enemi de Type GallinetteCendr�e de niveau n.
	public static Dragon g�n�rationDragonNiveau(int n) {
		Dragon dragon = new Dragon();
		for (int i = 0; i < n - 1; i++)
			dragon.mont�eNiveauMonstre();
		return dragon;
	}
}
