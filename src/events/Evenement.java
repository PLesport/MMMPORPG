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
	 * Methode permettant au joueur de choisir entre la génération d'un Evenement
	 * Aléatoire ou également de prendre du repos en utilisant une unité de
	 * feuDeCamp.
	 */
	public static void nextEvent(Game game) {
		Personnage personnage = game.getPersonnage();
		Scanner sc = new Scanner(System.in);
		int choix = 0;
		while (choix < 1 || choix > 3) {
			System.out.println(
					"Que voulez vous faire? 1 : Prendre du repos / 2 : Continuer votre avancée / 3 : Sauvegarder votre progression. ");
			choix = sc.nextInt();
			// Cette option est disponible pour se soigner entre les evenements aléatoires.
			// Utilise la
			// ressource feuDeCamp qui peut être supplémentée en tombant sur l'événement de
			// combat aléatoire Tréant.
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
				evenementAléatoire(game);
			} else if (choix == 3) {
				Game.Save(game);

			}
		}
	}

	/**
	 * Methode pour déterminer le prochain evenement que le joueur va rencontrer
	 * aléatoirement. Il y a pour le moment 3 possibilités : un combat aléatoire
	 * contre un Monstre, la rencontre avec un coach sportif qui augmente le niveau
	 * du joueur sans passer par une augmentation de son expérience et finalement
	 * une option de rencontre d'un apothicaire qui prodigue des soins au joueur
	 */
	public static void evenementAléatoire(Game game) {
		Personnage personnage = game.getPersonnage();
		double x = Math.random();
		if (x < 0.7) {
			Combat.CombatAléatoire(game);
		} else if (x >= 0.7 && x < 0.9) {
			System.out.println(
					"Jerome le coach vous trouve trop sec et vous force a faire 100 burpees. Toute cette activité vous permet de passer au niveau suivant.");
			Personnage.montéeNiveauCoach(game.getPersonnage());
		} else {
			System.out.println("Vous rencontrez un apothicaire qui accepte de vous prodiguer des soins.");
			Personnage.soinMedic(personnage);
		}
	}

}
