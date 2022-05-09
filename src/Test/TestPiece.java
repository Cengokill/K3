package Test;

import java.util.Iterator;
import java.util.LinkedList;

import Modeles.*;

public class TestPiece {
    public static void main(String[] args) {
        String res = "Pieces dans la liste: [";
        LinkedList<Piece> l = new LinkedList<>();

        l.add(new Piece(Couleurs.ROUGE));
        l.add(new Piece(Couleurs.BLEU));
        l.add(new Piece(Couleurs.VERT));
        l.add(new Piece(Couleurs.JAUNE));
        l.add(new Piece(Couleurs.BLANC));
        l.add(new Piece(Couleurs.NATUREL));

        Iterator<Piece> it = l.iterator();

        while (it.hasNext()) {
            Piece p = it.next();
            res += p.toString();
            res += ", ";
        }
        res += "]";
        System.out.println(res);
    }
}
