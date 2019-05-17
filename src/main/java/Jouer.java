package main.java;

public abstract class Jouer {
    private final int devMode = RecupConfig.devMode();
    private final int dificulte = RecupConfig.dificulte();
    private final int nbEssai = RecupConfig.nbEssai();
    private String aTrouver  = this.aTrouver(getDificulte());
    private String aTrouverOrdi;
    private String aProposerOrdi = "";
    private int[][] valeurProposeOrdi = new int[getDificulte()][2];
    private String annaliseOrdi = "";

    /* Début des Getter et des Setter */
    public int getDevMode() { return devMode; }

    public int getDificulte() { return dificulte; }

    public int getNbEssai() { return nbEssai; }

    public String getaTrouver() { return aTrouver; }

    public String getaTrouverOrdi() { return aTrouverOrdi; }

    public String getaProposerOrdi() { return aProposerOrdi; }

    public int getValeurProposeOrdi(int a, int b) { return valeurProposeOrdi[a][b]; }

    public String getAnnaliseOrdi() { return annaliseOrdi; }

    public void setaTrouver(String aTrouver) { this.aTrouver = aTrouver; }

    public void setaTrouverOrdi(String aTrouverOrdi) { this.aTrouverOrdi = aTrouverOrdi; }

    public void setaProposerOrdi(String aProposerOrdi) { this.aProposerOrdi = aProposerOrdi; }

    public void setValeurProposeOrdi(int a, int b, int valeurProposeOrdi) { this.valeurProposeOrdi[a][b] = valeurProposeOrdi; }

    public void setAnnaliseOrdi(String annaliseOrdi) { this.annaliseOrdi = annaliseOrdi; }

    /* Fin des Getter et des Setter */

    /**
     * Méthode pour générer un nombre aléatoire entre un minimum et un maximum qui ne sont pas compris dedans (min < x < max)
     * @param min Valeur minimum à trouver
     * @param max Valeur maximum à trouver
     * @return Retourne un entier
     */
    public abstract int aleatoireEntreMinMax(int min, int max);

    /**
     * Méthode qui génére le code a trouver en fonction de la dificulté qui est inscrite dans le fichier config.properties
     * @return retourne le code sous forme d'une chaine de carractere
     */
    public abstract String aTrouver(int dificulte);

    /**
     * Méthode pour la saisi clavier
     * @param dificulte Variable qui renseigne sur la dificulté
     * @return Retourne une chaine de carractère
     */
    public abstract String proposition(int dificulte);

    /**
     * Méthode pour annaliser la proposition
     * @param aAnnaliser Chaine de carractere que l'on doit annaliser
     * @param aTrouver Chaine de carractere que l'ordinateur doit trouver
     * @return Retourne une chaine de carractere (= si bon, + si doit trouver plus, - si doit trouver moins)
     */
    public abstract String annalise(String aAnnaliser, String aTrouver);

    /**
     * Méthode pour afficher le résultat de l'annalise
     * @param proposition Variable qui contien la chaine que l'ordinateur propose
     * @param aTrouver Variable qui contien la chaine que l'on souhaite faire trouver à l'ordinateur
     * @param nom Variable qui contien le nom de la personne qui à fait la proposition
     * @return Retourne un boolean à vrais si la solution a été trouvé
     */
    public abstract boolean messageAnnalise(String proposition, String aTrouver, String nom);

    /**
     * Méthode pour générer une proposition en fonction de l'annaliser qui a été faite
     * @return Retourne une chaine de carractere avec la nouvelle proposition
     */
    public abstract String propositionOrdi ();

    /**
     * Méthode qui permet de renseigner à l'ordinateur s'il doit trouver un valeur plus grande, plus petite ou égal
     * et de s'assurer qu'on a bien rentrer le bon nombre de carractere (+, -, =)
     * @param dificulte Variable qui renseigne sur la dificulté
     * @return Retourne un boolean à vrais si on a fait une bonne annalise (nombre egal de carractere)
     */
    public abstract boolean annaliseOrdi(int dificulte);
}
