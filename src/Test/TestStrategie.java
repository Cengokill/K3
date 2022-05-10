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
            Partie ktrois = new Partie(j1, j2);

            for (int i = 0; i < 21; i++) { // pioche initialiser
                Piece p;
                p = ktrois.joueur1().piocherPiece(ktrois.getBasePieces());
                ktrois.joueur1().addPiecesPiochees(p);
                p = ktrois.joueur2().piocherPiece(ktrois.getBasePieces());
                ktrois.joueur2().addPiecesPiochees(p);
            }
            System.out.println("Les pioches des joueurs sont faites");

            // création de pyramide
            IApiocheAlea iaP = new IApiocheAlea();
            PyramideJoueur pj1 = iaP.CreerPioche(ktrois.joueur1().getPiecesPiochees());
            PyramideJoueur pj2 = iaP.CreerPioche(ktrois.joueur2().getPiecesPiochees());
            System.out.println("Les pyramides des joueurs sont initialisées");

            System.out.println("Pyramide joueur 1:");
            System.out.println(pj1.toString());
            System.out.println("Pyramide joueur 2:");
            System.out.println(pj2.toString());
            // Jeu aléatoire
            int jcourant = 1;
            IAjeuAlea ia = new IAjeuAlea();

            while (ktrois.CoupsJouables((Joueur) ktrois.joueur1()).size() != 0
                    && ktrois.CoupsJouables((Joueur) ktrois.joueur2()).size() != 0) {
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
            if (ktrois.CoupsJouables((Joueur) ktrois.joueur1()).size() == 0) {
                victoirej2++;
            } else if (ktrois.CoupsJouables((Joueur) ktrois.joueur1()).size() == 0) {
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
        PiecePyramide ppj = new PiecePyramide(c.getPiece(), c.getposJ());
        switch (jcourant) {
            case 1:
                ktrois.joueur1().getCamp().empiler(ppj);
            case 2:
                ktrois.joueur2().getCamp().empiler(ppj);
        }
    }

}