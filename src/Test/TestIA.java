package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Modeles.*;

public class TestIA {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Combien de parties voulez vous simuler ? ");
        int objectif = s.nextInt(); // nombre de parties de test
        System.out.println("Quel difficulte pour l'IA 1 ? [0,1,2]");
        int diff1 = s.nextInt();
        System.out.println("Quel difficulte pour l'IA 2 ? [0,1,2]");
        int diff2 = s.nextInt();
        int nbParties = 0;
        int victoirej1 = 0;
        int victoirej2 = 0;
        final int TAILLE_CAMP_JOUEUR = 21;
        final boolean AFFICHAGE=false;

        // Chronometre du temps de toutes les parties
        double t1 = (double) System.currentTimeMillis();

        Partie ktrois = null;
        while (nbParties != objectif) { // On créer des parties jusqu a avoir le bon nombre de partie jouer

            // INITIALISATION DE LA PARTIE
            Acteur j1 = new IAActeur("IA 1", diff1, 0);
            Acteur j2 = new IAActeur("IA 2", diff2, 1);

            ktrois = new Partie(j1, j2, nbParties);// numero de partie
            // Modifier le premier joueur qui commence
            // ktrois.joueurCourant = 0; A TESTER
            // ktrois.joueurDebut = 0;

            // DISTIBUTION DES PIONS
            ktrois.distribuerBlancEtNaturels(); // On donne les blancs
            while (ktrois.joueur1().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR
                    || ktrois.joueur2().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR) {
                piocher(ktrois);
            }

            // CREATION DES PYRAMIDES
            if(AFFICHAGE) {
	            System.out.println("Le joueur numero " + (ktrois.joueurDebut + 1) + "commence la partie");
	            System.out.println("Phase de Creation");
	            System.out.println("Pyramide de jeu");
	            System.out.println(ktrois.getBaseMontagne().toString());
            }

            // A RETESTER
            if (ktrois.joueurDebut == 0) {
                ArrayList<PiecePyramide> piecesj1 = ktrois.joueur1().phase1(ktrois);

                for (PiecePyramide piece : piecesj1) { // a voir si mieux qu'utiliser des iterateurs
                    ktrois.joueur1().getCamp().empiler(piece);
                    ktrois.joueur1().getPiecesPiochees().remove(piece.getPiece());
                }

                ArrayList<PiecePyramide> piecesj2 = ktrois.joueur2().phase1(ktrois);

                for (PiecePyramide piece : piecesj2) { // a voir si mieux qu'utiliser des iterateurs
                    ktrois.joueur2().getCamp().empiler(piece);
                    ktrois.joueur2().getPiecesPiochees().remove(piece.getPiece());
                }
            } else {
                ArrayList<PiecePyramide> piecesj2 = ktrois.joueur2().phase1(ktrois);

                for (PiecePyramide piece : piecesj2) { // a voir si mieux qu'utiliser des iterateurs
                    ktrois.joueur2().getCamp().empiler(piece);
                    ktrois.joueur2().getPiecesPiochees().remove(piece.getPiece());
                }

                ArrayList<PiecePyramide> piecesj1 = ktrois.joueur1().phase1(ktrois);

                for (PiecePyramide piece : piecesj1) { // a voir si mieux qu'utiliser des iterateurs
                    ktrois.joueur1().getCamp().empiler(piece);
                    ktrois.joueur1().getPiecesPiochees().remove(piece.getPiece());
                }
            }
            if(AFFICHAGE) {
            System.out.println(
                    "Nombre de pieces dans la pioche du joueur 1: " + ktrois.joueur1().getPiecesPiochees().size());
            System.out.println("Boolean camp j1 " + ktrois.joueur1().valideCamp);
            System.out.println(
                    "Nombre de pieces dans la pioche du joueur 2: " + ktrois.joueur2().getPiecesPiochees().size());
            System.out.println("Boolean camp j2 " + ktrois.joueur2().valideCamp);

            // AFFICHAGE PYRA JOUEUR ET MONTAGNE
            System.out.println("Pyramide de " + ktrois.joueur1().getNom());
            System.out.println(ktrois.joueur1().getCamp().toString());
            System.out.println("Liste des pieces volees: " + ktrois.joueur1().toStringPiecesVolees());
            System.out.println("Pyramide de " + ktrois.joueur2().getNom());
            System.out.println(ktrois.joueur2().getCamp().toString());
            System.out.println("Liste des pieces volees: " + ktrois.joueur2().toStringPiecesVolees());
            System.out.println("Pyramide de jeu");
            System.out.println(ktrois.getBaseMontagne().toString());

            // PHASE DE JEU
            System.out.println();
            System.out.println("Phase de jeu");
            }
            while (!ktrois.estPartieFinie()) { // Argument partie en cours
                Coup c;
                Acteur jCourant;

                if (ktrois.joueurCourant == 0) {
                    jCourant = ktrois.joueur1();
                } else {
                    jCourant = ktrois.joueur2();
                }
                ArrayList<Coup> arr = ktrois.coupsJouables(jCourant);
                if (ktrois.joueurCourant == 0) {
                    c = jCourant.jouer(arr, ktrois);
                } else {
                    c = jCourant.jouer(arr, ktrois);
                }
                if(AFFICHAGE) {
	                System.out.print("Le joueur " + jCourant.getNom() + " joue le coup ");
	                System.out.println(c.toString());
                }

                // Joue
                if (ktrois.IAjoueCoup(c, ktrois.getJoueurCourant())) { // On peut se faire voler une piece
                    jCourant.addMauvaisCoup();
                    if(AFFICHAGE) {
                    	System.out.println("on recupere les pieces volables de " + jCourant.getNom());
                    }
                    ArrayList<PiecePyramide> accessibles = jCourant.getPiecesJouables();
                    if (ktrois.getJoueurCourant() == 0) {
                        jCourant = ktrois.joueur2();
                    } else {
                        jCourant = ktrois.joueur1();
                    }
                    PiecePyramide vol = jCourant.choixVol(accessibles, ktrois);
                    ktrois.IAvol(vol, ktrois.joueurCourant);
                    jCourant.addVol();
                } else {
                    if (c.getPosBase() == null) {
                        jCourant.addBlancJoue();
                    }
                }

                // Afiichage
                if(AFFICHAGE) {
	                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	                System.out.println("Pyramide de " + ktrois.joueur1().getNom());
	                System.out.println(ktrois.joueur1().getCamp().toString());
	                System.out.println("Liste des pieces volees: " + ktrois.joueur1().toStringPiecesVolees());
	                System.out.println("Pyramide de " + ktrois.joueur2().getNom());
	                System.out.println(ktrois.joueur2().getCamp().toString());
	                System.out.println("Liste des pieces volees: " + ktrois.joueur2().toStringPiecesVolees());
	                System.out.println("Pyramide de jeu");
	                System.out.println(ktrois.getBaseMontagne().toString());
                }

                // changer le joueur courant
                ktrois.changementJoueurCourant();
            }

            // GAGNANT
            System.out.println("Partie numero "+nbParties);
            int gagnant;
            if (ktrois.getBaseMontagne().estPleine()) { // Si egalite
                gagnant = 2;
            } else {
                if (ktrois.getJoueurCourant() == 0) {
                    gagnant = 1;
                } else {
                    gagnant = 0;
                }
                if (ktrois.getJoueurCourant() == 0) {
                    victoirej2++;
                } else {
                    victoirej1++;
                }
            }
            nbParties++;
            ktrois.sauvegarderStatsPartie(gagnant);
        }
        s.close();
        ktrois.combinerStats(0, objectif);
        System.out.println("Nombre de parties jouees : " + objectif);
        System.out.println("Taux de victoire du joueur 1 : " + ((double) victoirej1 * 100 / ((double) objectif)) + "%");
        System.out.println("Taux de victoire du joueur 2 : " + ((double) victoirej2 * 100 / ((double) objectif)) + "%");
        double t2 = (double) System.currentTimeMillis();
        System.out.println("Temps moyen d'une partie : " + (t2 - t1) / (objectif * 1000) + " s");
        System.out.println("Temps total de toutes les parties : " + ((t2 - t1) / 1000) + " s");
    }

    public static void affiche(ArrayList<Coup> l) {
        Iterator<Coup> it = l.iterator();
        System.out.println("Coups possibles");
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
        System.out.println();
    }

    public static void piocher(Partie partieEnCours) {
        Piece p;
        if (partieEnCours.getJoueurCourant() == 0) {
            p = partieEnCours.joueur1().piocherPiece(partieEnCours.getBasePieces());
            partieEnCours.joueur1().addPiecePiochee(p);
        } else {
            p = partieEnCours.joueur2().piocherPiece(partieEnCours.getBasePieces());
            partieEnCours.joueur2().addPiecePiochee(p);
        }
        partieEnCours.changementJoueurCourant();
    }

}