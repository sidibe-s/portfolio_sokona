package DAO;

import java.sql.Connection;

/**
 * DAO de base (sert d'exemple)

 * @param <T> le type de l'objet métier géré par ce DAO
 */
public abstract class DAO<T> {
	protected Connection connect = null;
	
	/** Connexion à la base de données
	 * 
	 * @param conn récupère la connexion à la base de données
	 */
	public DAO(Connection conn){
		this.connect = conn;
	}
	
	/**
	* Méthode de création d'un objet (de base)
	* @param obj (un objet)
	* @return boolean (vrai ou faux si la création a bien été faite)
	*/
	public abstract boolean create(T obj);
	
	/**
	* Méthode pour effacer un objet (de base)
	* @param obj (un objet)
	* @return boolean (vrai ou faux si la suppression a bien été faite)
	*/
	public abstract boolean delete(T obj);
	
	/**
	* Méthode de mise à jour d'un objet (de base)
	* @param obj (un objet)
	* @return boolean (vrai ou faux si la mise a jour a bien été faite)
	*/
	public abstract boolean update(T obj);
	
	/**
	* Méthode de recherche des informations d'un objet (de base)
	* @param id (un id d'un objet)
	* @return T (un objet)
	*/
	public abstract T find(int id);
	
}