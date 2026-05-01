package POJO;

import java.sql.Date;

/**
 * Classe représentant une fiche de frais d'un visiteur médical.
 */
public class FicheFrais {
	int idFiche;
	String idUtilisateur;
	int annee;
	int mois;
	int nbJustificatifs;
	float montantValide;
	Date dateModif;
	String idEtat;

	/**
	 * Constructeur de la classe FicheFrais.
	 * @param idFiche identifiant de la fiche de frais
	 * @param idUtilisateur identifiant de l'utilisateur associé
	 * @param annee année de la fiche de frais
	 * @param mois mois de la fiche de frais
	 * @param nbJustificatifs nombre de justificatifs fournis
	 * @param montantValide montant validé de la fiche
	 * @param dateModif date de dernière modification
	 * @param idEtat identifiant de l'état de la fiche
	 */
	public FicheFrais(int idFiche, String idUtilisateur, int annee, int mois, int nbJustificatifs, float montantValide,
			Date dateModif, String idEtat) {
		super();
		this.idFiche = idFiche;
		this.idUtilisateur = idUtilisateur;
		this.annee = annee;
		this.mois = mois;
		this.nbJustificatifs = nbJustificatifs;
		this.montantValide = montantValide;
		this.dateModif = dateModif;
		this.idEtat = idEtat;
	}

	/**
	 * Retourne l'identifiant de la fiche de frais.
	 * @return {@code int} l'identifiant de la fiche
	 */
	public int getIdFiche() {
		return idFiche;
	}

	/**
	 * Modifie l'identifiant de la fiche de frais.
	 * @param idFiche le nouvel identifiant de la fiche
	 */
	public void setIdFiche(int idFiche) {
		this.idFiche = idFiche;
	}

	/**
	 * Retourne l'identifiant de l'utilisateur associé à la fiche.
	 * @return {@code String} l'identifiant de l'utilisateur
	 */
	public String getIdUtilisateur() {
		return idUtilisateur;
	}

	/**
	 * Modifie l'identifiant de l'utilisateur associé à la fiche.
	 * @param idUtilisateur le nouvel identifiant de l'utilisateur
	 */
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	/**
	 * Retourne l'année de la fiche de frais.
	 * @return {@code int} l'année de la fiche
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * Modifie l'année de la fiche de frais.
	 * @param annee la nouvelle année de la fiche
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	/**
	 * Retourne le mois de la fiche de frais.
	 * @return {@code int} le mois de la fiche
	 */
	public int getMois() {
		return mois;
	}

	/**
	 * Modifie le mois de la fiche de frais.
	 * @param mois le nouveau mois de la fiche
	 */
	public void setMois(int mois) {
		this.mois = mois;
	}

	/**
	 * Retourne le nombre de justificatifs de la fiche.
	 * @return {@code int} le nombre de justificatifs
	 */
	public int getNbJustificatifs() {
		return nbJustificatifs;
	}

	/**
	 * Modifie le nombre de justificatifs de la fiche.
	 * @param nbJustificatifs le nouveau nombre de justificatifs
	 */
	public void setNbJustificatifs(int nbJustificatifs) {
		this.nbJustificatifs = nbJustificatifs;
	}

	/**
	 * Retourne le montant validé de la fiche de frais.
	 * @return {@code float} le montant validé
	 */
	public float getMontantValide() {
		return montantValide;
	}

	/**
	 * Modifie le montant validé de la fiche de frais.
	 * @param montantValide le nouveau montant validé
	 */
	public void setMontantValide(float montantValide) {
		this.montantValide = montantValide;
	}

	/**
	 * Retourne la date de dernière modification de la fiche.
	 * @return {@code Date} la date de modification
	 */
	public Date getDateModif() {
		return dateModif;
	}

	/**
	 * Modifie la date de dernière modification de la fiche.
	 * @param dateModif la nouvelle date de modification
	 */
	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	/**
	 * Retourne l'identifiant de l'état de la fiche.
	 * @return {@code String} l'identifiant de l'état
	 */
	public String getIdEtat() {
		return idEtat;
	}

	/**
	 * Modifie l'identifiant de l'état de la fiche.
	 * @param idEtat le nouvel identifiant de l'état
	 */
	public void setIdEtat(String idEtat) {
		this.idEtat = idEtat;
	}

	/**
	 * Retourne une représentation textuelle de l'objet FicheFrais.
	 * @return {@code String} la représentation textuelle de la fiche
	 */
	@Override
	public String toString() {
		return "FicheFrais [idFiche=" + idFiche + ", idUtilisateur=" + idUtilisateur + ", annee=" + annee + ", mois="
				+ mois + ", nbJustificatifs=" + nbJustificatifs + ", montantValide=" + montantValide + ", dateModif="
				+ dateModif + ", idEtat=" + idEtat + "]";
	}
}