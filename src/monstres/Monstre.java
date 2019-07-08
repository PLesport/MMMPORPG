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

	public Monstre(String pseudonyme, int niveau, int pvmax, int force, int agilit�, int intelligence,
			int forceParNiveau, int agilit�ParNiveau, int intelligenceParNiveau, int pvParNiveau,
			int resistancePhysique, int resistanceMagique) {
		super(pseudonyme, niveau, pvmax, force, agilit�, intelligence, forceParNiveau, agilit�ParNiveau,
				intelligenceParNiveau, pvParNiveau, resistancePhysique, resistanceMagique);
	}

	public void mont�eNiveauMonstre() {
		// Mont�e de niveau
		setNiveau(getNiveau() + 1);
		// Augmentation des statistiques
		setForce(getForce() + getForceParNiveau());
		setAgilit�(getAgilit�() + getAgilit�ParNiveau());
		setIntelligence(getIntelligence() + getIntelligenceParNiveau());
		setPvMax(getPvMax() + ((getPvMax() * getPvParNiveau()) / 100));
	}

	// Combat d�gats monstre avec 5% chance de crit (5x damage) {
	public int d�gatsMonstre() {
		int degatsAttaque = (getForce() + getIntelligence() + MethodesUtiles.randInt(1, 6)) / 3;
		int x = MethodesUtiles.randInt(1, 20);
		if (x == 20) {
			System.out.println("Coup Critique !");
			degatsAttaque *= 5;
		}
		return degatsAttaque;
	}

	// Methode pour la d�termination du montant d'exp�rience obtenu apr�s la
	// victoire contre un enemi.
	public static int Exp(Monstre mob) {
		return ((mob.getPvMax() * 5) / 50);
	}

	// methode Rencontre al�atoire
	public static Monstre RencontreAleatoire(Game game) {
		// Bloc d�termination monstre al�atoire
		Personnage personnage = game.getPersonnage();
		int n = -1;
		Monstre mob = null;
		while (n <= 0) {
			n = MethodesUtiles.randInt((personnage.getNiveau() - 2), (personnage.getNiveau() + 2));
		}
		int x = MethodesUtiles.randInt(1, 6);
		switch (x) {
		case 1:
			mob = Lag.g�n�rationLagNiveau(n);
			System.out.println("Vous vous retrouvez face � face avec un Lag sauvage de niveau : " + n + " !");
			break;
		case 2:
			mob = GallinetteCendr�e.g�n�rationGallinetteCendr�eNiveau(n);
			System.out.println(
					"Vous vous retrouvez face � face avec une vicieuse Gallinette Cendr�e de niveau : " + n + "!");
			break;
		case 3:
			mob = Ogre.g�n�rationOgreNiveau(n);
			System.out.println("Vous vous retrouvez face � face avec un f�roce Ogre de niveau : " + n + "!");
			break;
		case 4:
			mob = Treant.g�n�rationTreantNiveau(n);
			personnage.setFeuDeCamp(personnage.getFeuDeCamp() + 1);
			System.out.println("Vous ramassez du bois sec sur le chemin quand soudainement : ");
			System.out.println("Vous vous retrouvez face � face avec un Treant de niveau : " + n + "!");
			break;
		case 5:
			mob = Dragon.g�n�rationDragonNiveau(n);
			System.out.println("Vous vous retrouvez face � face avec un Dragon de niveau : " + n + "!");
			break;
		case 6:
			mob = Golem.g�n�rationGolemNiveau(n);
			System.out.println("Vous vous retrouvez face � face avec un Dragon de niveau : " + n + "!");
			break;
		default:
			break;
		}
		return mob;
	}
}
