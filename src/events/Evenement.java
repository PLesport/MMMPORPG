package events;

import java.io.Serializable;
import java.util.Scanner;

import game.Game;
import player.Personnage;

public abstract class Evenement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7953871464855785646L;

	/**
	 * Methode permettant au joueur de choisir entre la g�n�ration d'un Evenement
	 * Al�atoire ou �galement de prendre du repos en utilisant une unit� de
	 * feuDeCamp.
	 */
	public static void nextEvent(Game game) {
		Personnage personnage = game.getPersonnage();
		Scanner sc = new Scanner(System.in);
		int choix = 0;
		while (choix < 1 || choix > 3) {
			System.out.println(
					"Que voulez vous faire? 1 : Prendre du repos / 2 : Continuer votre avanc�e / 3 : Sauvegarder votre progression. ");
			choix = sc.nextInt();
			// Cette option est disponible pour se soigner entre les evenements al�atoires.
			// Utilise la
			// ressource feuDeCamp qui peut �tre suppl�ment�e en tombant sur l'�v�nement de
			// combat al�atoire Tr�ant.
			if (choix == 1) {
				if (personnage.getFeuDeCamp() > 0) {
					personnage.setFeuDeCamp(personnage.getFeuDeCamp() - 1);
					System.out.println(
							"Vous utilisez du bois de votre inventaire pour allumer un feu de camp. Il vous en reste : "
									+ personnage.getFeuDeCamp() + ".");
					Personnage.soinMedic(personnage);
				} else
					System.out.println(
							"Vous n'avez pas de quoi allumer un feu et votre repos ne vous apporte rien de plus qu'une envie d'avancer.");
			} else if (choix == 2) {
				evenementAl�atoire(game);
			} else if (choix == 3) {
				Game.Save(game);

			}
		}
	}

	/**
	 * Methode pour d�terminer le prochain evenement que le joueur va rencontrer
	 * al�atoirement. Il y a pour le moment 3 possibilit�s : un combat al�atoire
	 * contre un Monstre, la rencontre avec un coach sportif qui augmente le niveau
	 * du joueur sans passer par une augmentation de son exp�rience et finalement
	 * une option de rencontre d'un apothicaire qui prodigue des soins au joueur
	 */
	public static void evenementAl�atoire(Game game) {
		Personnage personnage = game.getPersonnage();
		double x = Math.random();
		if (x < 0.7) {
			Combat.CombatAl�atoire(game);
		} else if (x >= 0.7 && x < 0.9) {
			System.out.println(
					"Jerome le coach vous trouve trop sec et vous force a faire 100 burpees. Toute cette activit� vous permet de passer au niveau suivant.");
			Personnage.mont�eNiveauCoach(game.getPersonnage());
		} else {
			System.out.println("Vous rencontrez un apothicaire qui accepte de vous prodiguer des soins.");
			Personnage.soinMedic(personnage);
		}
	}

}
