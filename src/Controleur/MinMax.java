package Controleur;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import Modeles.*;

public class MinMax {
    int numerojoueur;
    Coup parfait;
    PiecePyramide avoler;
    double tf, td;
    Hashtable<String, PiecePyramide> volspossibles;

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

    public int meilleurConfigJ(Partie p, int horizon, boolean flag, int valeurcourante) {

        if (flag) {
            volspossibles = new Hashtable<>();
            // System.out.println("on cherche le coup pour l'ia");
            // td = (double) System.currentTimeMillis();
        }

        // EVALUTION---------------------------------------------------
        int joueurcourant = numerojoueur;
        if ((horizon == 0) || (p.estPartieFinie())) {
            return eval(p);
        }

        // RECUPERE JCOURANT-------------------------------------------
        Acteur JoueurCourant;
        if (joueurcourant == 0) {
            JoueurCourant = p.joueur1();
        } else {
            JoueurCourant = p.joueur2();
        }

        // UN SEUL COUP POSSIBLE--------------------------------------
        ArrayList<Coup> lc = p.coupsJouables(JoueurCourant);
        if (lc.size() == 1) {
            Coup c = lc.get(0);
            p.IAjoueCoup(c, joueurcourant);
            int uncoup = eval(p);
            p.IAannulCoup(c, joueurcourant);
            if (flag) {
                parfait = c;
            }
            return uncoup;
        }

        // RECHERCHE DE LA VALEURE DE LA MEILLEURE CONFIG-------------
        int valeurconfig = -10001;
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext() && valeurconfig != 10000) { // On itere sur les coups possibles
            Coup c = it.next();

            // On joue le coup
            if (p.IAjoueCoup(c, joueurcourant)) { // Si on peut se faire voler une piece
                ArrayList<PiecePyramide> piecesVolables = JoueurCourant.getPiecesJouables();
                Iterator<PiecePyramide> volables = piecesVolables.iterator();
                // On simule tout les vols possibles
                int confvol = 10001;
                while (volables.hasNext()) {
                    PiecePyramide next = volables.next();
                    p.IAvol(next, joueurcourant); // vole la piece
                    int stock = meilleurConfigAD(p, horizon - 1, flag, valeurconfig); // evalue la config
                    if (stock < confvol) { // maj de la valeur de config actu
                        confvol = stock;
                    }
                    p.IAannulvol(next, joueurcourant); // annul vol
                }
                // Pire vol qui peut nous arriver
                if (valeurconfig < confvol) { // Si le pire vol est meilleur que toutes les configs tester
                    valeurconfig = confvol;
                    if (flag) {
                        parfait = c;
                    }
                }

            } else {
                int valeurfils = meilleurConfigAD(p, horizon - 1, flag, valeurconfig);// calcule la valeur de la config
                if (flag && valeurfils > valeurconfig) { // si new valeur> old valeur && premier tour on enregistre le
                                                         // coup
                    parfait = c;
                }
                valeurconfig = Math.max(valeurconfig, valeurfils); // maj valeur
            }
            p.IAannulCoup(c, joueurcourant); // annule le coup

            if (valeurconfig > valeurcourante) {
                return valeurconfig;
            }
        }
        if (flag) {
            // System.out.println("on a trouve le coup pour l'ia");
            // tf = (double) System.currentTimeMillis();
            // System.out.println((tf - td) / 1000 + " s de recherche");
        }
        return valeurconfig;

    }

    public int meilleurConfigAD(Partie p, int horizon, boolean flag, int valeurcourante) {
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
            if (p.IAjoueCoup(c, joueurcourant)) {
                ArrayList<PiecePyramide> piecesVolables = JoueurCourant.getPiecesJouables();
                Iterator<PiecePyramide> volables = piecesVolables.iterator();
                int confvol = -10001;
                while (volables.hasNext()) {
                    PiecePyramide next = volables.next();
                    p.IAvol(next, joueurcourant); // vole la piece
                    int stock = meilleurConfigJ(p, horizon - 1, false, valeurconfig); // evalue la config
                    if (stock > confvol) { // maj de la valeur de config actu
                        confvol = stock;
                        if (flag) { // A CHANGER EN STRUCTURE
                            avoler = next;
                        }
                    }
                    p.IAannulvol(next, joueurcourant); // annul vol
                    if (flag) {
                        volspossibles.put(p.optiIA(), avoler);
                    }
                }
                if (valeurconfig > confvol) {
                    valeurconfig = confvol;
                }
            } else {
                valeurconfig = Math.min(valeurconfig, meilleurConfigJ(p, horizon - 1, false, valeurconfig)); // maj
                                                                                                             // valeur
            }
            p.IAannulCoup(c, joueurcourant);
            if (valeurconfig < valeurcourante) {
                return valeurconfig;
            }
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