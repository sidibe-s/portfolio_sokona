package VUES;

import javax.swing.JFrame;

import BDD.ConnexionDB;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Fenêtre permettant de se connecter à l'application.
 */
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField tfLogin;
	private JTextField tfPassword;

	/**
	 * Récupère le rôle de l'utilisateur depuis la base de données selon ses identifiants.
	 * @param username le login saisi par l'utilisateur
	 * @param password le mot de passe saisi par l'utilisateur
	 * @return {@code String} le rôle de l'utilisateur, ou null si les identifiants sont incorrects
	 */
	private String getRole(String username, String password) {
	    String sql = "SELECT idRole FROM utilisateur WHERE login = ? AND mdp = ?";

	    try {
	        Connection conn = ConnexionDB.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);
	        stmt.setString(2, password);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("idRole");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	/**
	 * Crée la fenêtre de connexion à l'application.
	 */
	public Login() {
		getContentPane().setLayout(null);

		setTitle("GSB - Connexion");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLogin.setBounds(109, 115, 82, 36);
		getContentPane().add(lblLogin);

		tfLogin = new JTextField();
		tfLogin.setBounds(217, 115, 150, 36);
		getContentPane().add(tfLogin);
		tfLogin.setColumns(10);

		JLabel lblPassword = new JLabel("Mot de passe : ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPassword.setBounds(21, 184, 167, 36);
		getContentPane().add(lblPassword);

		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(217, 184, 150, 36);
		getContentPane().add(tfPassword);

		JButton loginButton = new JButton("Connection");
		loginButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = tfLogin.getText();
		        String password = tfPassword.getText();

		        String role = getRole(username, password);

		        if (role != null) {
		            Menu pageMenu = new Menu(role);
		            pageMenu.setVisible(true);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Identifiants incorrects.", "Erreur", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		loginButton.setBounds(250, 276, 89, 23);
		getContentPane().add(loginButton);
	}
}