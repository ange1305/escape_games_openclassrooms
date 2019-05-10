# PROJET 3 OPENCLASSROOMS - METTEZ VOTRE LOGIQUE A L'EPREUVE


## Présentation de l'application

Il s’agit d'un jeu du plus ou moins

Le but du jeu est de deviner ou de faire deviner une combinaison de X chiffres en un nombre limité de coups.
(Nombre de chiffres et de coups définie dans le fichier config.properties).

Le jeu peut être joué selon 3 modes :

Challenger -> Vous devez deviner la combinaison.
Défenseur -> L'ordinateur doit deviner votre combinaison.
Duel -> A tour de rôle, le joueur et l'ordinateur doivent deviner la combinaison adverse avant l'adversaire.

## Compilation

Après avoir télécharger les fichiers source, ouvrez un terminal, rendez-vous dans le dossier contenant le fichier pom.xml
et lancez cette ligne de commande.

```cmd
mvn package
```

## Utilisation

Le programme se base sur le fichier config.properties pour configurer le mode développeur, le nombre de case possible
pour la combinaison secrète et le nombre d'essai. Il est possible de le modifier puis de relancer le programme pour
l’appliquer automatiquement. Par défaut, le nombre de chiffres à deviner est de quatre et le nombre de coups est de dix.
Le ficher config.properties ce trouve dans le répertoire classes.

Le mode développeur permet d'afficher les codes générés pour vérifier le bon fonctionnement.


## Lancer le jeu


Le jeu, s'utilisent en mode console.
Pour lancer le jeu, ouvrez un terminal, rendez-vous dans le dossier qui contient le projet et lancez cette ligne de commande.

```cmd
java -jar escape_games_openclassrooms-1.0-SNAPSHOT.jar
```

Au démarrage, un menu apparaitra et vous indiquera les différentes possibilités.
Il suffira de taper le bon code puis sur Entrée.
A la fin de la partie, l'utilisateur peut choisir de rejouer à ce jeu, de Jouer à un autre jeu ou d’arrêter de jouer.

# Projet3