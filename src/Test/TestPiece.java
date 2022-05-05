package Test;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

import Modeles.Piece;

public class TestPiece {
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

        while (it.hasNext()) {
            Piece p = it.next();
            res += p.toString();
            res += ", ";
        }
        res += "]";
        System.out.println(res);
    }
}
