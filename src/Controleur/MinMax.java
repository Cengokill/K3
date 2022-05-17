package Controleur;

import java.util.ArrayList;
import java.util.Iterator;

import Modeles.*;

public class MinMax {
    int numerojoueur;
    Coup parfait;
    PiecePyramide avoler;

    MinMax(int numerojoueur) { // on créer une IA associé à un joueur
        this.numerojoueur = numerojoueur;
    }

    public int eval(Partie p) { // Fonction d'évalutaion de la configuration difference entre notre
                                // nombre de coups jouables et celui de l'adversaire
        int nbcoupsia, nbcoupsadv;
        if (numerojoueur == 0) {
            nbcoupsia = p.coupsJouables(p.joueur1()).size();
            nbcoupsadv = p.coupsJouables(p.joueur2()).size();
        } else {
            nbcoupsia = p.coupsJouables(p.joueur2()).size();
            nbcoupsadv = p.coupsJouables(p.joueur1()).size();
        }
        if (nbcoupsia == 0) {
            return -10000;
        }
        if (nbcoupsadv == 0) {
            return 10000;
        }
        return (nbcoupsia - nbcoupsadv);
    }

    public int meilleurConfigJ(Partie p, int horizon, boolean flag) {
        int joueurcourant = numerojoueur;
        if ((horizon == 0) || (p.estPartieFinie())) {
            return eval(p);
        }
        Acteur JoueurCourant;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
        } else {
            JoueurCourant = p.joueur2();
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        if (lc.size() == 1) { // Si un seul coup possible on le renvoie
            Coup c = lc.get(0);
            p.IAjoueCoup(c, joueurcourant);
            int uncoup = eval(p);
            p.IAannulCoup(c, joueurcourant);
            if (flag) {
                parfait = c;
            }
            return uncoup;
        }
        int valeurconfig = -10001;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext() && valeurconfig != 10000) {
            Coup c = it.next();
            p.IAjoueCoup(c, joueurcourant); // joue le coup
            // Si une piece peut etre voler
            // Alors pour toutes les pieces voler
            // vole la piece
            // evalue la config
            // maj de la valeur de config actu
            // annul vol
            // Sinon
            // calcule la valeur de la config
            // si new valeur> old valeur && premier tour on enregistre le coup
            // maj valeur
            // annule le coup

            int valeurfils = meilleurConfigAD(p, horizon - 1, flag);
            if (flag && valeurfils > valeurconfig) {
                parfait = c;
            }
            valeurconfig = Math.max(valeurconfig, valeurfils);
            p.IAannulCoup(c, joueurcourant);
        }
        return valeurconfig;

    }

    public int meilleurConfigAD(Partie p, int horizon, boolean flag) {
        int joueurcourant = numerojoueur;
        if (numerojoueur == 0) {
            joueurcourant = 1;
        } else {
            joueurcourant = 0;
        }
        if ((horizon == 0) || (p.estPartieFinie())) {
            return eval(p);
        }
        Acteur JoueurCourant;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
        } else {
            JoueurCourant = p.joueur2();
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        int valeurconfig = 10001;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext() && valeurconfig != -10000) {
            Coup c = it.next();
            p.IAjoueCoup(c, joueurcourant);
            // Si une piece peut etre voler
            // Alors pour toutes les pieces voler
            // vole la piece
            // evalue la config
            // maj de la valeur de config actu
            // annul vol
            // Sinon
            // calcule la valeur de la config
            // si new valeur< old valeur
            // enregistre coup
            // si deuxieme tour
            // enregistre avoler verifier si min ou max
            // maj valeur
            // annule le coup

            valeurconfig = Math.min(valeurconfig, meilleurConfigJ(p, horizon - 1, false));
            p.IAannulCoup(c, joueurcourant);
        }
        return valeurconfig;
    }

    public Coup getparfait() {
        return parfait;
    }

    public void affiche(ArrayList<Coup> l) {
        Iterator<Coup> it = l.iterator();
        System.out.println("Coups possibles");
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
        System.out.println();
    }

    public PiecePyramide PieceAVoler() {
        return avoler;
    }
}