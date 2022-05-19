import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurMastermind implements ActionListener{
	
	private VueMastermind vue;
	private int nbCouleurs;
	private int taille;
	private int indiceCombinaisonUtilisateurY;
	private int [] combinaisonOrdi;
	private int [] combinaisonJoueur;
	private ModeleMastermind modele;
	private Color couleurChoisie;

	public ControleurMastermind(VueMastermind vue) {
		this.vue = vue;
		this.nbCouleurs = vue.getNbCouleurs();
		this.taille = vue.getTaille();
		this.modele = new ModeleMastermind(nbCouleurs, taille);
		this.modele.genererCombinaison();
		this.combinaisonOrdi = modele.getCombinaison();
		indiceCombinaisonUtilisateurY = 0;
		
		System.out.println(this.combinaisonOrdi[0]+""+this.combinaisonOrdi[1]+""+this.combinaisonOrdi[2]);
	}

	public void actionPerformed(ActionEvent e) {
		
		JButton  b = (JButton)e.getSource();
		
		//Si le boutton est une couleurs
		if(this.vue.appartientPalette(b)) {
			this.couleurChoisie = b.getBackground();
		}
		
		//Si le boutton est une combinaison du joueurs
		if(this.vue.appartientCombinaison(b, indiceCombinaisonUtilisateurY)) {
			b.setBackground(couleurChoisie);
		}
		
		//Si le boutton est Valider
		if(b == this.vue.btSouthValider) {
			if(this.vue.combinaisonComplete(indiceCombinaisonUtilisateurY)) {
				this.combinaisonJoueur = this.vue.combinaisonEnEntier(indiceCombinaisonUtilisateurY);
				this.vue.afficherBP(indiceCombinaisonUtilisateurY, this.modele.nbChiffresBienPlaces(combinaisonJoueur));
				this.vue.afficherMP(indiceCombinaisonUtilisateurY, this.modele.nbChiffresMalPlaces(combinaisonJoueur));

				System.out.println(this.combinaisonJoueur[0]+""+this.combinaisonJoueur[1]+""+this.combinaisonJoueur[2]);
				if(this.modele.nbChiffresBienPlaces(combinaisonJoueur) == this.taille || indiceCombinaisonUtilisateurY == 9) {
					this.vue.desactiverCombinaison(indiceCombinaisonUtilisateurY);
					this.vue.afficherCombinaisonOrdinateur(combinaisonOrdi);
					
				} else if(this.combinaisonJoueur != this.combinaisonOrdi) {
					this.vue.desactiverCombinaison(indiceCombinaisonUtilisateurY);
					indiceCombinaisonUtilisateurY ++;
					this.vue.activerCombinaison(indiceCombinaisonUtilisateurY);
				}
				
			}
		}
	}
	
}