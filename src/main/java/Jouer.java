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
     * @param nom Variable qui contien le nom de la personne qui à fait la proposition
     * @return Retourne un boolean à vrais si la solution a été trouvé
     */
    public boolean messageAnnalise(String proposition, String aTrouver, String nom){
        boolean valide = false;
        System.out.println("Proposition " + nom + ": " + proposition + " -> Réponce : " + annalise(proposition, aTrouver));
        if(proposition.equals(aTrouver))
            valide = true;
        return valide;
    }

    /**
     * Méthode pour générer une proposition en fonction de l'annaliser qui a été faite
     * @return Retourne une chaine de carractere avec la nouvelle proposition
     */
    public String propositionOrdi (){
        String proposition = "";
        /* On génére une proposition */
        if(getaProposerOrdi().equals("")){
            proposition = aTrouver(getDificulte());
            setaProposerOrdi(proposition);
        }else{
            /* On annalise le résultat que l'on a obtenue */
          //  String annalise = Static.annalise(getaProposer(), getaTrouver());
            if(!getAnnaliseOrdi().contentEquals("")) {
                /* On génére une nouvelle porposition */
                for (int j = 0; j < getaTrouver().length(); j++) {
                    String decoupeAnnalise = String.valueOf(getAnnaliseOrdi().charAt(j));
                    int propose;
                    if (decoupeAnnalise.contains("=")) {
                        setValeurProposeOrdi(j, 0, Integer.parseInt(String.valueOf(getaProposerOrdi().charAt(j))));
                        setValeurProposeOrdi(j, 1, Integer.parseInt(String.valueOf(getaProposerOrdi().charAt(j))));
                        proposition += String.valueOf(getaProposerOrdi().charAt(j));
                    } else if (decoupeAnnalise.contains("+")) {
                        setValeurProposeOrdi(j, 0, Integer.parseInt(String.valueOf(getaProposerOrdi().charAt(j))));
                        propose = aleatoireEntreMinMax(getValeurProposeOrdi(j, 0), getValeurProposeOrdi(j, 1));
                        proposition += String.valueOf(propose);
                    } else if (decoupeAnnalise.contains("-")) {
                        setValeurProposeOrdi(j, 1, Integer.parseInt(String.valueOf(getaProposerOrdi().charAt(j))));
                        propose = aleatoireEntreMinMax(getValeurProposeOrdi(j, 0), getValeurProposeOrdi(j, 1));
                        proposition += String.valueOf(propose);
                    }
                }
                setaProposerOrdi(proposition);
            }else {
                System.out.println("Faite l'annalise");
            }
        }
        return proposition;
    }

    public boolean annaliseOrdi(int dificulte) {
        boolean valide = false;
        String resultAnnalise = "";
        String regex = "(\\+|-|=)+";
        while (resultAnnalise.length() < dificulte || resultAnnalise.length() > dificulte) {
            do {
                resultAnnalise = String.valueOf(Main.sc.next());
                if (!resultAnnalise.matches(regex))
                    System.out.println("Entrez uniquement + ou - ou = commme carractère.");
            }while (!resultAnnalise.matches(regex));
            if(resultAnnalise.length() != dificulte)
                System.out.println("Vous n'avez pas correctement fais votre annalise.");
        }
        setAnnaliseOrdi(resultAnnalise);
        if(resultAnnalise.matches("={" + dificulte + "}"))
            valide = true;
        return valide;
    }
}
