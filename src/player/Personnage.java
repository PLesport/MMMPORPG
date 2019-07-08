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
	private int agilité;
	private int intelligence;
	private double coeffNiveau;
	private final int forceParNiveau;
	private final int agilitéParNiveau;
	private final int intelligenceParNiveau;
	private final int pvParNiveau;
	private int feuDeCamp;
	private int resistancePhysique;
	private int resistanceMagique;
	private int compteurVictoire;

	// Constructeur Personnage joueur
	public Personnage(String pseudonyme, int niveau, int pvmax, int force, int agilité, int intelligence,
			int forceParNiveau, int agilitéParNiveau, int intelligenceParNiveau, int pvParNiveau,
			int resistancePhysique, int resistanceMagique) {
		this.niveau = niveau;
		this.pseudonyme = pseudonyme;
		this.pvMax = pvmax;
		this.currentPv = pvmax;
		this.currentXp = 0;
		this.force = force;
		this.agilité = agilité;
		this.intelligence = intelligence;
		this.forceParNiveau = forceParNiveau;
		this.agilitéParNiveau = agilitéParNiveau;
		this.intelligenceParNiveau = intelligenceParNiveau;
		this.pvParNiveau = pvParNiveau;
		this.coeffNiveau = 1;
		this.feuDeCamp = 3;
		this.resistancePhysique = resistancePhysique;
		this.resistanceMagique = resistanceMagique;
		this.compteurVictoire = 0;
		// Essayer de dériver les stats force etc !
	}

	// Methode pour la vérification du gain de niveau suite à une augmentation de
	// l'expérience
	public boolean checkNiveau() {
		coeffNiveau += (0.1 * niveau);
		return (currentXp >= (niveau * coeffNiveau) * 10);
	}

	// remise a niveau du compteur xp
	public void resetCompteurExp() {
		currentXp = 0;
	}

	// Methode pour l'application de la montée de niveau
	public void montéeNiveau(Personnage personnage) {
		if (checkNiveau() == true) {
			resetCompteurExp();
			niveau += 1;
			// Augmentation des statistiques
			force += forceParNiveau;
			agilité += agilitéParNiveau;
			intelligence += intelligenceParNiveau;
			pvMax += ((pvMax * pvParNiveau) / 100);
			System.out.println("Chaussette ! Vous avez gagné un niveau !");
			displayPlayer(personnage);
		}
	}

	public void montéeNiveauSansCheck(Personnage personnage) {
		// Augmentation des statistiques
		force += forceParNiveau;
		agilité += agilitéParNiveau;
		intelligence += intelligenceParNiveau;
		pvMax += ((pvMax * pvParNiveau) / 100);
		System.out.println("Chaussette ! Vous avez gagné un niveau !");
		displayPlayer(personnage);
		resetCompteurExp();
	}

	public void displayPlayer(Personnage personnage) {
		System.out.println("==========Joueur==========");
		System.out.println("Force :        " + getForce() + ".");
		System.out.println("Agilité :      " + getAgilité() + ".");
		System.out.println("Intelligence : " + getIntelligence() + ".");
		System.out.println("Pvs actuels :  " + getCurrentPv() + ".");
		System.out.println("==========================");
	}

	public void displayMob(Personnage personnage) {
		System.out.println("(|=========Enemi=========|)");
		System.out.println("Pvs actuels :  " + getCurrentPv() + ".");
		System.out.println("===========================");
	}

	public int reductionDégatsPhysique(int dégats) {
		dégats = dégats - (int) (dégats * resistancePhysique) / 100;
		return dégats;
	}

	public int reductionDégatsMagique(int dégats) {
		dégats = dégats - (int) (dégats * resistanceMagique) / 100;
		return dégats;
	}
	/**
	 * Methode Pour l'encaissement des dégats de type Magique : Contient les
	 * instructions pour la réduction des dégats ainsi que l'application de la
	 * baisse des points de vie + texte d'ambiance.
	 */
	public static void encaissementDégatsMagique(Personnage personnage, int dégats) {
		personnage.setCurrentPv(personnage.getCurrentPv() - personnage.reductionDégatsMagique(dégats));

	}

	/**
	 * Methode Pour l'encaissement des dégats de type Physique : Contient les
	 * instructions pour la réduction des dégats ainsi que l'application de la
	 * baisse des points de vie + texte d'ambiance.
	 */
	public static void encaissementDégatsPhysique(Personnage personnage, int dégats) {
		personnage.setCurrentPv(personnage.getCurrentPv() - personnage.reductionDégatsPhysique(dégats));

	}
	
	/**
	 * Cette Methode permet de gérer la récupération des points de vie lors de
	 * l'apparition de l'evenement Medic. La récupération de spoints de vie est
	 * gérée par une détermination de la valeur effective du soin (25% du maximum d
	 * epoints de vie) puis par application itérative jusqu'au maximum de soin
	 * déterminé ou jusqu'au maximum de points de vie.
	 */
	public static void soinMedic(Personnage personnage) {
		int soin = (int) (personnage.getPvMax() * 0.25);
		System.out.println("Vous récupérez : " + soin + " points de vie.");
		for (int i = 0; i < soin; i++) {
			while (personnage.getCurrentPv() < personnage.getPvMax()) {
				personnage.setCurrentPv(personnage.getCurrentPv() + 1);
			}
		}
		personnage.displayPlayer(personnage);
	}
	// Methode montée de niveau Coach
	public static void montéeNiveauCoach(Personnage personnage) {
		personnage.montéeNiveauSansCheck(personnage);
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

	public int getAgilité() {
		return agilité;
	}

	public void setAgilité(int agilité) {
		this.agilité = agilité;
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

	public int getAgilitéParNiveau() {
		return agilitéParNiveau;
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

	public abstract int dégatsMonstre();

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
