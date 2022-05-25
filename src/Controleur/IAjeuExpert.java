package Controleur;

import java.util.ArrayList;
import java.util.Random;

import Modeles.*;

public class IAjeuExpert implements IAjeu {
    int horizon;
    PiecePyramide avoler;
    MinMax mm;

    public IAjeuExpert(int horizon) {
        this.horizon = horizon;
    }

    public Coup IACoup(Partie p, int numeroJoueur) {
        mm = new MinMax(numeroJoueur);
        mm.meilleurConfigJ(p, horizon, true, 10001);
        return mm.getparfait();
    }

    public PiecePyramide PieceAVoler(ArrayList<PiecePyramide> arr, Partie p) {
        return mm.PieceAVoler(arr, p);

        // if (avoler == null || !arr.contains(avoler)) {
        // Random r = new Random();
        // int index = r.nextInt(arr.size());
        // // System.out.println("L'IA vole la piece " + arr.get(index).toString());
        // return arr.get(index);
        // }
        // // System.out.println("L'IA vole la piece " + avoler.toString());
        // return avoler;
    }
}