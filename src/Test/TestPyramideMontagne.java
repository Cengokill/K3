package Test;

import Modeles.*;

public class TestPyramideMontagne {
    public static void main(String[] args) {
        Pyramide py = new PyramideMontagne(6, 6);
        System.out.println("Largeur : " + py.getlargeur());
        System.out.println("Largeur : " + py.getHauteur());
    }
}
