package Controleur;

import java.util.ArrayList;
import java.util.Iterator;

import Modeles.*;

public class IAjeuExpert implements IAjeu {
    int horizon;
    PiecePyramide avoler;

    public IAjeuExpert(int horizon) {
        this.horizon = horizon;
    }

    public Coup IACoup(Partie p, int numeroJoueur) {
        MinMax mm = new MinMax(numeroJoueur);
        mm.meilleurConfigJ(p, horizon, true);
        avoler = mm.PieceAVoler();
        return mm.getparfait();
    }

    public PiecePyramide PieceAVoler(ArrayList<PiecePyramide> arr) {
        Iterator<PiecePyramide> it = arr.iterator();
        while (it.hasNext()) {
            PiecePyramide temp = it.next();
            if (temp.getPiece() == avoler.getPiece() && temp.getPos() == avoler.getPos()) {
                System.out.println("La piece a voler existe bien");
            }
        }
        return avoler;
    }

    // voler piece: mode agressif: Voler les pieces importantes de l'adversaire
    // mode défensif: Voler les pieces permettant un maximum de coups au joueur
    // courant
}