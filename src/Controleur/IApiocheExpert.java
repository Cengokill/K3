package Controleur;

import java.util.ArrayList;
import java.util.Iterator;

import Modeles.*;

public class IApiocheExpert implements IApioche {

    public PyramideJoueur CreerPioche(Partie p, int numerojoueur) {
        ArrayList<Piece> piecesIA;
        if (numerojoueur == 0) {
            piecesIA = p.joueur1().getPiecesPiochees();
        } else {
            piecesIA = p.joueur2().getPiecesPiochees();
        }
        Pyramide pm = p.getBaseMontagne();
        PyramideJoueur pj = new PyramideJoueur(6, 6);
        ArrayList<PiecePyramide> aempiler = new ArrayList<>();

        // On place nos joker a des endroits deja définis
        aempiler.add(new PiecePyramide(new Piece(Couleurs.NATUREL), new Position(1, 0)));
        aempiler.add(new PiecePyramide(new Piece(Couleurs.BLANC), new Position(1, 4)));
        aempiler.add(new PiecePyramide(new Piece(Couleurs.NATUREL), new Position(3, 2)));
        aempiler.add(new PiecePyramide(new Piece(Couleurs.BLANC), new Position(3, 0)));

        Iterator<Piece> it = piecesIA.iterator();
        while (it.hasNext()) {
            if (it.next().getColor() == Couleurs.NATUREL || it.next().getColor() == Couleurs.BLANC) {
                it.remove();
            }
        }

        return pj;
    }

}