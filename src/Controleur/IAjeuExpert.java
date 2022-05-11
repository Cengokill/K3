package Controleur;

import Modeles.*;

public class IAjeuExpert implements IAjeu {

    public Coup IACoup(Partie p, int numeroJoueur) {
        MinMax mm = new MinMax(numeroJoueur);
        mm.meilleurConfigJ(p, 3, true);
        return mm.getparfait();
    }
}