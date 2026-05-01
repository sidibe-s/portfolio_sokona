package POJO;

/**
 * Classe représentant une ligne de frais forfaitaire associée à une fiche de frais.
 */
public class LigneFraisForfait {
	int idFiche;
	String idFraisForfait;
	int quantite;

	/**
	 * Constructeur de la classe LigneFraisForfait.
	 * @param idFiche identifiant de la fiche de frais associée
	 * @param idFraisForfait identifiant du type de frais forfaitaire
	 * @param quantite quantité du frais forfaitaire
	 */
	public LigneFraisForfait(int idFiche, String idFraisForfait, int quantite) {
		super();
		this.idFiche = idFiche;
		this.idFraisForfait = idFraisForfait;
		this.quantite = quantite;
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
	 * Retourne l'identifiant du type de frais forfaitaire.
	 * @return {@code String} l'identifiant du frais forfaitaire
	 */
	public String getIdFraisForfait() {
		return idFraisForfait;
	}

	/**
	 * Modifie l'identifiant du type de frais forfaitaire.
	 * @param idFraisForfait le nouvel identifiant du frais forfaitaire
	 */
	public void setIdFraisForfait(String idFraisForfait) {
		this.idFraisForfait = idFraisForfait;
	}

	/**
	 * Retourne la quantité du frais forfaitaire.
	 * @return {@code int} la quantité
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * Modifie la quantité du frais forfaitaire.
	 * @param quantite la nouvelle quantité
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/**
	 * Retourne une représentation textuelle de l'objet LigneFraisForfait.
	 * @return {@code String} la représentation textuelle de la ligne
	 */
	@Override
	public String toString() {
		return "LigneFraisForfait [idFiche=" + idFiche + ", idFraisForfait=" + idFraisForfait + ", quantite=" + quantite
				+ "]";
	}
}