package POJO;

/**
 * Classe représentant une région.
 */
public class Region {
	int idRegion;
	String libelleRegion;

	/**
	 * Constructeur de la classe Region.
	 * @param idRegion identifiant de la région
	 * @param libelleRegion libellé de la région
	 */
	public Region(int idRegion, String libelleRegion) {
		super();
		this.idRegion = idRegion;
		this.libelleRegion = libelleRegion;
	}

	/**
	 * Retourne l'identifiant de la région.
	 * @return {@code int} l'identifiant de la région
	 */
	public int getIdRegion() {
		return idRegion;
	}

	/**
	 * Modifie l'identifiant de la région.
	 * @param idRegion le nouvel identifiant de la région
	 */
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	/**
	 * Retourne le libellé de la région.
	 * @return {@code String} le libellé de la région
	 */
	public String getLibelleRegion() {
		return libelleRegion;
	}

	/**
	 * Modifie le libellé de la région.
	 * @param libelleRegion le nouveau libellé de la région
	 */
	public void setLibelleRegion(String libelleRegion) {
		this.libelleRegion = libelleRegion;
	}

	/**
	 * Retourne une représentation textuelle de l'objet Region.
	 * @return {@code String} la représentation textuelle de la région
	 */
	@Override
	public String toString() {
		return "\nRole : \nidRegion : " + idRegion + "\nlibelleRegion : " + libelleRegion;
	}
}