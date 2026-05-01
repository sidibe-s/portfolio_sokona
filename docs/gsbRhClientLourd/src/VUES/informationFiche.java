package VUES;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.util.ArrayList;
import DAO.FicheFraisDAO;
import POJO.FicheFrais;
import POJO.LigneFraisForfait;
import POJO.LigneFraisHorsForfait;

public class informationFiche extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField TFForfaitEtape;
    private JTextField TFFraisKilometrique;
    private JTextField TFNuitHotel;
    private JTextField TFRepasRestaurant;

    private String role;
    private String idVisiteur;
    private int idFiche;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new informationFiche("s").setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public informationFiche(String role) {
        this(role, null, 0);
    }

    /** @wbp.parser.constructor */
    public informationFiche(String role, String idVisiteur, int idFiche) {
        this.role = role;
        this.idVisiteur = idVisiteur;
        this.idFiche = idFiche;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 560, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitre = new JLabel("Fiche Visiteur numéro " + idFiche);
        lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitre.setBounds(181, 11, 212, 32);
        contentPane.add(lblTitre);

        JLabel lblForfaitEtape = new JLabel("Forfait Etape :");
        lblForfaitEtape.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblForfaitEtape.setBounds(21, 103, 104, 17);
        contentPane.add(lblForfaitEtape);

        TFForfaitEtape = new JTextField();
        TFForfaitEtape.setBounds(135, 100, 86, 20);
        TFForfaitEtape.setEditable(false);
        contentPane.add(TFForfaitEtape);

        JLabel lblFraisKm = new JLabel("Frais Kilometrique :");
        lblFraisKm.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFraisKm.setBounds(21, 162, 120, 17);
        contentPane.add(lblFraisKm);

        TFFraisKilometrique = new JTextField();
        TFFraisKilometrique.setBounds(150, 159, 86, 20);
        TFFraisKilometrique.setEditable(false);
        contentPane.add(TFFraisKilometrique);

        JLabel lblNuitHotel = new JLabel("Nuit Hotel :");
        lblNuitHotel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNuitHotel.setBounds(303, 103, 104, 17);
        contentPane.add(lblNuitHotel);

        TFNuitHotel = new JTextField();
        TFNuitHotel.setBounds(417, 100, 86, 20);
        TFNuitHotel.setEditable(false);
        contentPane.add(TFNuitHotel);

        JLabel lblRepas = new JLabel("Repas Restaurant :");
        lblRepas.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblRepas.setBounds(303, 162, 120, 17);
        contentPane.add(lblRepas);

        TFRepasRestaurant = new JTextField();
        TFRepasRestaurant.setBounds(417, 159, 86, 20);
        TFRepasRestaurant.setEditable(false);
        contentPane.add(TFRepasRestaurant);

        JLabel lblHorsForfait = new JLabel("Frais Hors Forfait Ce mois ci :");
        lblHorsForfait.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblHorsForfait.setBounds(153, 204, 209, 17);
        contentPane.add(lblHorsForfait);

        String[] colonnes = {"Libellé", "Date", "Montant (€)"};
        DefaultTableModel modelHF = new DefaultTableModel(colonnes, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tableHorsForfait = new JTable(modelHF);
        JScrollPane scrollPane = new JScrollPane(tableHorsForfait);
        scrollPane.setBounds(21, 226, 492, 124);
        contentPane.add(scrollPane);

        JButton btnRetour = new JButton("X");
        btnRetour.setBounds(520, 7, 23, 20);
        btnRetour.addActionListener(e -> {
            new listeFiches(role, idVisiteur).setVisible(true);
            dispose();
        });
        contentPane.add(btnRetour);

        chargerDonneesFiche(modelHF);
    }

    /**
     * Charge et affiche toutes les données de la fiche sélectionnée :
     * montant total, quantités des 4 frais forfaitaires, et lignes hors forfait.
     */
    private void chargerDonneesFiche(DefaultTableModel modelHF) {

        ArrayList<LigneFraisForfait> lignesForfait = FicheFraisDAO.selectLignesForfaitByIdFiche(idFiche);

        for (LigneFraisForfait l : lignesForfait) {
            switch (l.getIdFraisForfait()) {
                case "ETP": // Forfait Etape
                    TFForfaitEtape.setText(String.valueOf(l.getQuantite()));
                    break;
                case "KM":  // Frais Kilométrique
                    TFFraisKilometrique.setText(String.valueOf(l.getQuantite()));
                    break;
                case "NUI": // Nuit Hôtel
                    TFNuitHotel.setText(String.valueOf(l.getQuantite()));
                    break;
                case "REP": // Repas Restaurant
                    TFRepasRestaurant.setText(String.valueOf(l.getQuantite()));
                    break;
            }
        }

        ArrayList<LigneFraisHorsForfait> lignesHF = FicheFraisDAO.selectLignesHorsForfaitByIdFiche(idFiche);

        for (LigneFraisHorsForfait l : lignesHF) {
            modelHF.addRow(new Object[]{
                l.getLibelle(),
                l.getDateFrais(),
                l.getMontant()
            });
        }
    }
}