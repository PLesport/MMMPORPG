package player;

import java.io.Serializable;

public class Mage extends Personnage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6749475330558210900L;

	// Constructeur Personnage Initial
	public Mage(String pseudonyme) {
		super(pseudonyme, 1, 200, 6, 6, 10, 2, 1, 5, 6, 0, 5);
	}

	public int dégatsMonstre() {
		return 0;
	}
}
