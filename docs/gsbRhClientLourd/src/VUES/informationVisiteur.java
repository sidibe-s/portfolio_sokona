package VUES;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UtilisateurDAO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import POJO.Utilisateur;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Fenêtre permettant de créer ou modifier les informations d'un visiteur.
 */
public class informationVisiteur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelCreerVisteur;
	private JLabel labeIid;
	private JTextField idAsaisir;
	private JLabel lblNom;
	private JTextField textNom;
	private JLabel lblPrnom;
	private JTextField textPrenom;
	private JLabel lblLogin;
	private JLabel lblMdp;
	private JLabel lblAdresse;
	private JLabel lblCp;
	private JLabel lblDateembauche;
	private JLabel lblIdrole;
	private JLabel lblVille;
	private JButton btnValiderCreationVisiteur;
	private JTextField textMdp;
	private JTextField textCp;
	private JTextField textdateEmbauche;
	private JTextField textVille;
	private JTextField textidRole;
	private JTextField textAdresse;
	private JTextField textLogin;
	private Utilisateur utilisateurEnCours;
	private String role;

	/**
	 * Point d'entrée principal de l'application, lance la fenêtre.
	 * @param args arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					informationVisiteur frame = new informationVisiteur(null, "s");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crée la fenêtre de création ou modification d'un visiteur.
	 * @param u l'utilisateur à modifier, ou null pour une création
	 * @param role le rôle de l'utilisateur connecté
	 */
	public informationVisiteur(Utilisateur u, String role) {
	    this.utilisateurEnCours = u;
	    this.role = role;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLabelCreerVisteur());
		contentPane.add(getLabeIid());
		contentPane.add(getIdAsaisir());
		contentPane.add(getLblNom());
		contentPane.add(getTextNom());
		contentPane.add(getLblPrnom());
		contentPane.add(getTextPrenom());
		contentPane.add(getLblLogin());
		contentPane.add(getLblMdp());
		contentPane.add(getLblAdresse());
		contentPane.add(getLblCp());
		contentPane.add(getLblDateembauche());
		contentPane.add(getLblIdrole());
		contentPane.add(getLblVille());
		contentPane.add(getBtnValiderCreationVisiteur());
		contentPane.add(getTextMdp());
		contentPane.add(getTextCp());
		contentPane.add(getTextdateEmbauche());
		contentPane.add(getTextVille());
		contentPane.add(getTextidRole());
		contentPane.add(getTextAdresse());
		contentPane.add(getTextLogin());
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeVisiteurs listeDesVisiteurs = new listeVisiteurs(role);
				listeDesVisiteurs.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(254, 227, 89, 23);
		contentPane.add(btnRetour);

		if (u != null) {
		    getLabelCreerVisteur().setText("Modification Visiteur");
		    getIdAsaisir().setText(u.getIdUtilisateur());
		    getIdAsaisir().setEditable(false);
		    getTextNom().setText(u.getNom());
		    getTextPrenom().setText(u.getPrenom());
		    getTextLogin().setText(u.getLogin());
		    getTextMdp().setText(u.getMdp());
		    getTextAdresse().setText(u.getAdresse());
		    getTextCp().setText(u.getCp());
		    getTextVille().setText(u.getVille());
		    getTextdateEmbauche().setText(u.getDateEmbauche().toString());
		    getTextidRole().setText(u.getIdRole().getIdRole());
		} else {
		    getTextidRole().setText("v");
		    getTextidRole().setEditable(false);
		    String dateAujourdhui = java.time.LocalDate.now().toString();
		    getTextdateEmbauche().setText(dateAujourdhui);
		    getTextdateEmbauche().setEditable(false);
		}
	}

	/**
	 * Retourne le label du titre de la fenêtre.
	 * @return {@code JLabel} le label titre
	 */
	public JLabel getLabelCreerVisteur() {
		if (labelCreerVisteur == null) {
			labelCreerVisteur = new JLabel("Création Visiteur");
			labelCreerVisteur.setBounds(168, 11, 97, 14);
		}
		return labelCreerVisteur;
	}

	/**
	 * Retourne le label de l'identifiant utilisateur.
	 * @return {@code JLabel} le label id
	 */
	public JLabel getLabeIid() {
		if (labeIid == null) {
			labeIid = new JLabel("idUtilisateur: ");
			labeIid.setBounds(10, 47, 74, 14);
		}
		return labeIid;
	}

	/**
	 * Retourne le champ de saisie de l'identifiant utilisateur.
	 * @return {@code JTextField} le champ id
	 */
	public JTextField getIdAsaisir() {
		if (idAsaisir == null) {
			idAsaisir = new JTextField();
			idAsaisir.setBounds(112, 47, 86, 14);
			idAsaisir.setColumns(10);
		}
		return idAsaisir;
	}

	/**
	 * Retourne le label du nom.
	 * @return {@code JLabel} le label nom
	 */
	public JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("nom: ");
			lblNom.setBounds(254, 47, 74, 14);
		}
		return lblNom;
	}

	/**
	 * Retourne le champ de saisie du nom.
	 * @return {@code JTextField} le champ nom
	 */
	public JTextField getTextNom() {
		if (textNom == null) {
			textNom = new JTextField();
			textNom.setColumns(10);
			textNom.setBounds(318, 47, 86, 14);
		}
		return textNom;
	}

	/**
	 * Retourne le label du prénom.
	 * @return {@code JLabel} le label prénom
	 */
	public JLabel getLblPrnom() {
		if (lblPrnom == null) {
			lblPrnom = new JLabel("Prenom: ");
			lblPrnom.setBounds(10, 77, 74, 14);
		}
		return lblPrnom;
	}

	/**
	 * Retourne le champ de saisie du prénom.
	 * @return {@code JTextField} le champ prénom
	 */
	public JTextField getTextPrenom() {
		if (textPrenom == null) {
			textPrenom = new JTextField();
			textPrenom.setColumns(10);
			textPrenom.setBounds(112, 77, 86, 14);
		}
		return textPrenom;
	}

	/**
	 * Retourne le label du login.
	 * @return {@code JLabel} le label login
	 */
	public JLabel getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new JLabel("Login: ");
			lblLogin.setBounds(254, 77, 74, 14);
		}
		return lblLogin;
	}

	/**
	 * Retourne le label du mot de passe.
	 * @return {@code JLabel} le label mot de passe
	 */
	public JLabel getLblMdp() {
		if (lblMdp == null) {
			lblMdp = new JLabel("Mdp: ");
			lblMdp.setBounds(10, 117, 74, 14);
		}
		return lblMdp;
	}

	/**
	 * Retourne le label de l'adresse.
	 * @return {@code JLabel} le label adresse
	 */
	public JLabel getLblAdresse() {
		if (lblAdresse == null) {
			lblAdresse = new JLabel("Adresse: ");
			lblAdresse.setBounds(254, 117, 74, 14);
		}
		return lblAdresse;
	}

	/**
	 * Retourne le label du code postal.
	 * @return {@code JLabel} le label code postal
	 */
	public JLabel getLblCp() {
		if (lblCp == null) {
			lblCp = new JLabel("Cp: ");
			lblCp.setBounds(10, 152, 74, 14);
		}
		return lblCp;
	}

	/**
	 * Retourne le label de la date d'embauche.
	 * @return {@code JLabel} le label date d'embauche
	 */
	public JLabel getLblDateembauche() {
		if (lblDateembauche == null) {
			lblDateembauche = new JLabel("dateEmbauche: ");
			lblDateembauche.setBounds(10, 187, 86, 14);
		}
		return lblDateembauche;
	}

	/**
	 * Retourne le label du rôle.
	 * @return {@code JLabel} le label rôle
	 */
	public JLabel getLblIdrole() {
		if (lblIdrole == null) {
			lblIdrole = new JLabel("idRole: ");
			lblIdrole.setBounds(254, 187, 74, 14);
		}
		return lblIdrole;
	}

	/**
	 * Retourne le label de la ville.
	 * @return {@code JLabel} le label ville
	 */
	public JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville: ");
			lblVille.setBounds(254, 152, 74, 14);
		}
		return lblVille;
	}

	/**
	 * Retourne le bouton de validation de la création ou modification du visiteur.
	 * @return {@code JButton} le bouton valider
	 */
	public JButton getBtnValiderCreationVisiteur() {
	    if (btnValiderCreationVisiteur == null) {
	        btnValiderCreationVisiteur = new JButton("Valider");
	        btnValiderCreationVisiteur.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String id = getIdAsaisir().getText();
	                String nom = getTextNom().getText();
	                String prenom = getTextPrenom().getText();
	                String login = getTextLogin().getText();
	                String mdp = getTextMdp().getText();
	                String adresse = getTextAdresse().getText();
	                String cp = getTextCp().getText();
	                String ville = getTextVille().getText();
	                String idRole = getTextidRole().getText();

	                if (utilisateurEnCours != null) {
	                    UtilisateurDAO.updateUtilisateur(id, nom, prenom, login, mdp, adresse, cp, ville, idRole);
	                } else {
	                    UtilisateurDAO.createUtilisateur(id, nom, prenom, login, mdp, adresse, cp, ville, idRole);
	                }

	                listeVisiteurs liste = new listeVisiteurs(role);
	                liste.setVisible(true);
	                dispose();
	            }
	        });
	        btnValiderCreationVisiteur.setBounds(98, 227, 89, 23);
	    }
	    return btnValiderCreationVisiteur;
	}

	/**
	 * Retourne le champ de saisie du mot de passe.
	 * @return {@code JTextField} le champ mot de passe
	 */
	public JTextField getTextMdp() {
		if (textMdp == null) {
			textMdp = new JTextField();
			textMdp.setColumns(10);
			textMdp.setBounds(112, 114, 86, 14);
		}
		return textMdp;
	}

	/**
	 * Retourne le champ de saisie du code postal.
	 * @return {@code JTextField} le champ code postal
	 */
	public JTextField getTextCp() {
		if (textCp == null) {
			textCp = new JTextField();
			textCp.setColumns(10);
			textCp.setBounds(112, 149, 86, 14);
		}
		return textCp;
	}

	/**
	 * Retourne le champ de saisie de la date d'embauche.
	 * @return {@code JTextField} le champ date d'embauche
	 */
	public JTextField getTextdateEmbauche() {
		if (textdateEmbauche == null) {
			textdateEmbauche = new JTextField();
			textdateEmbauche.setColumns(10);
			textdateEmbauche.setBounds(112, 184, 86, 14);
		}
		return textdateEmbauche;
	}

	/**
	 * Retourne le champ de saisie de la ville.
	 * @return {@code JTextField} le champ ville
	 */
	public JTextField getTextVille() {
		if (textVille == null) {
			textVille = new JTextField();
			textVille.setColumns(10);
			textVille.setBounds(318, 152, 86, 14);
		}
		return textVille;
	}

	/**
	 * Retourne le champ de saisie du rôle.
	 * @return {@code JTextField} le champ rôle
	 */
	public JTextField getTextidRole() {
		if (textidRole == null) {
			textidRole = new JTextField();
			textidRole.setColumns(10);
			textidRole.setBounds(318, 184, 86, 14);
		}
		return textidRole;
	}

	/**
	 * Retourne le champ de saisie de l'adresse.
	 * @return {@code JTextField} le champ adresse
	 */
	public JTextField getTextAdresse() {
		if (textAdresse == null) {
			textAdresse = new JTextField();
			textAdresse.setColumns(10);
			textAdresse.setBounds(318, 117, 86, 14);
		}
		return textAdresse;
	}

	/**
	 * Retourne le champ de saisie du login.
	 * @return {@code JTextField} le champ login
	 */
	public JTextField getTextLogin() {
		if (textLogin == null) {
			textLogin = new JTextField();
			textLogin.setColumns(10);
			textLogin.setBounds(318, 77, 86, 14);
		}
		return textLogin;
	}
}