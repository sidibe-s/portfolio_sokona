package POJO;

/**
 * Classe représentant un type de frais forfaitaire.
 */
public class FraisForfait {
	int idFraisForfait;
	String libelle;
	float montant;

	/**
	 * Constructeur de la classe FraisForfait.
	 * @param idFraisForfait identifiant du frais forfaitaire
	 * @param libelle libellé du frais forfaitaire
	 * @param montant montant du frais forfaitaire
	 */
	public FraisForfait(int idFraisForfait, String libelle, float montant) {
		super();
		this.idFraisForfait = idFraisForfait;
		this.libelle = libelle;
		this.montant = montant;
	}

	/**
	 * Retourne l'identifiant du frais forfaitaire.
	 * @return {@code int} l'identifiant du frais forfaitaire
	 */
	public int getIdFraisForfait() {
		return idFraisForfait;
	}

	/**
	 * Modifie l'identifiant du frais forfaitaire.
	 * @param idFraisForfait le nouvel identifiant du frais forfaitaire
	 */
	public void setIdFraisForfait(int idFraisForfait) {
		this.idFraisForfait = idFraisForfait;
	}

	/**
	 * Retourne le libellé du frais forfaitaire.
	 * @return {@code String} le libellé du frais forfaitaire
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Modifie le libellé du frais forfaitaire.
	 * @param libelle le nouveau libellé du frais forfaitaire
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Retourne le montant du frais forfaitaire.
	 * @return {@code float} le montant du frais forfaitaire
	 */
	public float getMontant() {
		return montant;
	}

	/**
	 * Modifie le montant du frais forfaitaire.
	 * @param montant le nouveau montant du frais forfaitaire
	 */
	public void setMontant(float montant) {
		this.montant = montant;
	}

	/**
	 * Retourne une représentation textuelle de l'objet FraisForfait.
	 * @return {@code String} la représentation textuelle du frais forfaitaire
	 */
	@Override
	public String toString() {
		return "FraisForfait [idFraisForfait=" + idFraisForfait + ", libelle=" + libelle + ", montant=" + montant + "]";
	}
}