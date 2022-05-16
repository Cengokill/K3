package Modeles;

import java.util.ArrayList;

import Controleur.*;

public class IAActeur extends ActeurClasse implements Acteur {
    IAjeu jeu; // CREER METHODE QUI RENVOIE LA PIECE A VOLER
    IApioche pioche;
    int numerojoueur;

    public IAActeur(String nom, int diff, int numerojoueur) {
        super(nom);
        this.numerojoueur = numerojoueur;
        switch (diff) { //Construit nos IAs suivant la diffciulté choisis
            case 0:
                pioche = new IApiocheAlea();
                jeu = new IAjeuAlea();
                break;
            case 1:
                pioche = ;
                jeu = ;
                break;
            case 2:
                pioche = new IApiocheExpert();
                jeu = new IAjeuExpert(10); //definir la pronfondeur de recherche pour les prochains coups
                break;
            default:
                break;
        }
    }

    public ArrayList<PiecePyramide> Phase1(Partie encours) {
        return pioche.CreerPioche(encours, numerojoueur);
    }

    @Override
    public Coup jouer(Partie encours) { // Pas encore de gestion de vol
        return jeu.IACoup(encours, numerojoueur);
    }

    @Override
    public PiecePyramide choixVol(ArrayList<PiecePyramide> arr) {
        return jeu.PieceAVoler(arr);
    }

}