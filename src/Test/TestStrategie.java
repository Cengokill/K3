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
            Joueur j1 = new Joueur("BigBrain");
            Joueur j2 = new Joueur("Stupid");
            Partie ktrois = new Partie(j1, j2);

            for (int i = 0; i < 21; i++) { // pioche initialiser
                Piece p;
                p = ktrois.joueur1().piocherPiece(ktrois.getBasePieces());
                ktrois.joueur1().addPiecesPiochees(p);
                p = ktrois.joueur2().piocherPiece(ktrois.getBasePieces());
                ktrois.joueur2().addPiecesPiochees(p);
            }

            // création de pyramide
            IApiocheAlea iaP = new IApiocheAlea();
            PyramideJoueur pj1 = new PyramideJoueur(6, 6);
            PyramideJoueur pj2 = iaP.CreerPioche(ktrois.joueur2().getPiecesPiochees());
            // Jeu aléatoire
            // resultat gagnant
            compt++;
        }
    }

    void stats(PyramideMontagne baseMontagne) {

    }
}