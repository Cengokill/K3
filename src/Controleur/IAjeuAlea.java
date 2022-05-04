package Controleur;

import Modeles.*;

public class IAjeuAlea implements IAjeu {
    // recupere la pyramide du joueur
    // recupere la pyramide du adverse
    // recupere la pyramide du jeu
    Jeu kTrois;

    IAjeuAlea(Jeu kTrois) {
        this.kTrois = kTrois;
    }

    public Sequence<Piece> GetPiecesJouables() {

    }
}