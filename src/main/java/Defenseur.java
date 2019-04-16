package main.java;

public class Defenseur extends Jouer {
    public Defenseur() {
        System.out.println("Entrez la valeur à " + super.getDificulte() + " chifres que souhaité faire trouver à l'ordinateur.");
        super.setaTrouverOrdi(super.proposition(super.getDificulte()));
        if(super.getDevMode() == 1)
            System.out.println("(Combinaison secrète pour l'ordinateur : " + super.getaTrouverOrdi() + ")");

        /* On initialise la matrice des valeurs que l'ordinateur a proposé */
        for(int k = 0; k < super.getDificulte(); k++){
            setValeurProposeOrdi(k,0, -1);
            setValeurProposeOrdi(k,1,10);
        }

        boolean solutionTrouve = false;
        int i;
        for(i = 0; i < super.getNbEssai() && !solutionTrouve; i++){
            System.out.println("Il reste " + (super.getNbEssai() - i) + " essais.");
            super.setaProposerOrdi(super.propositionOrdi());
            System.out.println("Proposition : " + super.getaProposerOrdi());
            solutionTrouve = super.annaliseOrdi(super.getDificulte());
        }
        if (solutionTrouve)
            System.out.println("La solution a été trouvé en " + i + " essais.");
        else
            System.out.println("La solution n'a pas été trouvé.\n" +
                    "La solution était : " + getaTrouver());
    }
}
