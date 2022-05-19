
public class ModeleMastermind {

    /**
     * combinaison ordinateur
     */
    int combinaison[];
    
    private int taille;
    
    private int nbCouleurs;
    
    private VueMastermind vue;
    
    /**
     * cree une instance ModeleMastermind
     */
    public ModeleMastermind(int nbCouleurs, int taille) {
    	this.taille = taille;
    	this.nbCouleurs = nbCouleurs;
        this.combinaison = new int[taille];
        this.genererCombinaison();
    }

    /**
     * genere aleatoirement une combinaison de taille taille dont les valeurs
     * sont comprises entre 0 et nbValeurs-1
     */
    public void genererCombinaison() {
        for (int i = 0; i < taille; i++) {
            this.combinaison[i] = (int) (nbCouleurs * Math.random() + 1);
        }
    }
    
    /**
     * renvoie la combinaison de l'ordinateur
     * 
     * @return tableau representant la combinaison
     */
    public int[] getCombinaison() {
        return (this.combinaison);
    }

    /**
     * indique le nombre de chiffres bien places dans le tableau passe en
     * parametre
     * 
     * @param tableau
     *            contenant la combinaison a verifier
     * @return nombre de chiffres bien places
     */
    public int nbChiffresBienPlaces(int tabChiffres[]) {
        int v = 0;
        for (int i = 0; i < taille; i++) {
            if (this.combinaison[i] == tabChiffres[i]) {
                v++;
            }
        }
        return v;
    }

    /**
     * indique le nombre de chiffres mal places dans le tableau passe en
     * parametre
     * 
     * @param tableau
     *            contenant la combinaison a verifier
     * @return nombre de chiffres mal places
     */
    public int nbChiffresMalPlaces(int tabChiffres[]) {
        int v = 0;
        int combinaisonAux[] = new int[taille];
        int tabChiffresAux[] = new int[taille];
        for (int i = 0; i < taille; i++) {
            combinaisonAux[i] = combinaison[i];
            tabChiffresAux[i] = tabChiffres[i];
            if (tabChiffresAux[i] == combinaisonAux[i]) {
                combinaisonAux[i] = -1;
                tabChiffresAux[i] = -2;
            }
        }
        for (int i = 0; i < taille; i++) {
            boolean trouve = false;
            for (int j = 0; j < taille && !trouve; j++) {
                if (tabChiffresAux[i] == combinaisonAux[j]) {
                    v++;
                    combinaisonAux[j] = -1;
                    tabChiffresAux[i] = -2;
                    trouve = true;
                }
            }
        }
        return v;
    }

}
