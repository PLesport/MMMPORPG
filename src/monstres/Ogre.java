package monstres;

import java.io.Serializable;

public class Ogre extends Monstre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6508281930471581260L;

	public Ogre() {
		super("Ogre", 1, 350, 12, 2, 1, 3, 0, 0, 15, 3,1);
	}

	//Methode de g�n�ration d'un enemi de Type Ogre de niveau n.
	public static Ogre g�n�rationOgreNiveau(int n) {
		Ogre ogre = new Ogre();
		for (int i = 0; i < n - 1; i++)
			ogre.mont�eNiveauMonstre();
		return ogre;
	}

}
