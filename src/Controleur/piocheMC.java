package Controleur;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import Modeles.*;

public class piocheMC implements IApioche {
    IApioche commentpiocher = new IApiocheAlea();
    IApioche commentpiochermieux = new IApiocheExpert();
    IAjeu commentjouer = new IAjeuAlea();
    int meilleurvictoire;
    PyramideJoueur meilleurpyra;

    @Override
    public ArrayList<PiecePyramide> CreerPioche(Partie p, int numerojoueur) {
        double td = (double) System.currentTimeMillis();
        PyramideJoueur pyraadv = new PyramideJoueur(6, 6);

        boolean flag = true;
        int numeroadv;
        if (numerojoueur == 0) {
            numeroadv = 1;
            if (p.joueurDebut == 1) {
                // sauvegarde pyra adv
                pyraadv = clonepyra(p.joueur2().getCamp());
                // deconstruit
                AnnulCreerPyra(p, numeroadv);
                p.joueur2().valideCamp = false;
            }
        } else {
            numeroadv = 0;
            if (p.joueurDebut == 0) {
                // sauvegarde pyra adv
                pyraadv = clonepyra(p.joueur1().getCamp());
                // deconstruit
                AnnulCreerPyra(p, numeroadv);
                p.joueur1().valideCamp = false;
            }
        }

        // Pour i allant de 0 a 100
        for (int i = 0; i < 100; i++) {
            // Creer une picohe alea pour notre joueur
            CreerPyraAlea(p, numerojoueur);
            int nbvictoire = 0;
            // Pour j allant de 0 a 100
            for (int j = 0; j < 100; j++) {
                // Creer une pioche alea pour l'adversaire
                CreerPyraAlea(p, numeroadv);
                // Simule la partie
                // Si gagnant
                // nbvictoire ++
                if (simulePartie(p, numerojoueur)) {
                    nbvictoire++;
                }
                // Remet la pioche du joueur dans son etat de base
                AnnulCreerPyra(p, numeroadv);
            }
            if (flag) {
                meilleurvictoire = nbvictoire;
                if (numerojoueur == 0) {
                    meilleurpyra = clonepyra(p.joueur1().getCamp());
                } else {
                    meilleurpyra = clonepyra(p.joueur2().getCamp());
                }
                flag = false;
            } else {
                if (nbvictoire > meilleurvictoire) {
                    meilleurvictoire = nbvictoire;
                    if (numerojoueur == 0) {
                        meilleurpyra = clonepyra(p.joueur1().getCamp());
                    } else {
                        meilleurpyra = clonepyra(p.joueur2().getCamp());
                    }
                }
            }
            // remet la pioche de notre joueur dans son etat de base
            AnnulCreerPyra(p, numerojoueur);
        }

        ArrayList<PiecePyramide> aempiler = new ArrayList<>();
        // mettre les pieces de la pyramide dans la liste aempiler dans le bon ordre
        for (int etage = 0; etage < 6; etage++) {
            for (int rang = 0; rang < (6 - etage); rang++) {
                Position pos = new Position(etage, rang);
                aempiler.add(new PiecePyramide(meilleurpyra.getPiece(pos), pos));
            }
        }

        // Reconstruire pyra adverse
        if (numeroadv == p.joueurDebut) {
            if (numeroadv == 0) {
                p.joueur1().setCamp(pyraadv);
                p.joueur1().valideCamp = true;
            } else {
                p.joueur2().setCamp(pyraadv);
                p.joueur2().valideCamp = true;
            }
        }

        double tf = (double) System.currentTimeMillis();
        System.out.println((tf - td) / 1000 + " s de création de la pioche");
        System.out.println("Taux de victoire de cette pioche: " + meilleurvictoire);
        return aempiler;
    }

    public boolean simulePartie(Partie p, int numerojoueur) { // simule la partie et renvoie vrai si le joueur en
                                                              // argument gagne
        // Penser a annuler les coups a la fin de la partie pour recuperer la partie
        // dans le meme etat que dans celle avant de la simuler aleatoirement
        int gagnant = 2;
        ArrayList<Coup> AllPlayed = new ArrayList<>();
        ArrayList<PiecePyramide> AllStolen = new ArrayList<>();
        ArrayList<Integer> quoifaire = new ArrayList<>(); // O quand on ajoute un coup, 1 sinon

        while (!p.estPartieFinie()) {
            Coup c;
            Acteur jCourant;

            if (p.joueurCourant == 0) {
                jCourant = p.joueur1();
            } else {
                jCourant = p.joueur2();
            }

            c = commentjouer.IACoup(p, p.getJoueurCourant());
            AllPlayed.add(0, c);
            quoifaire.add(0, 0);

            if (p.IAjoueCoup(c, p.getJoueurCourant())) { // on peut voler
                ArrayList<PiecePyramide> accessibles = jCourant.getPiecesJouables();

                if (p.getJoueurCourant() == 0) {
                    jCourant = p.joueur2();
                } else {
                    jCourant = p.joueur1();
                }

                PiecePyramide vol = commentjouer.PieceAVoler(accessibles, p);
                p.IAvol(vol, p.joueurCourant);

                AllStolen.add(0, vol);
                quoifaire.add(0, 1);
            }
            p.changementJoueurCourant();

        }

        p.changementJoueurCourant();

        gagnant = p.getJoueurCourant();

        int numerocoup = 0;
        int numerovol = 0;
        for (Integer temp : quoifaire) {
            if (temp == 0) {
                Coup c = AllPlayed.get(numerocoup);
                p.IAannulCoup(c, p.getJoueurCourant());
                numerocoup++;
                p.changementJoueurCourant();
            } else {
                PiecePyramide token = AllStolen.get(numerovol);
                p.IAannulvol(token, p.getJoueurCourant());
                numerovol++;
            }
        }

        // remet le bon joueur qui doit debuter
        p.joueurCourant = p.joueurDebut;

        return gagnant == numerojoueur;
    }

    public void CreerPyraAlea(Partie p, int numerojoueur) { // Créer une pyramide aleatoire pour le joueur donner en
                                                            // argument
        ArrayList<PiecePyramide> pieces = commentpiocher.CreerPioche(p, numerojoueur);
        for (PiecePyramide piece : pieces) { // a voir si mieux qu'utiliser des iterateurs
            if (numerojoueur == 0) {
                p.joueur1().getCamp().empiler(piece);
                p.joueur1().getPiecesPiochees().remove(piece.getPiece());
            } else {
                p.joueur2().getCamp().empiler(piece);
                p.joueur2().getPiecesPiochees().remove(piece.getPiece());
            }
        }
    }

    public void AnnulCreerPyra(Partie p, int numerojoueur) { // Remet une pioche dans son etat normal celui d'avant test
        Acteur jCourant;
        if (numerojoueur == 0) {
            jCourant = p.joueur1();
        } else {
            jCourant = p.joueur2();
        }

        for (int etage = 5; etage >= 0; etage--) {
            for (int rang = 0; rang < (6 - etage); rang++) {
                Position pos = new Position(etage, rang);
                PiecePyramide pp = jCourant.getCamp().retirer(pos);
                jCourant.addPiecePiochee(pp.getPiece());
            }
        }
    }

    public PyramideJoueur clonepyra(PyramideJoueur pp) {
        PyramideJoueur clone = new PyramideJoueur(6, 6);
        for (int etage = 0; etage < 6; etage++) {
            for (int rang = 0; rang < (6 - etage); rang++) {
                Position pos = new Position(etage, rang);
                clone.empiler(new PiecePyramide(pp.getPiece(pos), pos));
            }
        }
        return clone;
    }
}
