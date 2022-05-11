package Controleur;

import java.util.ArrayList;
import java.util.Iterator;

import Modeles.*;

public class MinMax {
    int numerojoueur;
    Coup parfait;
    Partie copiepartie;

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

    public int meilleurConfigJ(Partie p, int horizon, boolean flag) {
        System.out.println("On est a l'horizon :" + horizon);
        int joueurcourant = numerojoueur;
        if ((horizon == 0) || (p.estPartieFinie(1)) || (p.estPartieFinie(0))) {
            System.out.println("On est aller a l horizon :" + horizon);
            System.out.println("Je suis une feuille");
            return eval(p);
        }
        Acteur JoueurCourant;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
        } else {
            JoueurCourant = p.joueur2();
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        affiche(lc);

        int valeurconfig = -10001;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext()) {
            Coup c = it.next();
            System.out.println("On teste le coup :" + c.toString());
            jouercoup(p, c, joueurcourant);
            System.out.println(copiepartie.joueur2().getCamp().toString());
            System.out.println(copiepartie.getBaseMontagne().toString());
            int valeurfils = meilleurConfigAD(copiepartie, horizon - 1);
            if (flag && valeurfils > valeurconfig) {
                parfait = c;
            }
            valeurconfig = Math.max(valeurconfig, valeurfils);
        }
        return valeurconfig;

    }

    public int meilleurConfigAD(Partie p, int horizon) {
        System.out.println("On est a l'horizon :" + horizon);
        int joueurcourant = numerojoueur;
        if (numerojoueur == 0) {
            joueurcourant = 1;
        } else {
            joueurcourant = 0;
        }
        if ((horizon == 0) || (p.estPartieFinie(1)) || (p.estPartieFinie(0))) {
            System.out.println("On est aller a l horizon :" + horizon);
            System.out.println("Je suis une feuille");
            return eval(p);
        }
        Acteur JoueurCourant;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
        } else {
            JoueurCourant = p.joueur2();
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        affiche(lc);
        int valeurconfig = 10000;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext()) {
            Coup c = it.next();
            System.out.println("On teste le coup :" + c.toString());
            jouercoup(p, c, joueurcourant);
            System.out.println(copiepartie.getBaseMontagne().toString());
            valeurconfig = Math.min(valeurconfig, meilleurConfigJ(copiepartie, horizon - 1, false));
        }
        return valeurconfig;
    }

    public void jouercoup(Partie p, Coup c, int joueurcourant) { // joue le coup sur une partie Cloner
        copiepartie = (Partie) p.clone();
        // System.out.println();
        // System.out.println("On teste le coup : " + c.toString());
        // System.out.println(copiepartie.joueur2().getCamp().toString());
        // System.out.println(copiepartie.getBaseMontagne().toString());
        copiepartie.jouer(c, joueurcourant);
        // System.out.println(copiepartie.joueur2().getCamp().toString());
        // System.out.println(copiepartie.getBaseMontagne().toString());
        System.out.println();
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

}