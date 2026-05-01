
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BDD.ConnexionDB;
import POJO.Region;
import POJO.Role;

/**
 * DAO des Régions
 */
public class RegionDAO extends DAO<Region> {

	/**
	 * Instanciation du DAO
	 */
	public RegionDAO() {
		super(null);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Méthode pour créer une région
	 * @param obj prend un objet Region comme données
	 * @return boolean vrai ou faux si la création de la Région a bien été faite
	 */
	@Override
	public boolean create(Region obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* Méthode pour effacer une région
	* @param obj prend un objet Region comme données
	* @return boolean vrai ou faux si la suppression de la région a bien été faite
	*/
	@Override
	public boolean delete(Region obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	* Méthode de mise à jour d'une région
	* @param obj prend un objet Region comme données
	* @return boolean vrai ou faux si la mise a jour de la région a bien été faite
	*/
	@Override
	public boolean update(Region obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	* Méthode de recherche des informations d'une région (non utiliser car faite dans une autre méthode mais obligatoire car dans DAO)
	* @param idRegion un id d'une région
	* @return Region un objet Region contenant les informations de la région
	*/
	@Override
	public Region find(int idRegion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	* Méthode de recherche des informations d'une région 
	* @param idRegion un id d'une région
	* @return Region un objet Region contenant les informations de la région
	*/
	public static Region findRegion(int idRegion) {
		Connection con = ConnexionDB.getConnection();

	    String sql = "SELECT * FROM region WHERE idRegion = '" + idRegion + "'";
	    try {
	        Statement requete = con.createStatement();
	        ResultSet result = requete.executeQuery(sql);

	        while (result.first()) {
	        	Region region = new Region(result.getInt("idRegion"),result.getString("libelleRegion"));
	        	return region;
	        }
	    } catch (SQLException e) {                                      
	        System.out.println("Il n'y a aucune region avec l'id " + idRegion);
	        e.printStackTrace(); // affiche l'erreur précise dans la console
	    }
		return null;
	}
	
	/**
	 * Méthode de recherche des informations de toutes les régions
	 * 
	 * @return {@code ArrayList<Region>} Une Liste de régions
	 */
	public ArrayList<Region> findAllRegions() {
		Connection con = ConnexionDB.getConnection();
	    ArrayList<Region> regions = new ArrayList<>();

	    String sql = "SELECT * FROM region";
	    try {
	        Statement requete = con.createStatement();
	        ResultSet result = requete.executeQuery(sql);

	        while (result.next()) {
	        	Region region = new Region(result.getInt("idRegion"),result.getString("libelleRegion"));
	        	regions.add(region);
	        }
	    } catch (SQLException e) {                                      
	        System.out.println("Il n'y a aucune Region");
	        e.printStackTrace(); // affiche l'erreur précise dans la console
	    }
	    return regions;
	}

}