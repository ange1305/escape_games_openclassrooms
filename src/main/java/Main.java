package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    public static Scanner sc = new Scanner(System.in);
    private int rejouer;

    public static void main(String[] args) {
        Main escapeGame = new Main();
        System.out.println("Bienvenue dans l'escape game de chez GamePlay Studio");
        do {
            int mode = escapeGame.askMode();
            do {
                switch (mode) {
                    case 1:
                        escapeGame.chalenger();
                        break;
                    case 2:
                        escapeGame.defenseur();
                        break;
                    case 3:
                        escapeGame.duel();
                        break;
                }
                escapeGame.setRejouer(escapeGame.rejouer());
            }while (escapeGame.getRejouer() == 1);
        }while (escapeGame.getRejouer() == 2);
        if(escapeGame.getRejouer() == 3)
            System.out.println("Merci d'avoir joué avec nos jeux.\nAu revoir et à bientôt.");
    }

    /* Début des Getter et des Setter */
    public int getRejouer() {
        return rejouer;
    }

    public void setRejouer(int rejouer) {
        this.rejouer = rejouer;
    }
    /* Fin des Getter et des Setter */

    /**
     * Méthode pour les parties jouées en temps que chalenger
     */
    private void chalenger(){
        Jouer challenger = new Challenger();
        if(challenger.getDevMode() == 1)
            System.out.println("(Combinaison secrète : " + challenger.getaTrouver() + ")");

        System.out.println("Vous devez trouver une valeur à " + challenger.getDificulte() + " chiffre.");

        boolean solutionTrouve = false;
        int i;
        for(i = 0; i < challenger.getNbEssai() && !solutionTrouve; i++){
            System.out.println("Il vous reste " + (challenger.getNbEssai() - i) + " essais.");
            solutionTrouve = challenger.messageAnnalise(challenger.proposition(challenger.getDificulte()), challenger.getaTrouver(), "");
        }
        if (solutionTrouve)
            System.out.println("Bravo, vous avez trouver la solution " + challenger.getaTrouver() + " en " + i + " essais.");
        else
            System.out.println("Dommage, vous n'avez pas trouvé la solution.\n" +
                    "La solution était : " + challenger.getaTrouver());
    }

    /**
     * Méthode pour les parties jouées en temps que défenseur
     */
    private void defenseur(){
        Jouer defenseur = new Defenseur();
        System.out.println("Entrez la valeur à " + defenseur.getDificulte() + " chifres que souhaité faire trouver à l'ordinateur.");
        defenseur.setaTrouverOrdi(defenseur.proposition(defenseur.getDificulte()));
        if(defenseur.getDevMode() == 1)
            System.out.println("(Combinaison secrète pour l'ordinateur : " + defenseur.getaTrouverOrdi() + ")");

        /* On initialise la matrice des valeurs que l'ordinateur a proposé */
        for(int k = 0; k < defenseur.getDificulte(); k++){
            defenseur.setValeurProposeOrdi(k,0, -1);
            defenseur.setValeurProposeOrdi(k,1,10);
        }

        boolean solutionTrouve = false;

        int i;
        for(i = 0; i < defenseur.getNbEssai() && !solutionTrouve; i++){
            System.out.println("Il reste " + (defenseur.getNbEssai() - i) + " essais.");
            defenseur.setaProposerOrdi(defenseur.propositionOrdi());
            System.out.println("Proposition : " + defenseur.getaProposerOrdi());
            solutionTrouve = defenseur.annaliseOrdi(defenseur.getDificulte());
        }
        if (solutionTrouve)
            System.out.println("La solution a été trouvé en " + i + " essais.");
        else
            System.out.println("La solution n'a pas été trouvé.\n" +
                    "La solution était : " + defenseur.getaTrouverOrdi());
    }

    /**
     * Méthode pour les parties jouées en duel contre l'ordinateur
     */
    private void duel(){
        Jouer duel = new Duel();
        System.out.println("Entrez la valeur à " + duel.getDificulte() + " chifres que souhaité faire trouver à l'ordinateur.");
        duel.setaTrouverOrdi(duel.proposition(duel.getDificulte()));
        if(duel.getDevMode() == 1) {
            System.out.println("(Combinaison secrète que le joueur doit trouver : " + duel.getaTrouver() + ")");
            System.out.println("(Combinaison secrète que l'ordinateur doit trouver : " + duel.getaTrouverOrdi() + ")");
        }

        /* On initialise la matrice des valeurs que l'ordinateur a proposé */
        for(int k = 0; k < duel.getDificulte(); k++){
            duel.setValeurProposeOrdi(k,0, -1);
            duel.setValeurProposeOrdi(k,1,10);
        }

        boolean okJoueur = false;
        boolean okOrdi = false;

        int i;
        for (i = 0; i < duel.getNbEssai() && !okJoueur && !okOrdi; i++){
            System.out.println("Il vous reste " + (duel.getNbEssai() - i) + " essais.");
            okJoueur = duel.messageAnnalise(duel.proposition(duel.getDificulte()), duel.getaTrouver(), "joueur");
            if(!okJoueur){
                duel.setaProposerOrdi(duel.propositionOrdi());
                System.out.println("Proposition ordinateur: " + duel.getaProposerOrdi());
                okOrdi = duel.annaliseOrdi(duel.getDificulte());
            }
        }
        if (okJoueur)
            System.out.println("Bravo, vous avez trouver la solution en " + i + " essais.");
        else
            System.out.println("Dommage, vous n'avez pas trouvé la solution.\n" +
                    "Vous deviez trouver : " + duel.getaTrouver());
        if (okOrdi)
            System.out.println("La solution a été trouvé par l'ordinateur en " + i + " essais.");
        else
            System.out.println("La solution n'a pas été trouvé par l'ordinateur.\n" +
                    "L'ordinateur devait trouver : " + duel.getaTrouverOrdi());
    }

    /**
     * Pour poser question choix mode de jeu
     * @return retourne un entier
     */
    private int askMode() {
        String[] mode = {"Challenger", "Défenseur", "Duel"};
        return this.askSomething("mode", mode);
    }

    /**
     * Signature pour afficher les réponces en fonction de la gatégorie
     * @param category la categorie choisi
     * @param responses les réponces qui sont données
     * @return le nombre choisi dans la selection
     */
    private int askSomething(String category, String[] responses) {
        System.out.println("Choix du " + category);
        for(int i = 1; i <= responses.length; i++)
            System.out.println(i + " - " + responses[i - 1]);
        System.out.println("Que souhaitez vous comme " + category + "?");
        int nbReponse = 0;
        boolean responseIsGood;
        do{
            try {
                nbReponse = sc.nextInt();
                responseIsGood = (nbReponse >= 1 && nbReponse <= responses.length);
            } catch ( InputMismatchException e) {
                sc.next();
                responseIsGood = false;
            }
            if(responseIsGood){
                String choice = "Vous avez choisi le " + category + " " + responses[nbReponse - 1];
                System.out.println(choice);
            } else {
                boolean estVoyelle = "aeiouy".contains(Character.toString(category.charAt(0)));
                if(estVoyelle)
                    System.out.println("Vous n'avez pas choisi d'" + category + " parmi les choix proposés");
                else
                    System.out.println("Vous n'avez pas choisi de " + category + " parmi les choix proposés");
            }
        } while (!responseIsGood);
        return nbReponse;
    }

    /**
     * Méthode pour la posser la question de si l'on souhaite rejouer
     * @return retourne un entier
     */
    private int rejouer(){
        int choix = 0;
        boolean responseIsGood;
        do {
            System.out.println("Que souhaitez vous faire ?");
            String[] listeChoix = {"Rejouer à ce jeu", "Jouer à un autre jeu", "Arrêter de jouer"};
            for(int i = 1; i <= listeChoix.length; i++)
                System.out.println(i + " - " + listeChoix[i - 1]);
            try {
                choix = sc.nextInt();
                responseIsGood = (choix >= 1 && choix <= listeChoix.length);
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
            }
            if(!responseIsGood)
                System.out.println("Votre choix n'est pas présent dans la liste");

        } while (!responseIsGood);
        return choix;
    }
}
