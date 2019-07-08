package monstres;

import java.io.Serializable;

public class GallinetteCendr�e extends Monstre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2098739233899356611L;

	public GallinetteCendr�e() {
		super("GallinetteCendr�e", 1, 150, 2, 5, 1, 1, 2, 0, 2,1,3);
	}

	//Methode de g�n�ration d'un enemi de Type GallinetteCendr�e de niveau n.
	public static GallinetteCendr�e g�n�rationGallinetteCendr�eNiveau(int n) {
		GallinetteCendr�e gallinetteCendr�e = new GallinetteCendr�e();
		for (int i = 0; i < n - 1; i++)
			gallinetteCendr�e.mont�eNiveauMonstre();
		return gallinetteCendr�e;
	}
}
