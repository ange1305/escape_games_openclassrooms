package main.java;

public class Challenger extends Jouer {
    public Challenger() {
    }

    /**
     * Méthode pour générer un nombre aléatoire entre un minimum et un maximum qui ne sont pas compris dedans (min < x < max)
     * @param min Valeur minimum à trouver
     * @param max Valeur maximum à trouver
     * @return Retourne un entier
     */
    @Override
    public int aleatoireEntreMinMax(int min, int max){
        int x = (int)((Math.random()*((max-1)-(min+1)))+(min+1));
        return x;
    }

    /**
     * Méthode qui génére le code a trouver en fonction de la dificulté qui est inscrite dans le fichier config.properties
     * @return retourne le code sous forme d'une chaine de carractere
     */
    @Override
    public String  aTrouver(int dificulte){
        String aTrouver = "";
        for(int i = 0; i < dificulte; i++){
            aTrouver += String.valueOf(aleatoireEntreMinMax(-1, 10));
        }
        return aTrouver;
    }

    /**
     * Méthode pour la saisi clavier
     * @param dificulte Variable qui renseigne sur la dificulté
     * @return Retourne une chaine de carractère
     */
    @Override
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
    @Override
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
    @Override
    public boolean messageAnnalise(String proposition, String aTrouver, String nom){
        boolean valide = false;
        System.out.println("Proposition " + nom + ": " + proposition + " -> Réponse : " + annalise(proposition, aTrouver));
        if(proposition.equals(aTrouver))
            valide = true;
        return valide;
    }

    /**
     * Méthode pour générer une proposition en fonction de l'annaliser qui a été faite
     * @return Retourne une chaine de carractere avec la nouvelle proposition
     */
    @Override
    public String propositionOrdi (){
        String proposition = "";
        /* On génére une premiere proposition avec que des 5*/
        if(getaProposerOrdi().equals("")){
            int propose;
            for(int i=0; i<getDificulte(); i++){
                propose = aleatoireEntreMinMax(4,6);
                proposition += String.valueOf(propose);
            }
            setaProposerOrdi(proposition);
        }else{
            /* On annalise le résultat que l'on a obtenue */
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

    /**
     * Méthode qui permet de renseigner à l'ordinateur s'il doit trouver un valeur plus grande, plus petite ou égal
     * et de s'assurer qu'on a bien rentrer le bon nombre de carractere (+, -, =)
     * @param dificulte Variable qui renseigne sur la dificulté
     * @return Retourne un boolean à vrais si on a fait une bonne annalise (nombre egal de carractere)
     */
    @Override
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
