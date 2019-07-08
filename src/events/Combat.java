package events;

import java.io.Serializable;
import java.util.Scanner;

import basic.MethodesUtiles;
import game.Game;
import monstres.Dragon;
import monstres.GallinetteCendr�e;
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
	 * D�roulement du combat dans son ensemble. C'est une suite d'attaques crois�es
	 * entre le personnage et son adversaire qui est g�n�r� au d�but de la
	 * rencontre. A chaque tour, le joueur peut utiliser 3 actions, deux d'attaque
	 * (physique ou magique) et une option de fuite qui termine ce combat.
	 */
	public static void CombatAl�atoire(Game game) {
		Personnage personnage = game.getPersonnage();
		Monstre mob = Monstre.RencontreAleatoire(game);

		while (mob.getCurrentPv() > 0 & personnage.getCurrentPv() > 0) {
			Scanner sc = new Scanner(System.in);
			int choix = 0;
			while (choix < 1 || choix > 3) {
				System.out.println();
				System.out.println(
						"Quelle action voulez vous r�aliser? 1 : Attaque Physique / 2 : Attaque Magique / 3 : Fuir ");
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
		// Attribution de l'exp�rience suite � une victoire du personnage joueur.
		if (mob.getCurrentPv() <= 0) {
			System.out.println("Vous avez terrass� votre enemi.");
			System.out.println("Vous gagnez " + Monstre.Exp(mob) + " points d'exp�rience.");
			personnage.setCurrentXp(personnage.getCurrentXp() + Monstre.Exp(mob));
			personnage.mont�eNiveau(personnage);
			personnage.upCompteurVictoire();
		}
		// Message de fin de partie suite � la mort d'un personnage joueur + fin
		// d'�x�cution du code(GameOver).
		if (personnage.getCurrentPv() <= 0) {
			System.out.println("Vous succombez sous les coups de votre enemi. GAME OVER");
			System.exit(0);
		}
	}

	/**
	 * Cette Methode permet de d�terminer les d�gats d'une attaque donn�e
	 * d�pendamment de son type. Les diff�rences sont g�r�es par la demande d'un
	 * argument suppl�mentaire(x) qui correspond a la valeur de la statistique
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
	 * Methode pour calculer la r�ussite d'une attaque Physique.
	 */
	public static boolean R�ussiteAttaquePhysique(Personnage personnage, Personnage personnage2, double x) {
		if (personnage2 instanceof Golem) {
			System.out.println("Le corps en m�tal du golem ne souffre pas des attaques physiques !");
			return false;
		}
		return (x + (personnage.getNiveau() - personnage2.getNiveau()) * 0.1
				+ (personnage.getAgilit�() - personnage2.getAgilit�()) * 0.05 + Math.random() >= 1);
	}

	/**
	 * Methode pour calculer la r�ussite d'une attaque Magique.
	 */
	public static boolean R�ussiteAttaqueMagique(Personnage personnage, Personnage personnage2, double x) {
		if (personnage2 instanceof Dragon) {
			System.out.println("La magie n'a aucun effet sur les dragons!");
			return false;
		}
		return (x + (personnage.getNiveau() - personnage2.getNiveau()) * 0.1
				+ (personnage.getAgilit�() - personnage2.getAgilit�()) * 0.05 + Math.random() >= 1);
	}

	/**
	 * Methode Deroulement Attaque Physique : Contient les instructions pour la
	 * v�rification des attaques, de leurs d�gats et leur application + texte
	 * d'ambiance.
	 */
	public static void deroulementAttaquePhysique(Personnage personnage, Personnage mob) {
		if (R�ussiteAttaquePhysique(personnage, mob, 0.7) == true) {
			int z = degatsAttaque(personnage, personnage.getForce());
			Personnage.encaissementD�gatsPhysique(mob, z);
			System.out.println("Vous infligez " + z + " points de d�gats � votre adversaire.");
			mob.displayMob(mob);
		} else
			System.out.println("Vous ratez votre attaque.");
		if (R�ussiteAttaquePhysique(mob, personnage, 0.7) == true) {
			int y = mob.d�gatsMonstre();
			Personnage.encaissementD�gatsPhysique(personnage, y);
			System.out.println("Votre adversaire riposte et vous inflige : " + y + " points de d�gats.");
			personnage.displayPlayer(personnage);
		} else
			System.out.println("Votre enemi rate son attaque.");
	}

	/**
	 * Methode Deroulement Attaque Magique : Contient les instructions pour la
	 * v�rification des attaques, de leurs d�gats et leur application + texte
	 * d'ambiance.
	 */
	public static void deroulementAttaqueMagique(Personnage personnage, Personnage mob) {
		if (R�ussiteAttaqueMagique(personnage, mob, 0.9) == true) {
			int z = degatsAttaque(personnage, personnage.getIntelligence());
			Personnage.encaissementD�gatsMagique(mob, z);
			System.out.println("Vous infligez " + z + " points de d�gats � votre adversaire.");
			mob.displayMob(mob);
		} else
			System.out.println("Votre magie n'a aucun effet sur cet enemi.");
		if (R�ussiteAttaquePhysique(mob, personnage, 0.7) == true) {
			int y = mob.d�gatsMonstre();
			Personnage.encaissementD�gatsPhysique(personnage, y);
			System.out.println("Votre adversaire riposte et vous inflige : " + y + " points de d�gats.");
			personnage.displayPlayer(personnage);
		} else
			System.out.println("Votre enemi rate son attaque.");
	}

}
