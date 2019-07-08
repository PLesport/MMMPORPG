package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

import game.Game;
import player.Guerrier;
import player.Mage;
import player.Personnage;
import player.Voleur;

public class Main implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1997718785131895230L;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/**
		 * Possibilité de choix de commencer avec un fichier de sauvegarde ou a partir
		 * d'un nouveau personnage.
		 * 
		 */
		System.out.println("Voulez vous charger une sauvegarde d'une partie précédente? 1 : Oui / 2 : Non ");
		int choix = 0;
		while (choix < 1 || choix > 2) {
			choix = sc.nextInt();
			if (choix == 1) {
				try {
					FileInputStream fileIn = new FileInputStream("Game.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					Game game = (Game) in.readObject();
					in.close();
					fileIn.close();
					System.out.println(game.getNombreDeTours());
					Game.Partie(game.getPersonnage(), game, game.getNombreDeTours());
				} catch (IOException i) {
					i.printStackTrace();
					return;
				} catch (ClassNotFoundException c) {
					System.out.println("Ce fichier ne correspond pas a une sauvagarde valable.");
					c.printStackTrace();
					return;
				}
			}

			else if (choix == 2) {
				/**
				 * Choix préalables pour la création du personnage joueur ainsi que la
				 * détermination de la taille du donjon.
				 */
				System.out.println("Pour cette partie , nous allons devoir créér un nouveau personnage.");
				System.out.println("Comment voulez vous nommer votre personnage? : ");

				String nom = sc.next();
				Personnage player = null;
				choix = 0;
				while (choix < 1 || choix > 3) {
					System.out.println();
					System.out.println(
							"Veuillez choisir la classe de votre personnage. 1 : Guerrier / 2 : Voleur / 3 : Mage");
					choix = sc.nextInt();
				}
				if (choix == 1) {
					player = new Guerrier(nom);
					System.out.println("Vous incarnerez donc " + nom + " le guerrier.");
				}
				if (choix == 2) {
					player = new Voleur(nom);
					System.out.println("Vous incarnerez donc " + nom + " le voleur.");
				}
				if (choix == 3) {
					player = new Mage(nom);
					System.out.println("Vous incarnerez donc " + nom + " le mage.");
				}

				System.out.println("Déterminez la taille de votre donjon (valeur numérique entière): ");
				int tailleDonjon = sc.nextInt();
				// Lancement partie
				Game game = new Game(player, tailleDonjon);
				Game.Partie(player, game, tailleDonjon);
				sc.close();

			}

			else
				System.out.println("Veuillez entrer un choix valide. Merci.");
		}
	}
}