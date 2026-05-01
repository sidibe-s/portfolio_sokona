package BDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire permettant d'accéder à la base de données pour l'application.
 * Non instanciable, utiliser uniquement les méthodes statiques.
 */
public class ConnexionDB {
	private static final String URL      = "jdbc:mysql://mysql-workstation.alwaysdata.net:3306/workstation_gsbfrais2025-ap?useSSL=false";
	private static final String USER     = "workstation_myroot";
	private static final String PASSWORD = "root123*"; // ton mot de passe pour cet utilisateur
    
    private static Connection instance = null;

    /**
     * Constructeur privé - empêche l'instanciation de la classe.
     */
    private ConnexionDB() {
        // Classe utilitaire, ne pas instancier
    }

    /**
     * Retourne la connexion à la base de données.
     * Si la connexion n'existe pas ou est fermée, elle est recréée.
     * @return {@code Connection} la connexion active à la base de données
     */
    public static Connection getConnection() {
        try {
            if (instance == null || instance.isClosed()) {
                instance = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de connexion à la base de données");
        }
        return instance;
    }

    /**
     * Ferme la connexion à la base de données si elle est ouverte.
     */
    public static void close() {
        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
                instance = null;
                System.out.println("Connexion fermée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problème lors de la fermeture");
        }
    }
}