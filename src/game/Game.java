package game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import events.Evenement;
import player.Personnage;

public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7935567585471879453L;
	private Personnage personnage;
	private int nombreDeTours;
	/**
	 * Cette Methode permet de lancer la partie en prenant en argument un Personnage
	 * créé préalablement ainsi qu'une valeur entière désignant le nombre
	 * d'evenements aléatoires a rencontrer avant de finir la partie.
	 */
	public Game(Personnage personnage, int nombreDeTours)       {
		this.personnage = personnage;
		this.nombreDeTours = nombreDeTours;
	}
	
	public static void Partie(Personnage personnage, Game game, int nombreDeTours) {

		while (personnage.getCompteurVictoire() < nombreDeTours) {
			Evenement.nextEvent(game);
		}
		System.out.println();
		System.out.println(
				"Vous ouvrez la porte suivante et un rayon de soleil vous indique que votre calvaire s'acheve enfin.");
		System.out.println("Vous avez finalement réussi à vous extirper de ce donjon maudit. Félicitations !");

		System.exit(0);
	}

	public static void Save(Game game) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Game.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			System.out.println("Votre partie est sauvegardée dans le fichier :  Game.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}


	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public int getNombreDeTours() {
		return nombreDeTours;
	}

	public void setNombreDeTours(int nombreDeTours) {
		this.nombreDeTours = nombreDeTours;
	}
}