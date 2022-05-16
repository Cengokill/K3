package Controleur;

import java.util.*;

import Modeles.*;

public class IApiocheAlea implements IApioche {

    public ArrayList<PiecePyramide> CreerPioche(Partie p, int numerojoueur) {
        ArrayList<Piece> piecesIA;
        if (numerojoueur == 0) {
            piecesIA = p.joueur1().getPiecesPiochees();
        } else {
            piecesIA = p.joueur2().getPiecesPiochees();
        }

        if (piecesIA.size() != (6 * 7 / 2)) {
            System.out.println("Il n'y a pas le bon nombre de piece dans le sac pour constuire sa pyramide");
        }
        ArrayList<PiecePyramide> pj = new ArrayList<>();
        Random r = new Random();
        Iterator<Piece> it;
        PiecePyramide pp;
        Position pos;
        LinkedList<Position> lPos = new LinkedList<>();
        for (int etage = 0; etage < 6; etage++) { // A tester peut avoir un bug sur les positions
            for (int largeur = 0; largeur < (6 - etage); largeur++) {
                pos = new Position(etage, largeur);
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
                    pj.add(pp); // on l'ajoute a la pyra et on la retire du sac
                    it.remove();
                }
                compt++;
            }
        }
        return pj;
    }
}