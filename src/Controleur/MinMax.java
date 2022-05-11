package Controleur;

import java.util.ArrayList;
import java.util.Iterator;

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

    public int meilleurConfigJ(Partie p, int horizon) {
        int joueurcourant = numerojoueur;
        if ((!p.estPartieFinie(0)) | (!p.estPartieFinie(1)) | (horizon != 0)) {
            return eval(p);
        }
        Acteur JoueurCourant;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
        } else {
            JoueurCourant = p.joueur2();
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        int valeurconfig = -10000;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext()) {
            Coup c = it.next();
            Partie np = jouercoup(p, c, joueurcourant);
            valeurconfig = Math.max(valeurconfig, meilleurConfigAD(np, horizon--));
        }
        return valeurconfig;
    }

    public int meilleurConfigAD(Partie p, int horizon) {
        int joueurcourant = numerojoueur;
        if (numerojoueur == 0) {
            joueurcourant = 1;
        } else {
            joueurcourant = 0;
        }
        if ((!p.estPartieFinie(0)) | (!p.estPartieFinie(1)) | (horizon != 0)) {
            return eval(p);
        }
        Acteur JoueurCourant;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
        } else {
            JoueurCourant = p.joueur2();
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        int valeurconfig = 10000;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext()) {
            Coup c = it.next();
            Partie np = jouercoup(p, c, joueurcourant);
            valeurconfig = Math.min(valeurconfig, meilleurConfigJ(np, horizon--));
        }
        return valeurconfig;
    }

    public Partie jouercoup(Partie p, Coup c, int joueurcourant) { // joue le coup sur une partie Cloner
        Partie np = new Partie(new Joueur("blc"), new Joueur("menfou"));
        try {
            np = (Partie) p.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } // A voir si c'est une copie ou non
        Acteur jCourant;
        if (joueurcourant == 0) {
            jCourant = np.joueur1();
        } else {
            jCourant = np.joueur2();
        }
        jCourant.getCamp().retirer(c.getPosJ());
        np.getBaseMontagne().empiler(new PiecePyramide(c.getPiece(), c.getPosBase()));
        return np;
    }

}