package Controleur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import Modeles.*;

public class MinMax {
    int numerojoueur;
    Coup parfait;
    PiecePyramide avoler;
    double tf, td;
    HashMap<String, PiecePyramide> volspossibles;
    int heur1;
    int heur2;
    boolean AFFICHE=false;

    MinMax(int numerojoueur) { // on créer une IA associé à un joueur
        this.numerojoueur = numerojoueur;
    }

    public Heuristique eval(Partie p) { // Fonction d'évalutaion de la configuration difference entre notre
        // nombre de coups jouables et celui de l'adversaire
        int nbcoupsia, nbcoupsadv, nbpiecesia;
        Heuristique heur = new Heuristique();
        if (numerojoueur == 0) {
            nbcoupsia = p.coupsJouables(p.joueur1()).size();
            nbcoupsadv = p.coupsJouables(p.joueur2()).size();
            nbpiecesia = p.joueur1().nbpieces();
        } else {
            nbcoupsia = p.coupsJouables(p.joueur2()).size();
            nbcoupsadv = p.coupsJouables(p.joueur1()).size();
            nbpiecesia = p.joueur2().nbpieces();
        }
        if (nbcoupsia == 0) {
            heur.setprem(-10000);
            heur.setdeux(-10000);
            heur.settrois(-10000);
        }
        if (nbcoupsadv == 0) {
            heur.setprem(10000);
            heur.setdeux(10000);
            heur.settrois(10000);
        } else {
            heur.setprem(nbcoupsia - nbcoupsadv);
            heur.setdeux(nbpiecesia);
            heur.settrois(nbcoupsadv);
        }

        return heur;
    }

    public Heuristique meilleurConfigJ(Partie p, int horizon, boolean flag, int valeurcourante) {

        if (flag) {
            volspossibles = new HashMap<>();
            // System.out.println("on cherche le coup pour l'ia");
            // td = (double) System.currentTimeMillis();
            heur1 = 0;
            heur2 = 0;
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
            Heuristique uncoup = eval(p);
            p.IAannulCoup(c, joueurcourant);
            if (flag) {
                parfait = c;
            }
            return uncoup;
        }

        // RECHERCHE DE LA VALEURE DE LA MEILLEURE CONFIG-------------
        Heuristique valeurconfig = new Heuristique();
        valeurconfig.setprem(-10001);
        valeurconfig.setdeux(-10001);
        valeurconfig.settrois(-10001);
        Iterator<Coup> it = lc.iterator();
        while (it.hasNext() && valeurconfig.getprem() != 10000) { // On itere sur les coups possibles
            Coup c = it.next();

            // On joue le coup
            if (p.IAjoueCoup(c, joueurcourant)) { // Si on peut se faire voler une piece
                ArrayList<PiecePyramide> piecesVolables = JoueurCourant.getPiecesJouables();
                Iterator<PiecePyramide> volables = piecesVolables.iterator();
                // On simule tout les vols possibles
                Heuristique confvol = new Heuristique();
                confvol.setprem(10001);
                confvol.setdeux(10001);
                confvol.settrois(10001);
                while (volables.hasNext()) {
                    PiecePyramide next = volables.next();
                    p.IAvol(next, joueurcourant); // vole la piece
                    Heuristique stock = meilleurConfigAD(p, horizon - 1, flag, valeurconfig.getprem()); // evalue la
                                                                                                        // config
                    if (compareHeuristique(stock, confvol)) { // maj de la valeur de config actu
                        confvol = stock;
                    }
                    p.IAannulvol(next, joueurcourant); // annul vol
                }
                // Pire vol qui peut nous arriver
                if (compareHeuristique(valeurconfig, confvol)) { // Si le pire vol est meilleur que toutes les configs
                                                                 // tester
                    valeurconfig = confvol;
                    if (flag) {
                        parfait = c;
                    }
                }

            } else {
                Heuristique valeurfils = meilleurConfigAD(p, horizon - 1, flag, valeurconfig.getprem());// calcule la
                                                                                                        // valeur de
                // la config

                if (flag && compareHeuristique(valeurconfig, valeurfils)) { // si new valeur> old valeur && premier tour
                                                                            // on enregistre le
                    // coup
                    parfait = c;
                }
                if (compareHeuristique(valeurconfig, valeurfils)) {
                    valeurconfig = valeurfils;
                } // maj valeur
            }

            p.IAannulCoup(c, joueurcourant); // annule le coup

            if (valeurconfig.getprem() > valeurcourante) {
                return valeurconfig;
            }
        }
        if(AFFICHE) {
	        if (flag) {
	            System.out.println("utilisation de l affination de l heuristique 1: " + heur1 + " fois");
	            System.out.println("utilisation de l affination de l heuristique 2: " + heur2 + " fois");
	        }
        }
        return valeurconfig;

    }

    public Heuristique meilleurConfigAD(Partie p, int horizon, boolean flag, int valeurcourante) {
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

        Heuristique valeurconfig = new Heuristique();
        valeurconfig.setprem(10001);
        valeurconfig.setdeux(10001);
        valeurconfig.settrois(10001);

        Iterator<Coup> it = lc.iterator();
        while (it.hasNext() && valeurconfig.getprem() != -10000) {
            Coup c = it.next();
            if (p.IAjoueCoup(c, joueurcourant)) {
                ArrayList<PiecePyramide> piecesVolables = JoueurCourant.getPiecesJouables();
                Iterator<PiecePyramide> volables = piecesVolables.iterator();
                Heuristique confvol = new Heuristique();
                confvol.setprem(-10001);
                confvol.setdeux(-10001);
                confvol.settrois(-10001);
                while (volables.hasNext()) {
                    PiecePyramide next = volables.next();
                    p.IAvol(next, joueurcourant); // vole la piece
                    Heuristique stock = meilleurConfigJ(p, horizon - 1, false, valeurconfig.getprem()); // evalue la
                                                                                                        // config
                    if (compareHeuristique(confvol, stock)) { // maj de la valeur de config actu
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
                if (compareHeuristique(confvol, valeurconfig)) {
                    valeurconfig = confvol;
                }
            } else {
                Heuristique valeurfils = meilleurConfigJ(p, horizon - 1, false, valeurconfig.getprem());
                if (compareHeuristique(valeurfils, valeurconfig)) {
                    valeurconfig = valeurfils;
                }
            }
            p.IAannulCoup(c, joueurcourant);
            if (valeurconfig.getprem() < valeurcourante) {
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

    public PiecePyramide PieceAVoler(ArrayList<PiecePyramide> arr, Partie p) {
        if (volspossibles.containsKey(p.optiIA())) {
            //System.out.println("CA MARCHE");
            return volspossibles.get(p.optiIA());
        } else {
            Random r = new Random();
            //System.out.println("On vole en aleatoire");
            int alea = r.nextInt(arr.size());
            return arr.get(alea);
        }
    }

    public boolean compareHeuristique(Heuristique un, Heuristique deux) { // renvoie vraie si un est moins fort que deux
        if (un.getprem() == deux.getprem()) {
            heur1++;
            if (un.getdeux() == deux.getdeux()) {
                heur2++;
                return (un.gettrois() > deux.gettrois());
            }
            return (un.getdeux() < deux.getdeux());
        }
        return (un.getprem() < deux.getprem());
    }
}