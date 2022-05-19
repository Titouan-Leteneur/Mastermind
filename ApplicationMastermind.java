import java.awt.GridLayout;

import javax.swing.JFrame;

public class ApplicationMastermind {
	
	public static void main(String[] args) {
		// Création de la fenêtre
		JFrame f = new JFrame();
		f.setTitle("Mastermind");
		f.setLayout(new GridLayout(1,1));
		
		// Ajout de la vue
		f.add(new VueMastermind(10, 5));
		
		f.pack();
		f.setVisible(true);
		f.setSize(300, 550);
	}

}
