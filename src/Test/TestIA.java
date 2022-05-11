package Test;

import java.util.ArrayList;

import Controleur.*;
import Modeles.*;

public class TestIA {
    private final static int TAILLE_CAMP_JOUEUR = 21;

    public static void main(String[] args) {
        int objectif = 1; // nombre de partie de test
        int nbParties = 0;
        while (nbParties != objectif) {
            // INITIALISATION DE LA PARTIE
            Joueur j1 = new Joueur("Stupid 1");
            Joueur j2 = new Joueur("Stupid 2");
            Partie partieEnCours = new Partie(j1, j2);
            int joueurCourant = 0; // Le joueur qui commence est le premier

            // DISTIBUTION DES PIONS
            partieEnCours.distribuerBlancEtNaturels(); // On donne les blancs
            while (partieEnCours.joueur1().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR
                    || partieEnCours.joueur2().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR) { // On pioche tant qu on
                                                                                                 // a pas assez de pions
                Piece p;
                if (joueurCourant == 0) {
                    p = partieEnCours.joueur1().piocherPiece(partieEnCours.getBasePieces());
                    partieEnCours.joueur1().addPiecePiochee(p);
                } else {
                    p = partieEnCours.joueur2().piocherPiece(partieEnCours.getBasePieces());
                    partieEnCours.joueur2().addPiecePiochee(p);
                }
                changementJoueurCourant(joueurCourant);
            }

            // CREATION DES PYRAMIDES

            // PHASE DE JEU
            while (!partieEnCours.estPartieFinie(joueurCourant)) { // Argument partie en cours
                Coup c;
                ArrayList<Coup> cJ = new ArrayList<Coup>();
                Acteur jCourant;
                if (joueurCourant == 0) {
                    jCourant = partieEnCours.joueur1();
                } else {
                    jCourant = partieEnCours.joueur2();
                }
                cJ = partieEnCours.coupsJouables(jCourant);

            }

            // GAGNANT

        }

    }

    public static void changementJoueurCourant(int joueurCourant) {
        if (joueurCourant == 1) {
            joueurCourant = 0;
        } else
            joueurCourant = 1;
    }
}