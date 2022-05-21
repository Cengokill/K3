package Controleur;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import Modeles.Acteur;
import Modeles.Partie;
import Modeles.Piece;
import Modeles.PiecePyramide;
import Modeles.Position;
import Modeles.Pyramide;
import Modeles.PyramideJoueur;

public class piocheMC implements IApioche{
    IApioche commentpiocher = new IApiocheAlea();
    IAjeu commentjouer = new IAjeuAlea();

    @Override
    public ArrayList<PiecePyramide> CreerPioche(Partie p, int numerojoueur) {
        double td = (double) System.currentTimeMillis();
        int numeroadv;
        if(numerojoueur==0){
            numeroadv = 1;
        }else{
            numeroadv=0;
        }
        //Liste de <pyra,nbvictoire>
        Hashtable<Pyramide,Integer> tauxdevictoire = new Hashtable<>();
        //Pour i allant de 0 a 100 
        for (int i =0; i<100;i++){
            //Creer une picohe alea pour notre joueur
            CreerPyraAlea(p, numerojoueur);
            int nbvictoire = 0;
            //Pour j allant de 0 a 100
            for (int j=0;j<100;j++){
                //Creer une pioche alea pour l'adversaire
            CreerPyraAlea(p, numeroadv);
                //Simule la partie
                // Si gagnant 
                    //nbvictoire ++
                if (simulePartie(p, numerojoueur)){
                    nbvictoire++;
                }
                //Remet la pioche du joueur dans son etat de base
                AnnulCreerPyra(p, numeroadv);
            }
            //enregistre <pyra,nbvictoire>
            if(numerojoueur == 0){
                tauxdevictoire.put(p.joueur1().getCamp(), nbvictoire);
            }else{
                tauxdevictoire.put(p.joueur2().getCamp(), nbvictoire);
            }
            //remet la pioche de notre joueur dans son etat de base
            AnnulCreerPyra(p, numerojoueur);
        }
        //Parcours la liste et renvoie la pyra qui a le plus grand nombre de victoire

        Pyramide pj  = null;
        int victoiremax = 0;
        Enumeration<Pyramide> enume = tauxdevictoire.keys();
        while (enume.hasMoreElements()){
            Pyramide pt = enume.nextElement();
            if (victoiremax<tauxdevictoire.get(pt)){
                pj = pt;
            }
        }

        ArrayList<PiecePyramide> aempiler = new ArrayList<>();
        //mettre les pieces de la pyramide dans la liste aempiler dans le bon ordre
        for (int etage=0;etage<6;etage++){
            for (int rang = 0 ; rang <(6-etage);rang++){
                Position pos = new Position(etage, rang);
                aempiler.add(new PiecePyramide(pj.getPiece(pos), pos));
            }
        }

        double tf = (double) System.currentTimeMillis();
        System.out.println((tf - td) / 1000 + " s de création de la pioche");
        return aempiler;
    }

    public boolean simulePartie(Partie p, int numerojoueur){ //simule la partie et renvoie vrai si le joueur en argument gagne
        //Penser a annuler les coups a la fin de la partie pour recuperer la partie dans le meme etat que dans celle avant de la simuler aleatoirement
        int gagnant = 2;


        return gagnant == numerojoueur;
    }

    public void CreerPyraAlea(Partie p, int numerojoueur){ //Créer une piramyde aleatoire pour le joueur donner en argument
        ArrayList<PiecePyramide> pieces = commentpiocher.CreerPioche(p, numerojoueur);

        for (PiecePyramide piece : pieces) { // a voir si mieux qu'utiliser des iterateurs
            if (numerojoueur== 0){
                p.joueur1().getCamp().empiler(piece);
                p.joueur1().getPiecesPiochees().remove(piece.getPiece());
            } else {
                p.joueur2().getCamp().empiler(piece);
                p.joueur2().getPiecesPiochees().remove(piece.getPiece());
            }
        }
    }

    public void AnnulCreerPyra(Partie p, int numerojoueur){ //Remet une pioche dans son etat normal celui d'avant test
        Acteur jCourant;
        if (numerojoueur == 0){
            jCourant = p.joueur1();
        } else {
            jCourant = p.joueur2();
        }

        for (int etage = 5; etage >=0 ; etage--){
            for (int rang = 0; rang <(6-etage);rang++){
                Position pos = new Position(etage, rang);
                PiecePyramide pp = jCourant.getCamp().retirer(pos);
                jCourant.addPiecePiochee(pp.getPiece());
            }
        }
    }

}