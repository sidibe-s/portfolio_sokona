package VUES;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

/**
* Fenêtre de test.
*/
public class PageTest extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageTest frame = new PageTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PageTest() {
		getContentPane().setLayout(null);
		
		setTitle("GSB - Accueil");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// texte à gauche (label) pour indiquer du test
		JLabel lblTest = new JLabel("Voici la page de test");
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTest.setBounds(92, 11, 228, 52);
		getContentPane().add(lblTest);
		
	}
}
