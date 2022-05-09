package Test;

import java.util.*;

import Modeles.*;

public class TestPyramideMontagne {
    static PyramideMontagne baseMontagne;

    public static void main(String[] args) {
        initBaseMontagne();
        System.out.println("Base de la pyramide:");
        for (int i = 0; i < 9; i++) {
            Position coord = new Position(i, 0);
            Piece p = baseMontagne.getPiece(coord);
            System.out.println(p.toString() + " de position" + coord.toString());
        }
        // Affiche les pieces qui sont posables sur la pyramide
        LinkedList<PiecePyramide> posables = baseMontagne.piecesPosables();
        Iterator<PiecePyramide> it = posables.iterator();
        System.out.println("Pieces posables: ");
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

    }

    static void initBaseMontagne() {// creation de la base de la montagne constituee de 9 pieces
        baseMontagne = new PyramideMontagne(9, 9);// 9 etages, 9 pieces
        Random r = new Random();
        Piece p;
        PiecePyramide pp;
        for (int i = 0; i < 9; i++) {
            int alea = r.nextInt(6);
            switch (alea) {
                case 0:
                    p = new Piece(Couleurs.ROUGE);
                    pp = new PiecePyramide(p, new Position(i, 0));
                    System.out.println("On ajoute ROUGE");
                    break;
                case 1:
                    p = new Piece(Couleurs.BLEU);
                    pp = new PiecePyramide(p, new Position(i, 0));
                    System.out.println("On ajoute BLEU");
                    break;
                case 2:
                    p = new Piece(Couleurs.JAUNE);
                    pp = new PiecePyramide(p, new Position(i, 0));
                    System.out.println("On ajoute JAUNE");
                    break;
                case 3:
                    p = new Piece(Couleurs.VERT);
                    pp = new PiecePyramide(p, new Position(i, 0));
                    System.out.println("On ajoute VERT");
                    break;
                case 4:
                    p = new Piece(Couleurs.NOIR);
                    pp = new PiecePyramide(p, new Position(i, 0));
                    System.out.println("On ajoute NOIR");
                    break;
                case 5:
                    p = new Piece(Couleurs.NATUREL);
                    pp = new PiecePyramide(p, new Position(i, 0));
                    System.out.println("On ajoute NATUREL");
                    break;
                default:
                    pp = null;
                    break;
            }
            baseMontagne.empiler(pp);
        }
    }

}
