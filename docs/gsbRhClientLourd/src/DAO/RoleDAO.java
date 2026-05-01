package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import POJO.Role;
import BDD.ConnexionDB;

/**
 * DAO des Roles
 */
public class RoleDAO extends DAO<Role> {

	/**
	 * Instanciation du DAO
	 */
	public RoleDAO() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Méthode pour créer un role
	 * @param obj prend un objet Role comme données
	 * @return boolean vrai ou faux si la création du role a bien été faite
	 */
	@Override
	public boolean create(Role obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* Méthode pour effacer un role
	* @param obj prend un objet Role comme données
	* @return boolean vrai ou faux si la suppression du role a bien été faite
	*/
	@Override
	public boolean delete(Role obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* Méthode de mise à jour d'un role
	* @param obj prend un objet Role comme données
	* @return boolean vrai ou faux si la mise a jour du role a bien été faite
	*/
	@Override
	public boolean update(Role obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* Méthode de recherche des informations d'un role (non utiliser car faite dans une autre méthode mais obligatoire car dans DAO)
	* @param idRole un id d'un role
	* @return Region un objet Role contenant les informations de la région
	*/
	@Override
	public Role find(int idRole) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	* Méthode de recherche des informations d'un role
	* @param idRole un id d'un role
	* @return Region un objet Role contenant les informations de la région
	*/
	public static Role findRole(String idRole) {
		Connection con = ConnexionDB.getConnection();

	    String sql = "SELECT * FROM role WHERE idRole = '" + idRole + "'";
	    try {
	        Statement requete = con.createStatement();
	        ResultSet result = requete.executeQuery(sql);

	        while (result.first()) {
	        	Role role = new Role(result.getString("idRole"),result.getString("libelleRole"));
	        	return role;
	        }
	    } catch (SQLException e) {                                      
	        System.out.println("Il n'y a aucun Role avec l'id" + idRole);
	        e.printStackTrace(); // affiche l'erreur précise dans la console
	    }
		return null;
	}
	
	/**
	 * Méthode de recherche des informations de toutes les roles
	 * 
	 * @return {@code ArrayList<Role>} Une Liste de roles
	 */
	public ArrayList<Role> findAllRoles() {
		Connection con = ConnexionDB.getConnection();
	    ArrayList<Role> roles = new ArrayList<>();

	    String sql = "SELECT * FROM role";
	    try {
	        Statement requete = con.createStatement();
	        ResultSet result = requete.executeQuery(sql);

	        while (result.next()) {
	        	Role role = new Role(result.getString("idRole"),result.getString("libelleRole"));
	        	roles.add(role);
	        }
	    } catch (SQLException e) {                                      
	        System.out.println("Il n'y a aucun Role");
	        e.printStackTrace(); // affiche l'erreur précise dans la console
	    }
	    return roles;
	}
}