package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import BDD.ConnexionDB;
import POJO.Region;
import POJO.Role;
import POJO.Utilisateur;

/**
 * DAO des Utilisateurs
 */
public class UtilisateurDAO extends DAO<Utilisateur> {

	/**
	 * Instanciation du DAO
	 */
	public UtilisateurDAO() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Méthode pour créer un utilisateur (non utiliser car faite dans une autre méthode)
	 * @param obj prend un objet Utilisateur comme données
	 * @return boolean vrai ou faux si la création de l'utilisateur a bien été faite
	 */
	@Override
	public boolean create(Utilisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* Méthode pour effacer un Utilisateur (non utiliser car faite dans une autre méthode)
	* @param obj prend un objet Utilisateur comme données
	* @return boolean vrai ou faux si la suppression de l'utilisateur a bien été faite
	*/
	@Override
	public boolean delete(Utilisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	* Méthode de mise à jour d'un Utilisateur (non utiliser car faite dans une autre méthode)
	* @param obj prend un objet Utilisateur comme données
	* @return boolean vrai ou faux si la mise a jour de l'utilisateur a bien été faite
	*/
	@Override
	public boolean update(Utilisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	* Méthode de recherche des informations d'un Utilisateur (non utiliser car faite dans une autre méthode mais obligatoire car dans DAO)
	* @param idUtilisateur un id d'un role
	* @return Utilisateur un objet Utilisateur contenant les informations de la région
	*/
	@Override
	public Utilisateur find(int idUtilisateur) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	/**
	 * Méthode de création d'un Utilisateur
	 * @param id id du nouvel utilisateur
	 * @param nom nom du nouvel utilisateur
	 * @param prenom prenom du nouvel utilisateur
	 * @param login login du nouvel utilisateur
	 * @param mdp mot de passe du nouvel utilisateur
	 * @param adresse adresse du nouvel utilisateur
	 * @param cp code postal du nouvel utilisateur
	 * @param ville ville du nouvel utilisateur
	 * @param idRole rôle du nouvel utilisateur
	 */
	public static void createUtilisateur(String id, String nom, String prenom, String login,
	        String mdp, String adresse, String cp, String ville, String idRole) {
	    String requeteSql = "INSERT INTO utilisateur (idUtilisateur, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche, idRole) " +
	                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), ?)";
	    try {
	        Connection conn = ConnexionDB.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(requeteSql);
	        stmt.setString(1, id);
	        stmt.setString(2, nom);
	        stmt.setString(3, prenom);
	        stmt.setString(4, login);
	        stmt.setString(5, mdp);
	        stmt.setString(6, adresse);
	        stmt.setString(7, cp);
	        stmt.setString(8, ville);
	        stmt.setString(9, idRole);
	        stmt.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Utilisateur créé avec succès !");
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + e.getMessage(),
	            "Erreur", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}
	
	/**
	* Méthode de suppression complète d'un utilisateur seulement si toutes ses fiches de frais sont rembourser
	* @param idUtilisateur un id d'un role
	*/
	public static void deleteUtilisateurComplete(String idUtilisateur) {
	    try {
	        Connection conn = ConnexionDB.getConnection();
	        conn.setAutoCommit(false);

	        // 1. Vérifier que TOUTES les fiches sont en état RB
	        PreparedStatement requeteVerifFiche = conn.prepareStatement("SELECT idFiche, idEtat FROM fichefrais WHERE idUtilisateur = ?");
	        requeteVerifFiche.setString(1, idUtilisateur);
	        ResultSet resultatVerif = requeteVerifFiche.executeQuery();

	        while (resultatVerif.next()) {
	            String etat = resultatVerif.getString("idEtat");
	            if (!etat.equals("RB")) {
	                conn.rollback();
	                JOptionPane.showMessageDialog(null,"Impossible de supprimer l'utilisateur , une fiche au moins n'est pas rembourser ");
	                return;
	            }
	        }

	        // 2. Récupérer les idFiche pour supprimer les lignes associées
	        PreparedStatement requeteFiches = conn.prepareStatement("SELECT idFiche FROM fichefrais WHERE idUtilisateur = ?");
	        requeteFiches.setString(1, idUtilisateur);
	        ResultSet resultatFiches = requeteFiches.executeQuery();

	        while (resultatFiches.next()) {
	            int idFiche = resultatFiches.getInt("idFiche");

	            // 3. Supprimer les lignes forfait
	            PreparedStatement psLFF = conn.prepareStatement("DELETE FROM lignefraisforfait WHERE idFiche = ?");
	            psLFF.setInt(1, idFiche);
	            psLFF.executeUpdate();

	            // 4. Supprimer les lignes hors forfait
	            PreparedStatement psLFHF = conn.prepareStatement("DELETE FROM lignefraishorsforfait WHERE idFiche = ?");
	            psLFHF.setInt(1, idFiche);
	            psLFHF.executeUpdate();
	        }

	        // 5. Supprimer les fiches de frais
	        PreparedStatement psFiche = conn.prepareStatement("DELETE FROM fichefrais WHERE idUtilisateur = ?");
	        psFiche.setString(1, idUtilisateur);
	        psFiche.executeUpdate();

	        // 6. Supprimer l'utilisateur
	        PreparedStatement psUser = conn.prepareStatement("DELETE FROM utilisateur WHERE idUtilisateur = ?");
	        psUser.setString(1, idUtilisateur);
	        psUser.executeUpdate();

	        conn.commit();
	        JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succès !");

	    } 
	    
	    catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	/**
	 * Méthode de mise à jour des informations d'un Utilisateur
	 * @param id id de l'utilisateur
	 * @param nom nouveau nom de l'utilisateur
	 * @param prenom nouveau prenom de l'utilisateur
	 * @param login nouveau login de l'utilisateur
	 * @param mdp nouveau mot de passe de l'utilisateur
	 * @param adresse nouvelle adresse de l'utilisateur
	 * @param cp nouveau codePostal de l'utilisateur
	 * @param ville nouvelle ville de l'utilisateur
	 * @param idRole idRole de l'utilisateur
	 */
	public static void updateUtilisateur(String id, String nom, String prenom, String login, String mdp, String adresse, String cp, String ville, String idRole) {
		String requeteSql = "UPDATE utilisateur SET nom=?, prenom=?, login=?, mdp=?, adresse=?, cp=?, ville=?, idRole=? WHERE idUtilisateur=?";
		try {
			Connection conn = ConnexionDB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(requeteSql);
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			stmt.setString(3, login);
			stmt.setString(4, mdp);
			stmt.setString(5, adresse);
			stmt.setString(6, cp);
			stmt.setString(7, ville);
			stmt.setString(8, idRole);
			stmt.setString(9, id);
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Utilisateur modifié avec succès !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Méthode de recherche des informations d'un utilisateur
	* @param idUtilisateur un id d'un idUtilisateur
	* @return Utilisateur un objet Role contenant les informations de l'utilisateur
	*/
	public static Utilisateur findUtilisateur(String idUtilisateur) {
		Connection con = ConnexionDB.getConnection();

	    String sql = "SELECT * FROM utilisateur WHERE idUtilisateur = '" + idUtilisateur + "'";
	    try {
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, idUtilisateur);
	        ResultSet result = stmt.executeQuery();

	        if (result.next()) {
	            Role role = new Role(
	                result.getString("idRole"),
	                result.getString("libelleRole")
	            );
	            Region region = new Region(
	                result.getInt("idRegion"),
	                result.getString("libelleRegion")
	            );
	            return new Utilisateur(
	                result.getString("idUtilisateur"),
	                result.getString("nom"),
	                result.getString("prenom"),
	                result.getString("login"),
	                result.getString("mdp"),
	                result.getString("adresse"),
	                result.getString("cp"),
	                result.getString("ville"),
	                result.getDate("dateEmbauche"),
	                role,
	                result.getDate("date_modif_mdp"),
	                region
	            );
	        }
	    } catch (SQLException e) {                                      
	        System.out.println("Il n'y a aucun Utilisateur avec l'id " + idUtilisateur);
	        e.printStackTrace(); // affiche l'erreur précise dans la console
	    }
		return null;
	}
	
	/**
	* Méthode de recherche des informations de tout les utilisateurs
	* 
	* @return {@code ArrayList<Utilisateur>} Une liste de tout les utilisateurs
	*/
	public static ArrayList<Utilisateur> findAllUtilisateur() {
	    ArrayList<Utilisateur> liste = new ArrayList<>();

	    try {
	        Connection conn = ConnexionDB.getConnection();

	        String sql = "SELECT u.*, ro.libelleRole, re.libelleRegion " +
	                     "FROM utilisateur u " +
	                     "LEFT JOIN role ro ON u.idRole = ro.idRole " +
	                     "LEFT JOIN region re ON u.idRegion = re.idRegion";

	        ResultSet result = conn.createStatement().executeQuery(sql);

	        while (result.next()) {
	            // Reconstruction du Role et de la Region
	            Role role = new Role(
	                result.getString("idRole"),
	                result.getString("libelleRole")
	            );
	            Region region = new Region(
	                result.getInt("idRegion"),
	                result.getString("libelleRegion")
	            );

	            // Construction de l'Utilisateur complet
	            Utilisateur u = new Utilisateur(
	                result.getString("idUtilisateur"),
	                result.getString("nom"),
	                result.getString("prenom"),
	                result.getString("login"),
	                result.getString("mdp"),
	                result.getString("adresse"),
	                result.getString("cp"),
	                result.getString("ville"),
	                result.getDate("dateEmbauche"),
	                role,
	                result.getDate("date_modif_mdp"),
	                region
	            );

	            liste.add(u);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return liste;
	}
	

}