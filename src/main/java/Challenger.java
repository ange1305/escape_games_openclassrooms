package main.java;

public class Challenger extends Jouer {
    public Challenger() {
        if(super.getDevMode() == 1)
            System.out.println("(Combinaison secrète : " + super.getaTrouver() + ")");

        System.out.println("Vous devez trouver une valeur à " + super.getDificulte() + " chiffre.");

        boolean solutionTrouve = false;
        int i;
        for(i = 0; i < super.getNbEssai() && !solutionTrouve; i++){
            System.out.println("Il vous reste " + (super.getNbEssai() - i) + " essais.");
            solutionTrouve = super.messageAnnalise(super.proposition(super.getDificulte()), super.getaTrouver(), "");
        }
        if (solutionTrouve)
            System.out.println("Bravo, vous avez trouver la solution " + super.getaTrouver() + " en " + i + " essais.");
        else
            System.out.println("Dommage, vous n'avez pas trouvé la solution.\n" +
                    "La solution était : " + super.getaTrouver());
    }
}
