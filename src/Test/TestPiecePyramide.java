package Test;

import java.util.Iterator;
import java.util.LinkedList;

import Modeles.*;

public class TestPiecePyramide {
    public static void main(String[] args) {
        String res = "Pieces dans la liste: \n";
        LinkedList<Piece> l = new LinkedList<>();

        l.add(new Piece(Couleurs.ROUGE));
        l.add(new Piece(Couleurs.BLEU));
        l.add(new Piece(Couleurs.VERT));
        l.add(new Piece(Couleurs.JAUNE));
        l.add(new Piece(Couleurs.BLANC));
        l.add(new Piece(Couleurs.NATUREL));

        Iterator<Piece> it = l.iterator();

        int compt = 0;
        while (it.hasNext()) {
            Piece p = it.next();
            Position pos = new Position(compt, compt + 1);
            PiecePyramide pp = new PiecePyramide(p, pos);
            res += pp.toString();
            res += "\n";
            compt++;
        }
        System.out.println(res);

    }
}
