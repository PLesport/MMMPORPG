package monstres;

import java.io.Serializable;

public class Treant extends Monstre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1617840893808609011L;

	public Treant() {
		super("Treant", 1, 400, 5, 2, 3, 2, 0, 2, 15, 10,5);
	}

	//Methode de génération d'un enemi de Type Treant de niveau n.
	public static Treant générationTreantNiveau(int n) {
		Treant treant = new Treant();
		for (int i = 0; i < n - 1; i++)
			treant.montéeNiveauMonstre();
		return treant;
	}

}
