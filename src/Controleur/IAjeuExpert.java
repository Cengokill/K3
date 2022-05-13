package Controleur;

import Modeles.*;

public class IAjeuExpert implements IAjeu {
    int horizon;

    public IAjeuExpert(int horizon) {
        this.horizon = horizon;
    }

    public Coup IACoup(Partie p, int numeroJoueur) {
        MinMax mm = new MinMax(numeroJoueur);
        mm.meilleurConfigJ(p, horizon, true);
        return mm.getparfait();
    }
}