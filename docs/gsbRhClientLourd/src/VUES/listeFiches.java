package VUES;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import DAO.FicheFraisDAO;
import POJO.FicheFrais;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.ArrayList;

/**
 * Fenêtre affichant la liste de toutes les fiches de frais.
 */
public class listeFiches extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableFiches;
    private ArrayList<FicheFrais> fichesfrais;
    private String role;
    private String idVisiteur;

    /**
     * Point d'entrée principal de l'application, lance la fenêtre.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    listeFiches frame = new listeFiches("s");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Crée la fenêtre de liste des fiches de frais (toutes les fiches).
     * @param role le rôle de l'utilisateur connecté
     */
    public listeFiches(String role) {
        this(role, null); // Appelle le constructeur principal avec null
    }

    /**
     * Crée la fenêtre de liste des fiches de frais pour un visiteur spécifique.
     * @param role le rôle de l'utilisateur connecté
     * @param idVisiteur l'ID du visiteur dont on veut voir les fiches de frais
     * @wbp.parser.constructor
     */
    public listeFiches(String role, String idVisiteur) {
        this.role = role;
        this.idVisiteur = idVisiteur;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 534, 386);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel labelFiches = new JLabel("Fiches de Frais");
        labelFiches.setFont(new Font("Tahoma", Font.PLAIN, 24));
        labelFiches.setBounds(170, 21, 200, 34);
        contentPane.add(labelFiches);

        String[] colonnes = {"ID", "Année", "Mois"};
        
        if (idVisiteur != null) {
            // Récupérer uniquement les fiches du visiteur sélectionné
            fichesfrais = FicheFraisDAO.selectFicheByIdUtilisateur(idVisiteur);
        }

        DefaultTableModel tableFichesModel = new DefaultTableModel(colonnes, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        for (FicheFrais f : fichesfrais) {
            tableFichesModel.addRow(new String[]{
                String.valueOf(f.getIdFiche()),
                String.valueOf(f.getAnnee()),
                String.valueOf(f.getMois())
            });
        }

        tableFiches = new JTable(tableFichesModel);
        tableFiches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tableFiches);
        scrollPane.setBounds(37, 66, 460, 229);
        contentPane.add(scrollPane);

        JButton btnViewFiche = new JButton("Voir Fiche");
        btnViewFiche.setBounds(201, 318, 99, 20);
        btnViewFiche.addActionListener(e -> {
            FicheFrais f = getFicheSelectionnee();
            if (f != null) {
                // TODO : ouvrir la vue détail de la fiche
                JOptionPane.showMessageDialog(null, "Fiche sélectionnée : " + f.getIdFiche());
            }
        });
        contentPane.add(btnViewFiche);

        JButton btnRetour = new JButton("X");
        btnRetour.setBounds(485, 7, 23, 20);
        btnRetour.addActionListener(e -> {
            consulterFicheVisiteurs reMenu = new consulterFicheVisiteurs(role);
            reMenu.setVisible(true);
            dispose();
        });
        contentPane.add(btnRetour);
    }

    /**
     * Retourne la fiche de frais sélectionnée dans le tableau.
     * Affiche un message d'avertissement si aucune ligne n'est sélectionnée.
     * @return {@code FicheFrais} la fiche sélectionnée, ou null si aucune
     */
    private FicheFrais getFicheSelectionnee() {
        int ligneSelectionnee = tableFiches.getSelectedRow();
        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une fiche.","Attention", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        int idSelectionne = Integer.parseInt((String) tableFiches.getValueAt(ligneSelectionnee, 0));
        
        for (FicheFrais f : fichesfrais) {
            if (f.getIdFiche() == idSelectionne) {
            	// va être utiliser pour afficher la fiche qu'on veut précisément
            	informationFiche ficheChoisit = new informationFiche(role,idVisiteur,f.getIdFiche());
            	ficheChoisit.setVisible(true);
                dispose();
                return f;
            }
        }
        return null;
    }
}