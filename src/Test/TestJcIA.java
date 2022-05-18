package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Modeles.*;

public class TestJcIA {

    public static void main(String[] args) {
        int objectif = 1; // nombre de parties de test
        int nbParties = 0;
        int victoirej1 = 0;
        int victoirej2 = 0;
        int matchnull = 0;
        final int TAILLE_CAMP_JOUEUR = 21;

        // Chronometre du temps de toutes les parties
        double t1 = (double) System.currentTimeMillis();

        Partie ktrois = null;
        while (nbParties != objectif) { // On créer des parties jusqu a avoir le bon nombre de partie jouer

            // INITIALISATION DE LA PARTIE
            Scanner s = new Scanner(System.in);
            System.out.print("Quel est votre nom? ");
            String name = s.nextLine();
            Acteur j1 = new Joueur(name);
            Acteur j2 = new IAActeur("IA", 1, 1);

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
            System.out.println("Le joueur numero " + (ktrois.joueurDebut + 1) + " commence la partie");
            System.out.println("Phase de Création");
            System.out.println("Pyramide de jeu");
            System.out.println(ktrois.getBaseMontagne().toString());

            while (ktrois.joueur1().getTaillePiecesPiochees() > 0 && ktrois.joueur2().getTaillePiecesPiochees() > 0) {
                construirepyraJ(ktrois, s);
                construirepyraIA(ktrois);
            }
            // AFFICHAGE PYRA JOUEUR ET MONTAGNE
            System.out.println("Pyramide de " + ktrois.joueur1().getNom());
            System.out.println(ktrois.joueur1().getCamp().toString());
            System.out.println("Liste des pieces volées: " + ktrois.joueur1().toStringPiecesVolees());
            System.out.println("Pyramide de " + ktrois.joueur2().getNom());
            System.out.println(ktrois.joueur2().getCamp().toString());
            System.out.println("Liste des pieces volées: " + ktrois.joueur2().toStringPiecesVolees());
            System.out.println("Pyramide de jeu");
            System.out.println(ktrois.getBaseMontagne().toString());

            // PHASE DE JEU
            System.out.println();
            System.out.println("Phase de jeu");
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
                    c = choixCoup(ktrois, s);
                } else {
                    c = jCourant.jouer(arr, ktrois);
                }
                System.out.print("Le joueur " + jCourant.getNom() + " joue le coup");
                System.out.println(c.toString());

                // Joue
                if (ktrois.IAjoueCoup(c, ktrois.getJoueurCourant())) { // On peut se faire voler une piece
                    System.out.println("on recuppere les pieces volables de " + jCourant.getNom());
                    ArrayList<PiecePyramide> accessibles = jCourant.getPiecesJouables();
                    if (ktrois.getJoueurCourant() == 0) {
                        jCourant = ktrois.joueur2();
                    } else {
                        jCourant = ktrois.joueur1();
                    }
                    PiecePyramide vol = jCourant.choixVol(accessibles);
                    ktrois.IAvol(vol, ktrois.joueurCourant);
                }

                // Afiichage
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Pyramide de " + ktrois.joueur1().getNom());
                System.out.println(ktrois.joueur1().getCamp().toString());
                System.out.println("Liste des pieces volées: " + ktrois.joueur1().toStringPiecesVolees());
                System.out.println("Pyramide de " + ktrois.joueur2().getNom());
                System.out.println(ktrois.joueur2().getCamp().toString());
                System.out.println("Liste des pieces volées: " + ktrois.joueur2().toStringPiecesVolees());
                System.out.println("Pyramide de jeu");
                System.out.println(ktrois.getBaseMontagne().toString());

                // changer le joueur courant
                ktrois.changementJoueurCourant();
            }

            // GAGNANT
            if (ktrois.getJoueurCourant() == 0) {
                victoirej2++;
            } else {
                victoirej1++;
            }
            nbParties++;
            s.close();
        }
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

    public static void construirepyraJ(Partie ktrois, Scanner s) {
        // Créer liste des positions
        Acteur j1 = ktrois.joueur1();
        ArrayList<Position> allpos = new ArrayList<>();
        for (int etage = 0; etage < 6; etage++) {
            for (int rang = 0; rang < (6 - etage); rang++) {
                allpos.add(new Position(etage, rang));
            }
        }

        Iterator<Position> it = allpos.iterator();
        System.out.println("Le joueur " + j1.getNom() + " doit choisir comment empiler ses pieces:");
        while (it.hasNext()) {
            Position pos = it.next();
            System.out.println("Pyramide de " + ktrois.joueur1().getNom());
            System.out.println(ktrois.joueur1().getCamp().toString());
            System.out.println("Choix pour la position " + pos.toString());
            PiecePyramide pp = new PiecePyramide(demandPiece(j1, s), pos);
            j1.getCamp().empiler(pp);
        }
    }

    public static void construirepyraIA(Partie encours) {
        // empiler en fonction ia.phase1
        ArrayList<PiecePyramide> pieces = encours.joueur2().phase1(encours);

        for (PiecePyramide piece : pieces) { // a voir si mieux qu'utiliser des iterateurs
            encours.joueur2().getCamp().empiler(piece);
            encours.joueur2().getPiecesPiochees().remove(piece.getPiece());
        }
    }

    public static Piece demandPiece(Acteur j1, Scanner s) {
        // lister les pieces restantes
        ArrayList<Piece> posables = j1.getPiecesPiochees();
        int compt = 0;
        int res = 0; // par default premiere piece possible
        for (Piece p : posables) {
            System.out.println("Piece " + p.toString() + "posables. Numero [" + compt + "]");
            compt++;
        }
        // demander un numero a l'utilisateur
        try {
            System.out.print("Quel piece voulez vous jouez? ");
            res = s.nextInt();
            while (res >= compt) {
                System.out.println("Le numero ne correspond pas a une piece.");
                System.out.print("Quel piece voulez vous jouez? ");
                res = s.nextInt();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        // renvoyer la piece correspondante
        Piece retour = posables.get(res);
        posables.remove(res);
        return retour;
    }

    public static Coup choixCoup(Partie ktrois, Scanner s) {
        Acteur j1 = ktrois.joueur1();
        int res = 0;
        ArrayList<Coup> jouables = ktrois.coupsJouables(j1);
        int compt = 0;
        for (Coup coup : jouables) {
            System.out.println("Coup " + coup.toString() + " jouable. Numero [" + compt + "]");
            compt++;
        }

        try {
            System.out.print("Quel coup voulez vous jouez? ");
            res = s.nextInt();
            while (res >= compt) {
                System.out.println("Le numero ne correspond pas a un coup.");
                System.out.print("Quel coup voulez vous jouez? ");
                res = s.nextInt();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return jouables.get(res);

    }
}