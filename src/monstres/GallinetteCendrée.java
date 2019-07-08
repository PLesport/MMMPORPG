package monstres;

import java.io.Serializable;

public class GallinetteCendrée extends Monstre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2098739233899356611L;

	public GallinetteCendrée() {
		super("GallinetteCendrée", 1, 150, 2, 5, 1, 1, 2, 0, 2,1,3);
	}

	//Methode de génération d'un enemi de Type GallinetteCendrée de niveau n.
	public static GallinetteCendrée générationGallinetteCendréeNiveau(int n) {
		GallinetteCendrée gallinetteCendrée = new GallinetteCendrée();
		for (int i = 0; i < n - 1; i++)
			gallinetteCendrée.montéeNiveauMonstre();
		return gallinetteCendrée;
	}
}
