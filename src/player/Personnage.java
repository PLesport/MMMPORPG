package player;

import java.io.Serializable;

import game.Game;

public abstract class Personnage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -611607611896840532L;
	private String pseudonyme;
	private int niveau;
	private int pvMax;
	private int currentPv;
	private int currentXp;
	private int force;
	private int agilit�;
	private int intelligence;
	private double coeffNiveau;
	private final int forceParNiveau;
	private final int agilit�ParNiveau;
	private final int intelligenceParNiveau;
	private final int pvParNiveau;
	private int feuDeCamp;
	private int resistancePhysique;
	private int resistanceMagique;
	private int compteurVictoire;

	// Constructeur Personnage joueur
	public Personnage(String pseudonyme, int niveau, int pvmax, int force, int agilit�, int intelligence,
			int forceParNiveau, int agilit�ParNiveau, int intelligenceParNiveau, int pvParNiveau,
			int resistancePhysique, int resistanceMagique) {
		this.niveau = niveau;
		this.pseudonyme = pseudonyme;
		this.pvMax = pvmax;
		this.currentPv = pvmax;
		this.currentXp = 0;
		this.force = force;
		this.agilit� = agilit�;
		this.intelligence = intelligence;
		this.forceParNiveau = forceParNiveau;
		this.agilit�ParNiveau = agilit�ParNiveau;
		this.intelligenceParNiveau = intelligenceParNiveau;
		this.pvParNiveau = pvParNiveau;
		this.coeffNiveau = 1;
		this.feuDeCamp = 3;
		this.resistancePhysique = resistancePhysique;
		this.resistanceMagique = resistanceMagique;
		this.compteurVictoire = 0;
		// Essayer de d�river les stats force etc !
	}

	// Methode pour la v�rification du gain de niveau suite � une augmentation de
	// l'exp�rience
	public boolean checkNiveau() {
		coeffNiveau += (0.1 * niveau);
		return (currentXp >= (niveau * coeffNiveau) * 10);
	}

	// remise a niveau du compteur xp
	public void resetCompteurExp() {
		currentXp = 0;
	}

	// Methode pour l'application de la mont�e de niveau
	public void mont�eNiveau(Personnage personnage) {
		if (checkNiveau() == true) {
			resetCompteurExp();
			niveau += 1;
			// Augmentation des statistiques
			force += forceParNiveau;
			agilit� += agilit�ParNiveau;
			intelligence += intelligenceParNiveau;
			pvMax += ((pvMax * pvParNiveau) / 100);
			System.out.println("Chaussette ! Vous avez gagn� un niveau !");
			displayPlayer(personnage);
		}
	}

	public void mont�eNiveauSansCheck(Personnage personnage) {
		// Augmentation des statistiques
		force += forceParNiveau;
		agilit� += agilit�ParNiveau;
		intelligence += intelligenceParNiveau;
		pvMax += ((pvMax * pvParNiveau) / 100);
		System.out.println("Chaussette ! Vous avez gagn� un niveau !");
		displayPlayer(personnage);
		resetCompteurExp();
	}

	public void displayPlayer(Personnage personnage) {
		System.out.println("==========Joueur==========");
		System.out.println("Force :        " + getForce() + ".");
		System.out.println("Agilit� :      " + getAgilit�() + ".");
		System.out.println("Intelligence : " + getIntelligence() + ".");
		System.out.println("Pvs actuels :  " + getCurrentPv() + ".");
		System.out.println("==========================");
	}

	public void displayMob(Personnage personnage) {
		System.out.println("(|=========Enemi=========|)");
		System.out.println("Pvs actuels :  " + getCurrentPv() + ".");
		System.out.println("===========================");
	}

	public int reductionD�gatsPhysique(int d�gats) {
		d�gats = d�gats - (int) (d�gats * resistancePhysique) / 100;
		return d�gats;
	}

	public int reductionD�gatsMagique(int d�gats) {
		d�gats = d�gats - (int) (d�gats * resistanceMagique) / 100;
		return d�gats;
	}
	/**
	 * Methode Pour l'encaissement des d�gats de type Magique : Contient les
	 * instructions pour la r�duction des d�gats ainsi que l'application de la
	 * baisse des points de vie + texte d'ambiance.
	 */
	public static void encaissementD�gatsMagique(Personnage personnage, int d�gats) {
		personnage.setCurrentPv(personnage.getCurrentPv() - personnage.reductionD�gatsMagique(d�gats));

	}

	/**
	 * Methode Pour l'encaissement des d�gats de type Physique : Contient les
	 * instructions pour la r�duction des d�gats ainsi que l'application de la
	 * baisse des points de vie + texte d'ambiance.
	 */
	public static void encaissementD�gatsPhysique(Personnage personnage, int d�gats) {
		personnage.setCurrentPv(personnage.getCurrentPv() - personnage.reductionD�gatsPhysique(d�gats));

	}
	
	/**
	 * Cette Methode permet de g�rer la r�cup�ration des points de vie lors de
	 * l'apparition de l'evenement Medic. La r�cup�ration de spoints de vie est
	 * g�r�e par une d�termination de la valeur effective du soin (25% du maximum d
	 * epoints de vie) puis par application it�rative jusqu'au maximum de soin
	 * d�termin� ou jusqu'au maximum de points de vie.
	 */
	public static void soinMedic(Personnage personnage) {
		int soin = (int) (personnage.getPvMax() * 0.25);
		System.out.println("Vous r�cup�rez : " + soin + " points de vie.");
		for (int i = 0; i < soin; i++) {
			while (personnage.getCurrentPv() < personnage.getPvMax()) {
				personnage.setCurrentPv(personnage.getCurrentPv() + 1);
			}
		}
		personnage.displayPlayer(personnage);
	}
	// Methode mont�e de niveau Coach
	public static void mont�eNiveauCoach(Personnage personnage) {
		personnage.mont�eNiveauSansCheck(personnage);
	}
	public String getPseudonyme() {
		return pseudonyme;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public void upCompteurVictoire() {
		this.compteurVictoire++;
	}
	public int getCompteurVictoire() {
		return compteurVictoire;
	}

	public int getPvMax() {
		return pvMax;
	}

	public void setPvMax(int pvmax) {
		this.pvMax = pvmax;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getAgilit�() {
		return agilit�;
	}

	public void setAgilit�(int agilit�) {
		this.agilit� = agilit�;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public double getCoeff() {
		return coeffNiveau;
	}

	public void setCoeff(double newCoeff) {
		this.coeffNiveau = newCoeff;
	}

	public int getCurrentPv() {
		return currentPv;
	}

	public void setCurrentPv(int currentPv) {
		this.currentPv = currentPv;
	}

	public int getCurrentXp() {
		return currentXp;
	}

	public void setCurrentXp(int currentXp) {
		this.currentXp = currentXp;
	}

	public double getCoeffNiveau() {
		return coeffNiveau;
	}

	public void setCoeffNiveau(double coeffNiveau) {
		this.coeffNiveau = coeffNiveau;
	}

	public int getForceParNiveau() {
		return forceParNiveau;
	}

	public int getAgilit�ParNiveau() {
		return agilit�ParNiveau;
	}

	public int getIntelligenceParNiveau() {
		return intelligenceParNiveau;
	}

	public int getPvParNiveau() {
		return pvParNiveau;
	}

	public int getFeuDeCamp() {
		return feuDeCamp;
	}

	public void setFeuDeCamp(int feuDeCamp) {
		this.feuDeCamp = feuDeCamp;
	}

	public abstract int d�gatsMonstre();

	public int getResistancePhysique() {
		return resistancePhysique;
	}

	public void setResistancePhysique(int resistancePhysique) {
		this.resistancePhysique = resistancePhysique;
	}

	public int getResistanceMagique() {
		return resistanceMagique;
	}

	public void setResistanceMagique(int resistanceMagique) {
		this.resistanceMagique = resistanceMagique;
	}
}
