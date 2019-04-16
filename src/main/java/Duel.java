package main.java;

public class Duel extends Jouer {
    public Duel() {
        System.out.println("Entrez la valeur à " + super.getDificulte() + " chifres que souhaité faire trouver à l'ordinateur.");
        super.setaTrouverOrdi(super.proposition(super.getDificulte()));
        if(super.getDevMode() == 1) {
            System.out.println("(Combinaison secrète que le joueur doit trouver : " + super.getaTrouver() + ")");
            System.out.println("(Combinaison secrète que l'ordinateur doit trouver : " + super.getaTrouverOrdi() + ")");
        }

        /* On initialise la matrice des valeurs que l'ordinateur a proposé */
        for(int k = 0; k < super.getDificulte(); k++){
            setValeurProposeOrdi(k,0, -1);
            setValeurProposeOrdi(k,1,10);
        }

        boolean okJoueur = false;
        boolean okOrdi = false;
        int i;
        for (i = 0; i < super.getNbEssai() && !okJoueur && !okOrdi; i++){
            System.out.println("Il vous reste " + (super.getNbEssai() - i) + " essais.");
            okJoueur = super.messageAnnalise(super.proposition(super.getDificulte()), super.getaTrouver(), "joueur");
            if(!okJoueur){
                super.setaProposerOrdi(super.propositionOrdi());
                System.out.println("Proposition ordinateur: " + super.getaProposerOrdi());
                okOrdi = super.annaliseOrdi(super.getDificulte());
            }
        }
        if (okJoueur)
            System.out.println("Bravo, vous avez trouver la solution en " + i + " essais.");
        else
            System.out.println("Dommage, vous n'avez pas trouvé la solution.\n" +
                    "Vous deviez trouver : " + super.getaTrouver());
        if (okOrdi)
            System.out.println("La solution a été trouvé par l'ordinateur en " + i + " essais.");
        else
            System.out.println("La solution n'a pas été trouvé par l'ordinateur.\n" +
                    "L'ordinateur devait trouver : " + super.getaTrouverOrdi());
    }
}
