package Test;

import Modeles.*;
import Controleur.*;

public class TestStrategie {
    public static void main(String[] args) {
        int objectif = 1;
        int compt = 0;
        int victoirej1 = 0;
        int victoirej2 = 0;

        while (compt < objectif) {
            Acteur j1 = new Joueur("BigBrain");
            Acteur j2 = new Joueur("Stupid");
            Partie ktrois = new Partie(j1, j2, compt);// numero de partie
            for (int i = 0; i < 2; i++) {
                Piece pb = new Piece(Couleurs.BLANC);
                Piece pn = new Piece(Couleurs.NATUREL);
                ktrois.joueur1().addPiecePiochee(pb);
                ktrois.joueur1().addPiecePiochee(pn);
                ktrois.joueur2().addPiecePiochee(pb);
                ktrois.joueur2().addPiecePiochee(pn);
            }

            for (int i = 0; i < 17; i++) { // pioche initialiser
                Piece p;
                p = ktrois.joueur1().piocherPiece(ktrois.getBasePieces()); // Attention pour l'instant il y a les blancs
                                                                           // et naturels dans le sac
                ktrois.joueur1().addPiecePiochee(p);
                p = ktrois.joueur2().piocherPiece(ktrois.getBasePieces());
                ktrois.joueur2().addPiecePiochee(p);
            }
            System.out.println("Les pioches des joueurs sont faites");

            // création de pyramide
            IApiocheAlea iaP = new IApiocheAlea();
            PyramideJoueur pj1; // =iaP.CreerPioche(ktrois.joueur1().getPiecesPiochees());
            PyramideJoueur pj2; // =iaP.CreerPioche(ktrois.joueur2().getPiecesPiochees());
            System.out.println("Les pyramides des joueurs sont initialiseees");
            System.out.println();
            System.out.println("Pyramide joueur 1:");
            //System.out.println(pj1.toString());
            System.out.println("Pyramide joueur 2:");
            //System.out.println(pj2.toString());
            // Jeu aléatoire

            System.out.println("Le jeu commence");
            int jcourant = 1;
            IAjeuAlea ia = new IAjeuAlea();

            while (ktrois.coupsJouables((Joueur) ktrois.joueur1()).size() != 0
                    && ktrois.coupsJouables((Joueur) ktrois.joueur2()).size() != 0) {
                Coup c;
                c = ia.IACoup(ktrois, jcourant);
                jouer(ktrois, c, jcourant);
                if (jcourant == 1) {
                    jcourant = 2;
                } else {
                    jcourant = 1;
                }
            }
            // resultat gagnant
            if (ktrois.coupsJouables((Joueur) ktrois.joueur1()).size() == 0) {
                victoirej2++;
            } else if (ktrois.coupsJouables((Joueur) ktrois.joueur1()).size() == 0) {
                victoirej1++;
            }
            compt++;
        }

        System.out.println("Taux de victoire du joueur 1: " + (victoirej1 * 100 / (objectif)) + "%");
        System.out.println("Taux de victoire du joueur 2: " + (victoirej2 * 100 / (objectif)) + "%");
    }

    public static void jouer(Partie ktrois, Coup c, int jcourant) {
        PiecePyramide pp = new PiecePyramide(c.getPiece(), c.getPosBase());
        ktrois.getBaseMontagne().empiler(pp);
        PiecePyramide ppj = new PiecePyramide(c.getPiece(), c.getPosJ());
        switch (jcourant) {
            case 1:
                ktrois.joueur1().getCamp().empiler(ppj);
            case 2:
                ktrois.joueur2().getCamp().empiler(ppj);
        }
    }

}