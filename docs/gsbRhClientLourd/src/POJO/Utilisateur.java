package POJO;

import java.sql.Date;
import DAO.RegionDAO;
import DAO.RoleDAO;

/**
 * Classe représentant un utilisateur de l'application.
 */
public class Utilisateur {
	String idUtilisateur;
	String nom;
	String prenom;
	String login;
	String mdp;
	String adresse;
	String cp;
	String ville;
	Date dateEmbauche;
	Role idRole;
	Date date_modif_mdp;
	Region idRegion;

	/**
	 * Constructeur de la classe Utilisateur.
	 * @param idUtilisateur identifiant de l'utilisateur
	 * @param nom nom de l'utilisateur
	 * @param prenom prénom de l'utilisateur
	 * @param login login de l'utilisateur
	 * @param mdp mot de passe de l'utilisateur
	 * @param adresse adresse de l'utilisateur
	 * @param cp code postal de l'utilisateur
	 * @param ville ville de l'utilisateur
	 * @param dateEmbauche date d'embauche de l'utilisateur
	 * @param idRole rôle de l'utilisateur
	 * @param date_modif_mdp date de dernière modification du mot de passe
	 * @param idRegion région de l'utilisateur
	 */
	public Utilisateur(String idUtilisateur, String nom, String prenom, String login, String mdp, String adresse,
			String cp, String ville, Date dateEmbauche, Role idRole, Date date_modif_mdp, Region idRegion) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.dateEmbauche = dateEmbauche;
		this.idRole = idRole;
		this.date_modif_mdp = date_modif_mdp;
		this.idRegion = idRegion;
	}

	/**
	 * Retourne l'identifiant de l'utilisateur.
	 * @return {@code String} l'identifiant de l'utilisateur
	 */
	public String getIdUtilisateur() {
		return idUtilisateur;
	}

	/**
	 * Modifie l'identifiant de l'utilisateur.
	 * @param idUtilisateur le nouvel identifiant de l'utilisateur
	 */
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	/**
	 * Retourne le nom de l'utilisateur.
	 * @return {@code String} le nom de l'utilisateur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom de l'utilisateur.
	 * @param nom le nouveau nom de l'utilisateur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le prénom de l'utilisateur.
	 * @return {@code String} le prénom de l'utilisateur
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le prénom de l'utilisateur.
	 * @param prenom le nouveau prénom de l'utilisateur
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Retourne le login de l'utilisateur.
	 * @return {@code String} le login de l'utilisateur
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Modifie le login de l'utilisateur.
	 * @param login le nouveau login de l'utilisateur
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Retourne le mot de passe de l'utilisateur.
	 * @return {@code String} le mot de passe de l'utilisateur
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * Modifie le mot de passe de l'utilisateur.
	 * @param mdp le nouveau mot de passe de l'utilisateur
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * Retourne l'adresse de l'utilisateur.
	 * @return {@code String} l'adresse de l'utilisateur
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse de l'utilisateur.
	 * @param adresse la nouvelle adresse de l'utilisateur
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Retourne le code postal de l'utilisateur.
	 * @return {@code String} le code postal de l'utilisateur
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * Modifie le code postal de l'utilisateur.
	 * @param cp le nouveau code postal de l'utilisateur
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
	 * Retourne la ville de l'utilisateur.
	 * @return {@code String} la ville de l'utilisateur
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Modifie la ville de l'utilisateur.
	 * @param ville la nouvelle ville de l'utilisateur
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Retourne la date d'embauche de l'utilisateur.
	 * @return {@code Date} la date d'embauche de l'utilisateur
	 */
	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * Modifie la date d'embauche de l'utilisateur.
	 * @param dateEmbauche la nouvelle date d'embauche de l'utilisateur
	 */
	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 * Retourne le rôle de l'utilisateur.
	 * @return {@code Role} le rôle de l'utilisateur
	 */
	public Role getIdRole() {
		return idRole;
	}

	/**
	 * Modifie le rôle de l'utilisateur.
	 * @param idRole le nouveau rôle de l'utilisateur
	 */
	public void setIdRole(Role idRole) {
		this.idRole = idRole;
	}

	/**
	 * Retourne la date de dernière modification du mot de passe.
	 * @return {@code Date} la date de modification du mot de passe
	 */
	public Date getDate_modif_mdp() {
		return date_modif_mdp;
	}

	/**
	 * Modifie la date de dernière modification du mot de passe.
	 * @param date_modif_mdp la nouvelle date de modification du mot de passe
	 */
	public void setDate_modif_mdp(Date date_modif_mdp) {
		this.date_modif_mdp = date_modif_mdp;
	}

	/**
	 * Retourne la région de l'utilisateur.
	 * @return {@code Region} la région de l'utilisateur
	 */
	public Region getIdRegion() {
		return idRegion;
	}

	/**
	 * Modifie la région de l'utilisateur.
	 * @param idRegion la nouvelle région de l'utilisateur
	 */
	public void setIdRegion(Region idRegion) {
		this.idRegion = idRegion;
	}

	/**
	 * Retourne une représentation textuelle de l'objet Utilisateur.
	 * @return {@code String} la représentation textuelle de l'utilisateur
	 */
	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", login="
				+ login + ", mdp=" + mdp + ", adresse=" + adresse + ", cp=" + cp + ", ville=" + ville
				+ ", dateEmbauche=" + dateEmbauche + ", idRole=" + RoleDAO.findRole(idRole.idRole) + ", date_modif_mdp=" + date_modif_mdp
				+ ", idRegion=" + RegionDAO.findRegion(idRegion.idRegion) + "]";
	}
}