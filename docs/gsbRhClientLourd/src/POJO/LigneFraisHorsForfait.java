package POJO;

import java.sql.Date;

/**
 * Classe représentant une ligne de frais hors forfait associée à une fiche de frais.
 */
public class LigneFraisHorsForfait {
	int idLigneFHF;
	int idFiche;
	String libelle;
	Date dateFrais;
	float montant;

	/**
	 * Constructeur de la classe LigneFraisHorsForfait.
	 * @param idLigneFHF identifiant de la ligne hors forfait
	 * @param idFiche identifiant de la fiche de frais associée
	 * @param libelle libellé descriptif du frais
	 * @param dateFrais date à laquelle le frais a été engagé
	 * @param montant montant du frais hors forfait
	 */
	public LigneFraisHorsForfait(int idLigneFHF, int idFiche, String libelle, Date dateFrais, float montant) {
		super();
		this.idLigneFHF = idLigneFHF;
		this.idFiche = idFiche;
		this.libelle = libelle;
		this.dateFrais = dateFrais;
		this.montant = montant;
	}

	/**
	 * Retourne l'identifiant de la ligne hors forfait.
	 * @return {@code int} l'identifiant de la ligne
	 */
	public int getIdLigneFHF() {
		return idLigneFHF;
	}

	/**
	 * Modifie l'identifiant de la ligne hors forfait.
	 * @param idLigneFHF le nouvel identifiant de la ligne
	 */
	public void setIdLigneFHF(int idLigneFHF) {
		this.idLigneFHF = idLigneFHF;
	}

	/**
	 * Retourne l'identifiant de la fiche de frais associée.
	 * @return {@code int} l'identifiant de la fiche
	 */
	public int getIdFiche() {
		return idFiche;
	}

	/**
	 * Modifie l'identifiant de la fiche de frais associée.
	 * @param idFiche le nouvel identifiant de la fiche
	 */
	public void setIdFiche(int idFiche) {
		this.idFiche = idFiche;
	}

	/**
	 * Retourne le libellé du frais hors forfait.
	 * @return {@code String} le libellé du frais
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Modifie le libellé du frais hors forfait.
	 * @param libelle le nouveau libellé du frais
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Retourne la date à laquelle le frais a été engagé.
	 * @return {@code Date} la date du frais
	 */
	public Date getDateFrais() {
		return dateFrais;
	}

	/**
	 * Modifie la date à laquelle le frais a été engagé.
	 * @param dateFrais la nouvelle date du frais
	 */
	public void setDateFrais(Date dateFrais) {
		this.dateFrais = dateFrais;
	}

	/**
	 * Retourne le montant du frais hors forfait.
	 * @return {@code float} le montant du frais
	 */
	public float getMontant() {
		return montant;
	}

	/**
	 * Modifie le montant du frais hors forfait.
	 * @param montant le nouveau montant du frais
	 */
	public void setMontant(float montant) {
		this.montant = montant;
	}

	/**
	 * Retourne une représentation textuelle de l'objet LigneFraisHorsForfait.
	 * @return {@code String} la représentation textuelle de la ligne
	 */
	@Override
	public String toString() {
		return "LigneFraisHorsForfait [idLigneFHF=" + idLigneFHF + ", idFiche=" + idFiche + ", libelle=" + libelle
				+ ", dateFrais=" + dateFrais + ", montant=" + montant + "]";
	}
}