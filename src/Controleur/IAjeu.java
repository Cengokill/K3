package Controleur;

import java.util.ArrayList;

import Modeles.*;

public interface IAjeu {
    // facile: aleatoire
    // moyen: min-max avec moins de profondeur
    // expert: min-max juqu'au bout

    // Heuristique de base: si notre nb de coups =0 -> +infini
    // sinon le nombre de coups de l 'adversaire

    public Coup IACoup(Partie p, int numeroJoueur);

    public PiecePyramide PieceAVoler(ArrayList<PiecePyramide> arr, Partie p);
}