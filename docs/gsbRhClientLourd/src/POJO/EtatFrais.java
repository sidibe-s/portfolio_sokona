package POJO;

/**
 * Classe représentant l'état d'une fiche de frais.
 */
public class EtatFrais {
	String idEtat;
	String libelleEtat;

	/**
	 * Constructeur de classe EtatFrais.
	 * @param idEtat identifiant de l'état (ex: "RB", "CR")
	 * @param libelleEtat libellé de l'état (ex: "Remboursé", "Créé")
	 */
	public EtatFrais(String idEtat, String libelleEtat) {
		super();
		this.idEtat = idEtat;
		this.libelleEtat = libelleEtat;
	}

	/**
	 * Retourne l'identifiant de l'état.
	 * @return {@code String} l'identifiant de l'état
	 */
	public String getIdEtat() {
		return idEtat;
	}

	/**
	 * Modifie l'identifiant de l'état.
	 * @param idEtat le nouvel identifiant de l'état
	 */
	public void setIdEtat(String idEtat) {
		this.idEtat = idEtat;
	}

	/**
	 * Retourne le libellé de l'état.
	 * @return {@code String} le libellé de l'état
	 */
	public String getLibelleEtat() {
		return libelleEtat;
	}

	/**
	 * Modifie le libellé de l'état.
	 * @param libelleEtat le nouveau libellé de l'état
	 */
	public void setLibelleEtat(String libelleEtat) {
		this.libelleEtat = libelleEtat;
	}

	/**
	 * Retourne une représentation textuelle de l'objet EtatFrais.
	 * @return {@code String} la représentation textuelle de l'état
	 */
	@Override
	public String toString() {
		return "EtatFrais [idEtat=" + idEtat + ", libelleEtat=" + libelleEtat + "]";
	}
}