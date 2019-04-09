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
}
