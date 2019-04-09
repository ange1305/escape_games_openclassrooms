package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Jouer {
    private final Path config = Paths.get("./src/main/resources/config.properties"); // Emplacement fichier configuration
    private final int devMode = this.devMode();
    private final int dificulte = this.dificulte();
    private final int nbEssai = this.nbEssai();
    private String aTrouver  = this.aTrouver(getDificulte());

    /* Début des Getter et des Setter */
    public int getDevMode() {
        return devMode;
    }

    public int getDificulte() {
        return dificulte;
    }

    public int getNbEssai() {
        return nbEssai;
    }

    public String getaTrouver() {
        return aTrouver;
    }

    public void setaTrouver(String aTrouver) {
        this.aTrouver = aTrouver;
    }
    /* Fin des Getter et des Setter */

    /**
     * Méthode pour savoir si on est en mode developeur ou non
     * @return retourne un entier "1 : mode developeur activé"
     */
    private int devMode (){
        int retour = 0;
        if(Files.exists(this.config)){
            List<String> ligne = null;
            try {
                ligne = Files.readAllLines(this.config);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < ligne.size(); i++){
                String traitement;
                String[] traitement2;
                traitement = ligne.get(i);
                traitement2 = traitement.split(" : ");
                if(traitement2[0].equals("devmode")){
                    if(traitement2[1].equals("on"))
                        retour = 1;
                }
            }
        } else
            System.err.println("Le fichier n'existe pas.");
        return retour;
    }

    /**
     * Méthode pour savoir la dificulté du jeu (nombre de carractere que l'on devra trouver)
     * @return retourne un entier
     */
    private int dificulte() {
        int retour = 0;
        if(Files.exists(this.config)){
            List<String> ligne = null;
            try {
                ligne = Files.readAllLines(this.config);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < ligne.size(); i++){
                String traitement;
                String[] traitement2;
                traitement = ligne.get(i);
                traitement2 = traitement.split(" : ");
                if(traitement2[0].equals("dificulte")) {
                    retour = Integer.parseInt(traitement2[1]);
                }
            }
        } else
            System.err.println("Le fichier n'existe pas.");
        return retour;
    }

    /**
     * Méthode pour savoir le nombre d'essais qu'a le joueur pour trouver la solution
     * @return retourne un entier
     */
    private int nbEssai() {
        int retour = 0;
        String temp = null;
        if(Files.exists(this.config)){
            List<String> ligne = null;
            try {
                ligne = Files.readAllLines(this.config);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < ligne.size(); i++){
                String traitement;
                String[] traitement2;
                traitement = ligne.get(i);
                traitement2 = traitement.split(" : ");
                if(traitement2[0].equals("nbessai")) {
                    retour = Integer.parseInt(traitement2[1]);
                }
            }
        } else
            System.err.println("Le fichier n'existe pas.");
        return retour;
    }

    /**
     * Méthode pour générer un nombre aléatoire entre un minimum et un maximum qui ne sont pas compris dedans (min < x < max)
     * @param min Valeur minimum à trouver
     * @param max Valeur maximum à trouver
     * @return Retourne un entier
     */
    private int aleatoireEntreMinMax(int min, int max){
        int x = (int)((Math.random()*((max-1)-(min+1)))+(min+1));
        return x;
    }

    /**
     * Méthode qui génére le code a trouver en fonction de la dificulté qui est inscrite dans le fichier config.properties
     * @return retourne le code sous forme d'une chaine de carractere
     */
    private String  aTrouver(int dificulte){
        String aTrouver = "";
        for(int i = 0; i < dificulte; i++){
            aTrouver += String.valueOf(this.aleatoireEntreMinMax(-1, 10));
        }
        return aTrouver;
    }

    /**
     * Méthode pour la saisi clavier
     * @return Retourne une chaine de carractère
     */
    public String proposition(int dificulte){
        String proposition = "";
        while (proposition.length() < dificulte || proposition.length() > dificulte){
            do{
                proposition = String.valueOf(Main.sc.next());
                if(!proposition.matches("\\p{Digit}+"))
                    System.out.println("Entrez uniquement des chiffres.");
            }while (!proposition.matches("\\p{Digit}+"));
            if(proposition.length() != dificulte)
                System.out.println("Vous n'avez pas rentré une valeur à " + dificulte + " de chiffre.\nEntrez une valeur à " + dificulte + " chifres.");
        }
        return proposition;
    }

    /**
     * Méthode pour annaliser la proposition
     * @param aAnnaliser Chaine de carractere que l'on doit annaliser
     * @param aTrouver Chaine de carractere que l'ordinateur doit trouver
     * @return Retourne une chaine de carractere (= si bon, + si doit trouver plus, - si doit trouver moins)
     */
    public String annalise(String aAnnaliser, String aTrouver){
        String annalise = "";
        for (int i = 0; i < aTrouver.length(); i++) {
            if (aAnnaliser.charAt(i) == aTrouver.charAt(i)) {
                annalise += "=";
            } else if (aAnnaliser.charAt(i) < aTrouver.charAt(i)) {
                annalise += "+";
            } else if (aAnnaliser.charAt(i) > aTrouver.charAt(i)) {
                annalise += "-";
            }
        }
        return annalise;
    }

    /**
     * Méthode pour afficher le résultat de l'annalise
     * @param proposition Variable qui contien la chaine que l'ordinateur propose
     * @param aTrouver Variable qui contien la chaine que l'on souhaite faire trouver à l'ordinateur
     * @return Retourne un boolean à vrais si la solution a été trouvé
     */
    public boolean messageAnnalise(String proposition, String aTrouver, String nom){
        boolean valide = false;
        System.out.println("Proposition " + nom + ": " + proposition + " -> Réponce : " + annalise(proposition, aTrouver));
        if(proposition.equals(aTrouver))
            valide = true;
        return valide;
    }
}
