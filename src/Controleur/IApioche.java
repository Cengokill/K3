package Controleur;

import java.util.ArrayList;

import Modeles.Piece;
import Modeles.PyramideJoueur;

public interface IApioche {
    // on ne regarde pas la pyramide adverse tant que on n'a pas fini la notre
    // Mais on connait son nombre de piece et leurs couleurs
    // On connait aussi la base et son ordre
    // Stratégie optimale?

    // But: avoir une pioche la plus efficace possible(nous permet un grand choix de
    // coups et bloquer un maximum l'adversaire)
    // IA facile : creation de la pioche en full aléatoire
    // IA moyen : sans regarder ce que l'adversaire a juste avec tes pieces et la
    // base
    // IA expert : trop intelligent pour perdre (min-max)

    public PyramideJoueur CreerPioche(ArrayList<Piece> piecesIA);
}