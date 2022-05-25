package Controleur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Modeles.*;

public class IAjeuAlea implements IAjeu {
    Joueur ia;

    public Coup IACoup(Partie p, int numeroJoueur) {
        // mettre a jour les donnees du joueur IA
        Acteur ia;
        if (numeroJoueur == 0) {
            ia = p.joueur1();
        } else {
            ia = p.joueur2();
        }
        Coup c;
        ArrayList<Coup> l = p.coupsJouables(ia);
        // affiche(l);

        Random r = new Random();
        int alea = r.nextInt(l.size());
        int compt = 0;

        Iterator<Coup> it = l.iterator();
        while (it.hasNext()) {
            c = it.next();
            if (compt == alea) {
                return c;
            } else {
                compt++;
            }
        }
        return null;
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
        Random r = new Random();
        int alea = r.nextInt(arr.size());
        return arr.get(alea);
    }
}