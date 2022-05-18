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
        if (flag) {
            System.out.println("on cherche le coup pour l'ia");
        }
        int joueurcourant = numerojoueur;
        if ((horizon == 0) || (p.estPartieFinie())) {
            return eval(p);
        }
        Acteur JoueurCourant;
        Acteur JoueurPrecedent;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
            JoueurPrecedent = p.joueur2();
        } else {
            JoueurCourant = p.joueur2();
            JoueurPrecedent = p.joueur1();
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
            // Si une piece peut etre voler
            if (p.IAjoueCoup(c, joueurcourant)) {
                // Alors pour toutes les pieces voler
                ArrayList<PiecePyramide> piecesVolables = JoueurCourant.getPiecesJouables();
                // System.out.println("Le joueur " + JoueurPrecedent.getNom() + " peut voler
                // :");
                // for (PiecePyramide ttt : piecesVolables) {
                // System.out.println(ttt.toString());
                // }
                Iterator<PiecePyramide> volables = piecesVolables.iterator();
                while (volables.hasNext()) {
                    PiecePyramide next = volables.next();
                    p.IAvol(next, joueurcourant); // vole la piece
                    int stock = meilleurConfigAD(p, horizon - 1, flag); // evalue la config
                    if (stock > valeurconfig) { // maj de la valeur de config actu
                        valeurconfig = stock;
                        if (flag) {
                            parfait = c;
                        }
                    }
                    p.IAannulvol(next, joueurcourant); // annul vol
                }

            } else {
                int valeurfils = meilleurConfigAD(p, horizon - 1, flag);// calcule la valeur de la config
                if (flag && valeurfils > valeurconfig) { // si new valeur> old valeur && premier tour on enregistre le
                                                         // coup
                    parfait = c;
                }
                valeurconfig = Math.max(valeurconfig, valeurfils); // maj valeur
            }
            p.IAannulCoup(c, joueurcourant); // annule le coup
        }
        if (flag) {
            System.out.println("on a trouve le coup pour l'ia");
        }
        return valeurconfig;

    }

    public int meilleurConfigAD(Partie p, int horizon, boolean flag) {
        int joueurcourant = 100;
        if (numerojoueur == 0) {
            joueurcourant = 1;
        } else {
            joueurcourant = 0;
        }
        if ((horizon == 0) || (p.estPartieFinie())) {
            return eval(p);
        }
        Acteur JoueurCourant;
        Acteur JoueurPrecedent;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
            JoueurPrecedent = p.joueur2();
        } else {
            JoueurCourant = p.joueur2();
            JoueurPrecedent = p.joueur1();
        }
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        int valeurconfig = 10001;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext() && valeurconfig != -10000) {
            Coup c = it.next();
            // Si une piece peut etre voler
            if (p.IAjoueCoup(c, joueurcourant)) { // PEUT AVOIR PBS EFFICACITES
                // Alors pour toutes les pieces voler
                ArrayList<PiecePyramide> piecesVolables = JoueurCourant.getPiecesJouables();
                // System.out.println("Le joueur " + JoueurPrecedent.getNom() + " peut voler
                // :");
                // for (PiecePyramide ttt : piecesVolables) {
                // System.out.println(ttt.toString());
                // }
                Iterator<PiecePyramide> volables = piecesVolables.iterator();
                while (volables.hasNext()) {
                    PiecePyramide next = volables.next();
                    p.IAvol(next, joueurcourant); // vole la piece
                    int stock = meilleurConfigJ(p, horizon - 1, false); // evalue la config
                    if (stock < valeurconfig) { // maj de la valeur de config actu
                        valeurconfig = stock;
                        if (flag) {
                            avoler = next;
                        }
                    }
                    p.IAannulvol(next, joueurcourant); // annul vol
                }

            } else {
                valeurconfig = Math.min(valeurconfig, meilleurConfigJ(p, horizon - 1, false)); // maj valeur
            }
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