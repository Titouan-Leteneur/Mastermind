import java.awt.GridLayout;

import javax.swing.JFrame;

public class ApplicationMastermind {
	
	public static void main(String[] args) {
		// Cr�ation de la fen�tre
		JFrame f = new JFrame();
		f.setTitle("Mastermind");
		f.setLayout(new GridLayout(1,1));
		
		// Ajout de la vue morpion
		f.add(new VueMastermind(10, 5));
		
		f.pack();
		f.setVisible(true);
		f.setSize(300, 550);
	}

}
