package Test;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

import Modeles.Piece;
import Modeles.PiecePyramide;
import Modeles.Position;

public class TestPiecePyramide {
    public static void main(String[] args) {
        String res = "Pieces dans la liste: [";
        LinkedList<Piece> l = new LinkedList<>();

        l.add(new Piece(Color.red));
        l.add(new Piece(Color.blue));
        l.add(new Piece(Color.green));
        l.add(new Piece(Color.yellow));
        l.add(new Piece(Color.white));
        l.add(new Piece(Color.lightGray));

        Iterator<Piece> it = l.iterator();

        int compt = 0;
        while (it.hasNext()) {
            Piece p = it.next();
            Position pos = new Position(compt, compt + 1);
            PiecePyramide pp = new PiecePyramide(p, pos);
            res += pp.toString();
            res += ", ";
            compt++;
        }
        res += "]";
        System.out.println(res);

    }
}
