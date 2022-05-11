package Controleur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Modeles.*;

public class IAjeuAlea implements IAjeu {
    Joueur ia;
    // recupere les coupsjouables du joueurIA (on part du principe piece accessible
    // + piece jouable sur plateau)
    // iterer sur les coups jouables avec un random puis
    // renvoie un coup random parmi ceux la

    // liste des coups possibles a faire
    // recuperer un coup aleatoire et le renvoyer ->
    // dans le cas ou les joueur / le jeu peut envoyer cette liste de coups
    // Sinon la calculer avec la pyramide actuelle et la pyramide du joueur en cours

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
}