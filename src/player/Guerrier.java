package player;

import java.io.Serializable;

public class Guerrier extends Personnage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1792350492826897926L;

	// Constructeur Personnage Initial
	public Guerrier(String pseudonyme) {
		super(pseudonyme, 1, 300, 10, 8, 6, 4, 1, 1, 10, 5, 0);
	}

	public int dégatsMonstre() {
		return 0;
	}

}
