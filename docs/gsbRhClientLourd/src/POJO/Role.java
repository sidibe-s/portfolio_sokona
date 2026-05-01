package POJO;

/**
 * Classe représentant le rôle d'un utilisateur.
 */
public class Role {
	String idRole;
	String libelleRole;

	/**
	 * Constructeur de la classe Role.
	 * @param idRole identifiant du rôle
	 * @param libelleRole libellé du rôle
	 */
	public Role(String idRole, String libelleRole) {
		super();
		this.idRole = idRole;
		this.libelleRole = libelleRole;
	}

	/**
	 * Retourne l'identifiant du rôle.
	 * @return {@code String} l'identifiant du rôle
	 */
	public String getIdRole() {
		return idRole;
	}

	/**
	 * Modifie l'identifiant du rôle.
	 * @param idRole le nouvel identifiant du rôle
	 */
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	/**
	 * Retourne le libellé du rôle.
	 * @return {@code String} le libellé du rôle
	 */
	public String getLibelleRole() {
		return libelleRole;
	}

	/**
	 * Modifie le libellé du rôle.
	 * @param libelleRole le nouveau libellé du rôle
	 */
	public void setLibelleRole(String libelleRole) {
		this.libelleRole = libelleRole;
	}

	/**
	 * Retourne une représentation textuelle de l'objet Role.
	 * @return {@code String} la représentation textuelle du rôle
	 */
	@Override
	public String toString() {
		return "\nRole : \nidRole : " + idRole + "\nlibelleRole : " + libelleRole;
	}
}