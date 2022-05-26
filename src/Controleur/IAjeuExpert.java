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
        if (this.mm == null) {
            Random r = new Random();
            int index = r.nextInt(arr.size());
            return arr.get(index);
        }
        return mm.PieceAVoler(arr, p);
    }
}