package Modeles;

import java.util.ArrayList;

import Controleur.*;

public class IAActeur extends Acteur {
    IAjeu jeu;
    IApioche pioche;
    int numerojoueur;

    public IAActeur(String nom, int diff, int numerojoueur) {
        super(nom);
        super.diff = diff;
        this.numerojoueur = numerojoueur;
        this.valideCamp = true;
        this.validerCoup = true;
        this.validerVol = true;
        switch (diff) { // Construit nos IAs suivant la diffciulté choisis
            case 0:
                pioche = new piocheMC(diff);
                jeu = new IAjeuExpert(1);
                break;
            case 1:
                pioche = new piocheMC(diff);
                jeu = new IAjeuExpert(3);
                break;
            case 2:
                pioche = new piocheMC(diff);
                jeu = new IAjeuExpert(5); // definir la pronfondeur de recherche pour les prochains coups
                break;
            default:
                break;
        }
    }

    public int tempsReflexion() {
        int r = Aleatoire.genInt(100, 400);
        return r;
    }

    public ArrayList<PiecePyramide> phase1(Partie encours) {
        return pioche.CreerPioche(encours, numerojoueur);
    }

    @Override
    public Coup jouer(ArrayList<Coup> arr, Partie encours) {
        return jeu.IACoup(encours, numerojoueur);
    }

    @Override
    public PiecePyramide choixVol(ArrayList<PiecePyramide> arr, Partie p) {
        return jeu.PieceAVoler(arr, p);
    }

}