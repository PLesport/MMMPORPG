package player;

import java.io.Serializable;

public class Voleur extends Personnage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8745032523407588242L;

	// Constructeur Personnage Initial
	public Voleur(String pseudonyme) {
		super(pseudonyme, 1, 250, 8, 10, 8, 2, 3, 2, 8, 3, 3);
	}

	public int dégatsMonstre() {
		return 0;
	}

}
