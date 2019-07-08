package monstres;

import java.io.Serializable;

import basic.MethodesUtiles;
import game.Game;
import player.Personnage;

public abstract class Monstre extends Personnage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6065120793299880510L;

	public Monstre(String pseudonyme, int niveau, int pvmax, int force, int agilité, int intelligence,
			int forceParNiveau, int agilitéParNiveau, int intelligenceParNiveau, int pvParNiveau,
			int resistancePhysique, int resistanceMagique) {
		super(pseudonyme, niveau, pvmax, force, agilité, intelligence, forceParNiveau, agilitéParNiveau,
				intelligenceParNiveau, pvParNiveau, resistancePhysique, resistanceMagique);
	}

	public void montéeNiveauMonstre() {
		// Montée de niveau
		setNiveau(getNiveau() + 1);
		// Augmentation des statistiques
		setForce(getForce() + getForceParNiveau());
		setAgilité(getAgilité() + getAgilitéParNiveau());
		setIntelligence(getIntelligence() + getIntelligenceParNiveau());
		setPvMax(getPvMax() + ((getPvMax() * getPvParNiveau()) / 100));
	}

	// Combat dégats monstre avec 5% chance de crit (5x damage) {
	public int dégatsMonstre() {
		int degatsAttaque = (getForce() + getIntelligence() + MethodesUtiles.randInt(1, 6)) / 3;
		int x = MethodesUtiles.randInt(1, 20);
		if (x == 20) {
			System.out.println("Coup Critique !");
			degatsAttaque *= 5;
		}
		return degatsAttaque;
	}

	// Methode pour la détermination du montant d'expérience obtenu après la
	// victoire contre un enemi.
	public static int Exp(Monstre mob) {
		return ((mob.getPvMax() * 5) / 50);
	}

	// methode Rencontre aléatoire
	public static Monstre RencontreAleatoire(Game game) {
		// Bloc détermination monstre aléatoire
		Personnage personnage = game.getPersonnage();
		int n = -1;
		Monstre mob = null;
		while (n <= 0) {
			n = MethodesUtiles.randInt((personnage.getNiveau() - 2), (personnage.getNiveau() + 2));
		}
		int x = MethodesUtiles.randInt(1, 6);
		switch (x) {
		case 1:
			mob = Lag.générationLagNiveau(n);
			System.out.println("Vous vous retrouvez face à face avec un Lag sauvage de niveau : " + n + " !");
			break;
		case 2:
			mob = GallinetteCendrée.générationGallinetteCendréeNiveau(n);
			System.out.println(
					"Vous vous retrouvez face à face avec une vicieuse Gallinette Cendrée de niveau : " + n + "!");
			break;
		case 3:
			mob = Ogre.générationOgreNiveau(n);
			System.out.println("Vous vous retrouvez face à face avec un féroce Ogre de niveau : " + n + "!");
			break;
		case 4:
			mob = Treant.générationTreantNiveau(n);
			personnage.setFeuDeCamp(personnage.getFeuDeCamp() + 1);
			System.out.println("Vous ramassez du bois sec sur le chemin quand soudainement : ");
			System.out.println("Vous vous retrouvez face à face avec un Treant de niveau : " + n + "!");
			break;
		case 5:
			mob = Dragon.générationDragonNiveau(n);
			System.out.println("Vous vous retrouvez face à face avec un Dragon de niveau : " + n + "!");
			break;
		case 6:
			mob = Golem.générationGolemNiveau(n);
			System.out.println("Vous vous retrouvez face à face avec un Dragon de niveau : " + n + "!");
			break;
		default:
			break;
		}
		return mob;
	}
}
