package events;

import java.io.Serializable;
import java.util.Scanner;

import basic.MethodesUtiles;
import game.Game;
import monstres.Dragon;
import monstres.GallinetteCendrée;
import monstres.Golem;
import monstres.Lag;
import monstres.Monstre;
import monstres.Ogre;
import monstres.Treant;
import player.Personnage;

public class Combat extends Evenement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7185338179235179201L;

	/**
	 * Déroulement du combat dans son ensemble. C'est une suite d'attaques croisées
	 * entre le personnage et son adversaire qui est généré au début de la
	 * rencontre. A chaque tour, le joueur peut utiliser 3 actions, deux d'attaque
	 * (physique ou magique) et une option de fuite qui termine ce combat.
	 */
	public static void CombatAléatoire(Game game) {
		Personnage personnage = game.getPersonnage();
		Monstre mob = Monstre.RencontreAleatoire(game);

		while (mob.getCurrentPv() > 0 & personnage.getCurrentPv() > 0) {
			Scanner sc = new Scanner(System.in);
			int choix = 0;
			while (choix < 1 || choix > 3) {
				System.out.println();
				System.out.println(
						"Quelle action voulez vous réaliser? 1 : Attaque Physique / 2 : Attaque Magique / 3 : Fuir ");
				choix = sc.nextInt();
			}
			if (choix == 1) {
				deroulementAttaquePhysique(personnage, mob);
			}
			if (choix == 2) {
				deroulementAttaqueMagique(personnage, mob);
			}
			if (choix == 3) {
				System.out.println("Vous fuyez ce combat !");
				break;
				
			}
		}
		// Attribution de l'expérience suite à une victoire du personnage joueur.
		if (mob.getCurrentPv() <= 0) {
			System.out.println("Vous avez terrassé votre enemi.");
			System.out.println("Vous gagnez " + Monstre.Exp(mob) + " points d'expérience.");
			personnage.setCurrentXp(personnage.getCurrentXp() + Monstre.Exp(mob));
			personnage.montéeNiveau(personnage);
			personnage.upCompteurVictoire();
		}
		// Message de fin de partie suite à la mort d'un personnage joueur + fin
		// d'éxécution du code(GameOver).
		if (personnage.getCurrentPv() <= 0) {
			System.out.println("Vous succombez sous les coups de votre enemi. GAME OVER");
			System.exit(0);
		}
	}

	/**
	 * Cette Methode permet de déterminer les dégats d'une attaque donnée
	 * dépendamment de son type. Les différences sont gérées par la demande d'un
	 * argument supplémentaire(x) qui correspond a la valeur de la statistique
	 * correspondante au type d'attaque.
	 */
	public static int degatsAttaque(Personnage personnage, int x) {
		int degatsAttaque = x + MethodesUtiles.randInt(1, 12);
		if (MethodesUtiles.randInt(1, 20) >= 19) {
			System.out.println("Coup Critique !");
			degatsAttaque *= 5;
		}
		return degatsAttaque;
	}

	/**
	 * Methode pour calculer la réussite d'une attaque Physique.
	 */
	public static boolean RéussiteAttaquePhysique(Personnage personnage, Personnage personnage2, double x) {
		if (personnage2 instanceof Golem) {
			System.out.println("Le corps en métal du golem ne souffre pas des attaques physiques !");
			return false;
		}
		return (x + (personnage.getNiveau() - personnage2.getNiveau()) * 0.1
				+ (personnage.getAgilité() - personnage2.getAgilité()) * 0.05 + Math.random() >= 1);
	}

	/**
	 * Methode pour calculer la réussite d'une attaque Magique.
	 */
	public static boolean RéussiteAttaqueMagique(Personnage personnage, Personnage personnage2, double x) {
		if (personnage2 instanceof Dragon) {
			System.out.println("La magie n'a aucun effet sur les dragons!");
			return false;
		}
		return (x + (personnage.getNiveau() - personnage2.getNiveau()) * 0.1
				+ (personnage.getAgilité() - personnage2.getAgilité()) * 0.05 + Math.random() >= 1);
	}

	/**
	 * Methode Deroulement Attaque Physique : Contient les instructions pour la
	 * vérification des attaques, de leurs dégats et leur application + texte
	 * d'ambiance.
	 */
	public static void deroulementAttaquePhysique(Personnage personnage, Personnage mob) {
		if (RéussiteAttaquePhysique(personnage, mob, 0.7) == true) {
			int z = degatsAttaque(personnage, personnage.getForce());
			Personnage.encaissementDégatsPhysique(mob, z);
			System.out.println("Vous infligez " + z + " points de dégats à votre adversaire.");
			mob.displayMob(mob);
		} else
			System.out.println("Vous ratez votre attaque.");
		if (RéussiteAttaquePhysique(mob, personnage, 0.7) == true) {
			int y = mob.dégatsMonstre();
			Personnage.encaissementDégatsPhysique(personnage, y);
			System.out.println("Votre adversaire riposte et vous inflige : " + y + " points de dégats.");
			personnage.displayPlayer(personnage);
		} else
			System.out.println("Votre enemi rate son attaque.");
	}

	/**
	 * Methode Deroulement Attaque Magique : Contient les instructions pour la
	 * vérification des attaques, de leurs dégats et leur application + texte
	 * d'ambiance.
	 */
	public static void deroulementAttaqueMagique(Personnage personnage, Personnage mob) {
		if (RéussiteAttaqueMagique(personnage, mob, 0.9) == true) {
			int z = degatsAttaque(personnage, personnage.getIntelligence());
			Personnage.encaissementDégatsMagique(mob, z);
			System.out.println("Vous infligez " + z + " points de dégats à votre adversaire.");
			mob.displayMob(mob);
		} else
			System.out.println("Votre magie n'a aucun effet sur cet enemi.");
		if (RéussiteAttaquePhysique(mob, personnage, 0.7) == true) {
			int y = mob.dégatsMonstre();
			Personnage.encaissementDégatsPhysique(personnage, y);
			System.out.println("Votre adversaire riposte et vous inflige : " + y + " points de dégats.");
			personnage.displayPlayer(personnage);
		} else
			System.out.println("Votre enemi rate son attaque.");
	}

}
