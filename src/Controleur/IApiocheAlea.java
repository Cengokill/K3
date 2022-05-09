package Controleur;

import java.util.*;

import Modeles.*;

public class IApiocheAlea implements IApioche {

    // void CreerPioche(LinkedList<Piece> piecesIA, Joueur jc) {
    // Pyramide pj = new PyramideJoueur(6, 6);
    // Random r = new Random();
    // int rand = r.nextInt(piecesIA.size());

    // Iterator<Piece> it = piecesIA.iterator();

    // while (!piecesIA.isEmpty()) {

    // }
    // }

    Pyramide CreerPioche(LinkedList<Piece> piecesIA) {
        if (piecesIA.size() != (6 * 7 / 2)) {
            System.out.println("Il n'y a pas le bon nombre de piece dans le sac pour constuire sa pyramide");
        }
        PyramideJoueur pj = new PyramideJoueur(6, 6);
        Random r = new Random();
        Iterator<Piece> it;
        PiecePyramide pp;
        Position pos;
        LinkedList<Position> lPos = new LinkedList<>();
        for (int i = 0; i < 6; i++) { // A tester peut avoir un bug sur les positions
            for (int j = 0; j < (6 - i); j++) {
                pos = new Position(i, j);
                lPos.add(pos);
            }
        }
        int alea, compt;
        Iterator<Position> it2 = lPos.iterator();
        while (it2.hasNext()) { // Tant qu'il y a des positions a remplir
            alea = r.nextInt(piecesIA.size());
            compt = 0;
            pos = it2.next();
            it = piecesIA.iterator();
            while (it.hasNext()) { // On cherche la piece qui correspond a l'aléatoire
                pp = new PiecePyramide(it.next(), pos);
                if (compt == alea) {
                    pj.empiler(pp); // on l'ajoute a la pyra et on la retire du sac
                    it.remove();
                }
                compt++;
            }
        }
        return pj;
    }
}