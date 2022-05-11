package Controleur;

import java.util.ArrayList;

import Modeles.*;

public class MinMax {
    int numerojoueur;

    MinMax(int numerojoueur) { // on créer une IA associé à un joueur
        this.numerojoueur = numerojoueur;
    }

    public int eval(Partie p) { // Fonction d'évalutaion de la configuration difference entre notre
                                // nombre de coups jouables et celui de l'adversaire
        int nbcoupsj1 = p.coupsJouables(p.joueur1()).size();
        int nbcoupsj2 = p.coupsJouables(p.joueur1()).size();
        if (numerojoueur == 0) {
            if (nbcoupsj1 == 0) {
                return -10000;
            }
            if (nbcoupsj2 == 0) {
                return 10000;
            }
            return (nbcoupsj1 - nbcoupsj2);
        } else {
            if (nbcoupsj2 == 0) {
                return -10000;
            }
            if (nbcoupsj1 == 0) {
                return 10000;
            }
            return (nbcoupsj2 - nbcoupsj1);
        }

    }

    public int meilleurConfig(Partie p, int horizon) {
        int profondeur = 1;
        if ((!p.estPartieFinie(0)) | (!p.estPartieFinie(1)) | (profondeur == horizon)) {
            return eval(p);
        }
        Acteur JoueurCourant;
        if (numerojoueur == 0) {
            JoueurCourant = ;   
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);

        return 0;
    }

}