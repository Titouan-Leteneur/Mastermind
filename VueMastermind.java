import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueMastermind extends JPanel{

	private Color[] tabCouleurs = {Color.blue,Color.red,Color.green,Color.yellow,Color.cyan,Color.magenta,Color.black, Color.gray,Color.orange,Color.white};
	private JPanel[] panelCenterCouleurs;
	private JPanel[] panelCenterPlacement;

	private JTextField[] bPIHM;		//tableau des champs contenant le nombre de couleurs bien placees pour chaque combinaison.
	private JTextField[] combinaisonOrdiIHM;	//tableau des champs contenant les couleurs de la combinaison a trouver.
	public JButton[][] combinaisonsJoueurIHM;	//tableau des champs contenant les combinaisons de couleurs proposees par l'utilisateur.
	private JTextField[] mPIHM;		//tableau des champs contenant le nombre de couleurs mal placees pour chaque combinaison.
	int nbCouleurs;		//nombre de couleurs possible dans la combinaison a trouver
	public static int NBMAX_COMBINAISONS = 10;	//nombre maximum d'essais pour trouver la combinaison de couleurs
	private JButton[] paletteIHM;	//ensemble des boutons contenant les couleurs de la palette
	int taille;		//taille de la combinaison a trouver
	JButton btSouthValider;

	public VueMastermind(int nbCouleurs, int taille) {

		this.taille = taille;
		this.nbCouleurs = nbCouleurs;
		
		ControleurMastermind contoleur = new ControleurMastermind(this);
		
		//Fenêtre entiere
		this.setLayout(new BorderLayout());

		//Partie du haut
		JPanel panelNorth = new JPanel();
		this.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new FlowLayout());

		JLabel labelCouleurs = new JLabel("Couleurs : ");
		panelNorth.add(labelCouleurs);

		JPanel panelNorthCouleurs = new JPanel();
		panelNorth.add(panelNorthCouleurs);
		panelNorthCouleurs.setLayout(new GridLayout(1,nbCouleurs));

		this.paletteIHM = new JButton[nbCouleurs];
		for(int i = 0 ; i != nbCouleurs ; i++) {
			this.paletteIHM[i] = new JButton("");
			panelNorthCouleurs.add(this.paletteIHM[i]);
			this.paletteIHM[i].setBackground(tabCouleurs[i]);
			this.paletteIHM[i].addActionListener(contoleur);
		}

		//Partie du centre
		JPanel panelCenter = new JPanel();
		this.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(NBMAX_COMBINAISONS, 2));

		this.panelCenterCouleurs = new JPanel[NBMAX_COMBINAISONS];
		this.combinaisonsJoueurIHM = new JButton[NBMAX_COMBINAISONS][taille];
		this.panelCenterPlacement = new JPanel[NBMAX_COMBINAISONS];
		this.bPIHM = new JTextField[NBMAX_COMBINAISONS];
		this.mPIHM = new JTextField[NBMAX_COMBINAISONS];
		for(int i = 0 ; i != NBMAX_COMBINAISONS ; i++) {
			//Partie gauche
			this.panelCenterCouleurs[i] = new JPanel();
			panelCenter.add(this.panelCenterCouleurs[i]);
			this.panelCenterCouleurs[i].setLayout(new GridLayout(1, taille));
			for(int j = 0 ; j != taille ; j++) {
				this.combinaisonsJoueurIHM[i][j] = new JButton("");
				panelCenterCouleurs[i].add(this.combinaisonsJoueurIHM[i][j]);
				this.combinaisonsJoueurIHM[i][j].addActionListener(contoleur);
				if (i > 0) {
					this.combinaisonsJoueurIHM[i][j].setEnabled(false);
				}
			}
			//Partie droite
			this.panelCenterPlacement[i] = new JPanel();
			panelCenter.add(this.panelCenterPlacement[i]);
			this.panelCenterPlacement[i].setLayout(new GridLayout(2, 2));
			JLabel bp = new JLabel("BP");
			this.panelCenterPlacement[i].add(bp);
			JLabel mp = new JLabel("MP");
			this.panelCenterPlacement[i].add(mp);
			bPIHM[i] = new JTextField("");
			this.panelCenterPlacement[i].add(bPIHM[i]);
			bPIHM[i].setEditable(false);
			mPIHM[i] = new JTextField("");
			this.panelCenterPlacement[i].add(mPIHM[i]);
			mPIHM[i].setEditable(false);
		}


		//Partie du bas
		JPanel panelSouth = new JPanel();
		this.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(1, 2));

		JPanel panelSouthCouleurs = new JPanel();
		panelSouth.add(panelSouthCouleurs);
		panelSouthCouleurs.setLayout(new GridLayout(1,taille));

		this.combinaisonOrdiIHM = new JTextField[taille];
		for(int i = 0 ; i != taille ; i++) {
			this.combinaisonOrdiIHM[i] = new JTextField("");
			panelSouthCouleurs.add(this.combinaisonOrdiIHM[i]);
			this.combinaisonOrdiIHM[i].setEditable(false);
		}

		btSouthValider = new JButton("valider");
		panelSouth.add(btSouthValider);
		this.btSouthValider.addActionListener(contoleur);
	}

	public int getNbCouleurs(){
		return this.nbCouleurs;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public void desactiverCombinaison(int i) {
		for(int j = 0 ; j != taille ; j++) {
			this.combinaisonsJoueurIHM[i][j].setEnabled(false);
		}
	}
	
	public void activerCombinaison(int i) {
		for(int j = 0 ; j != taille ; j++) {
			this.combinaisonsJoueurIHM[i][j].setEnabled(true);
		}
	}
	
	public static int couleurEnChiffre(Color c) {
		int i = 0;
		if(c == Color.blue) {i = 1;}
		if(c == Color.red)	{i = 2;}
		if(c == Color.green) {i = 3;}
		if(c == Color.yellow) {i = 4;}
		if(c == Color.cyan)	{i = 5;}
		if(c == Color.magenta) {i = 6;}
		if(c == Color.black) {i = 7;}
		if(c == Color.gray) {i = 8;}
		if(c == Color.orange) {i = 9;}
		if(c == Color.white) {i = 10;}
		return i;
	}
	
	public Color chiffreEnCouleur(int i) {
		Color c = Color.black;
		if(i == 1) {c = Color.blue;}
		if(i == 2) {c = Color.red;}
		if(i == 3) {c = Color.green;}
		if(i == 4) {c = Color.yellow;}
		if(i == 5) {c = Color.cyan;}
		if(i == 6) {c = Color.magenta;}
		if(i == 7) {c = Color.black;}
		if(i == 8) {c = Color.gray;}
		if(i == 9) {c = Color.orange;}
		if(i == 10) {c = Color.white;}
		return c;
	}
	
	public int[] combinaisonEnEntier(int i) {
		int [] tab = new int [taille];
		for(int j = 0 ; j != taille ; j++) {
			tab[j] = couleurEnChiffre(this.combinaisonsJoueurIHM[i][j].getBackground());
		}
		return tab;
	}
	
	public boolean combinaisonComplete(int i) {
		for(int j = 0 ; j != taille ; j++) {
			if(!(
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.blue || 
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.red ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.green ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.yellow ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.cyan ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.magenta ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.black ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.gray ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.orange ||
				this.combinaisonsJoueurIHM[i][j].getBackground() == Color.white
				)) return false;
		}
		return true;
	}
	
	public boolean appartientPalette(JButton b) {
		for(int i = 0 ; i != nbCouleurs ; i++) {
			if(b == this.paletteIHM[i]) return true;
		}
		return false;
	}
	
	public boolean appartientCombinaison(JButton b, int i) {
		for(int j = 0 ; j != taille ; j++) {
			if(b == this.combinaisonsJoueurIHM[i][j]) return true;
		}
		return false;
	}
	
	public void afficherMP(int i, int nbMP) {
		mPIHM[i].setText(Integer.toString(nbMP));
	}
	
	public void afficherBP(int i, int nbBP) {
		bPIHM[i].setText(Integer.toString(nbBP));
	}
	
	public void afficherCombinaisonOrdinateur(int[] tableauCouleurs) {
		for(int i = 0 ; i != taille ; i++) {
			this.combinaisonOrdiIHM[i].setBackground(chiffreEnCouleur(tableauCouleurs[i]));
		}
	}
	
}