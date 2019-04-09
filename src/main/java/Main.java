package main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    private int rejouer;

    public static void main(String[] args) {
        Main escapeGame = new Main();
        do {
            System.out.println("Bienvenue dans l'escape game de chez GamePlay Studio");
            int mode = escapeGame.askMode();
            do {
                switch (mode) {
                    case 1:
                        Jouer challenger = new Challenger();
                        break;
                    case 2:
                        Jouer defenseur = new Defenseur();
                        break;
                    case 3:
                        Jouer duel = new Duel();
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
            String[] listeChoix = {"Rejouer à ce jeu", "Jouer à un autre jeu", "Arreter de jouer"};
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
