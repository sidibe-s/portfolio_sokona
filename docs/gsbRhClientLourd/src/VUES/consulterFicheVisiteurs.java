package VUES;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.util.ArrayList;
import javax.swing.JScrollPane;
import DAO.UtilisateurDAO;
import POJO.Utilisateur;
import javax.swing.JTextField;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 * Fenêtre permettant de consulter la liste des visiteurs et d'accéder à leurs fiches de frais.
 */
public class consulterFicheVisiteurs extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConsultation;
	private JTable tableVisiteurs;
	private ArrayList<Utilisateur> utilisateurs;
	private JButton btnRetour;
	private String role;
	private JTextField tfRechercheVisiteurs;

	/**
	 * Point d'entrée principal de l'application, lance la fenêtre.
	 * @param args arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listeVisiteurs frame = new listeVisiteurs("s");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crée la fenêtre de consultation des fiches visiteurs.
	 * @param role le rôle de l'utilisateur connecté
	 */
	public consulterFicheVisiteurs(String role) {
		this.role = role;
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 550, 400);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    contentPane.add(getBtnConsulter());

	    JLabel labelVisiteurs = new JLabel("Visiteurs");
	    labelVisiteurs.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelVisiteurs.setBounds(201, 24, 114, 34);
		contentPane.add(labelVisiteurs);
		
		String[] colonnes = {"ID", "Nom", "Prénom", "Login", "Ville", "Rôle"};

		utilisateurs = UtilisateurDAO.findAllUtilisateur();

		DefaultTableModel tableVisiteursModel = new DefaultTableModel(colonnes, 0) {
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		for (Utilisateur u : utilisateurs) {
			tableVisiteursModel.addRow(new String[]{u.getIdUtilisateur(),u.getNom(),u.getPrenom(),u.getLogin(),u.getVille(),u.getIdRole().getLibelleRole()});
		}
		
		tableVisiteurs = new JTable(tableVisiteursModel);
		tableVisiteurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tableVisiteurs);
		scrollPane.setBounds(50, 69, 410, 229);
		contentPane.add(scrollPane);
		contentPane.add(getBtnRetour());

		JLabel lblRechercheVisiteurs = new JLabel("Rechercher : ");
		lblRechercheVisiteurs.setBounds(50, 16, 85, 14);
		contentPane.add(lblRechercheVisiteurs);

		tfRechercheVisiteurs = new JTextField();
		tfRechercheVisiteurs.setBounds(49, 38, 139, 20);
		tfRechercheVisiteurs.setColumns(10);
		contentPane.add(tfRechercheVisiteurs);

		tfRechercheVisiteurs.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e)  { filtrer(); }
		    public void removeUpdate(DocumentEvent e)  { filtrer(); }
		    public void changedUpdate(DocumentEvent e) { filtrer(); }

		    private void filtrer() {
		        String recherche = tfRechercheVisiteurs.getText().toLowerCase().trim();
		        tableVisiteursModel.setRowCount(0);

		        for (Utilisateur u : utilisateurs) {
		            if (u.getIdUtilisateur().toLowerCase().contains(recherche)|| u.getNom().toLowerCase().contains(recherche)|| u.getPrenom().toLowerCase().contains(recherche)|| u.getLogin().toLowerCase().contains(recherche)|| u.getVille().toLowerCase().contains(recherche)|| u.getIdRole().getLibelleRole().toLowerCase().contains(recherche)) 
		            {
		            	tableVisiteursModel.addRow(new String[]{u.getIdUtilisateur(),u.getNom(),u.getPrenom(),u.getLogin(),u.getVille(),u.getIdRole().getLibelleRole()});
		            }
		        }
		    }
		});
	}
	
	/**
	 * Retourne l'utilisateur sélectionné dans le tableau.
	 * Affiche un message d'avertissement si aucune ligne n'est sélectionnée.
	 * @return {@code Utilisateur} l'utilisateur sélectionné, ou null si aucun
	 */
	private Utilisateur getUtilisateurSelectionne() {
	    int ligneSelectionnee = tableVisiteurs.getSelectedRow();
	    
	    if (ligneSelectionnee == -1) {
	        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un utilisateur.", "Attention", JOptionPane.WARNING_MESSAGE);
	        return null;
	    }
	    
	    String idSelectionne = (String) tableVisiteurs.getValueAt(ligneSelectionnee, 0);
	    for (Utilisateur u : utilisateurs) {
	        if (u.getIdUtilisateur() == idSelectionne) {
	            return u;
	        }
	    }
	    return null;
	}

	/**
	 * Retourne le bouton permettant de consulter les fiches de frais du visiteur sélectionné.
	 * @return {@code JButton} le bouton de consultation
	 */
	public JButton getBtnConsulter() {
	    if (btnConsultation == null) {
	        btnConsultation = new JButton("Consulter ses fiches");
	        btnConsultation.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                Utilisateur u = getUtilisateurSelectionne();
	                if (u != null) {
	                    // Passer l'ID du visiteur à listeFiches
	                    listeFiches fichesDuVisiteur = new listeFiches(role, u.getIdUtilisateur());
	                    fichesDuVisiteur.setVisible(true);
	                    dispose();
	                }
	            }
	        });
	        btnConsultation.setBounds(171, 318, 144, 20);
	    }
	    return btnConsultation;
	}
	
	/**
	 * Retourne le bouton de retour vers le menu principal.
	 * @return {@code JButton} le bouton retour
	 */
	private JButton getBtnRetour() {
	    if (btnRetour == null) {
	        btnRetour = new JButton("X");
	        btnRetour.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                Menu reMenu = new Menu(role);
	                reMenu.setVisible(true);
	                dispose();
	            }
	        });
	        btnRetour.setBounds(501, 13, 23, 20);
	    }
	    return btnRetour;
	}
}