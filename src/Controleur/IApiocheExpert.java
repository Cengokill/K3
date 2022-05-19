package Controleur;

import java.util.*;

import Modeles.*;

public class IApiocheExpert implements IApioche {

    public ArrayList<PiecePyramide> CreerPioche(Partie p, int numerojoueur) {
        ArrayList<Piece> piecesIA = new ArrayList<>();
        if (numerojoueur == 0) {
            for (Piece aplacer : p.joueur1().getPiecesPiochees()) {
                piecesIA.add(aplacer);
            }
            ;
        } else {
            for (Piece aplacer : p.joueur2().getPiecesPiochees()) {
                piecesIA.add(aplacer);
            }
        }
        PyramideMontagne pm = p.getBaseMontagne();
        ArrayList<PiecePyramide> aempiler = new ArrayList<>();

        // creer une liste de position pour savoir quelle position sont deja utilises
        HashSet<Integer> allpos = new HashSet<>();

        // On place nos joker a des endroits deja d�finis
        aempiler.add(new PiecePyramide(new Piece(Couleurs.NATUREL), new Position(1, 0)));
        aempiler.add(new PiecePyramide(new Piece(Couleurs.BLANC), new Position(1, 4)));
        aempiler.add(new PiecePyramide(new Piece(Couleurs.NATUREL), new Position(3, 2)));
        aempiler.add(new PiecePyramide(new Piece(Couleurs.BLANC), new Position(3, 0)));
        allpos.add(PostoIndice(new Position(1, 0)));
        allpos.add(PostoIndice(new Position(1, 4)));
        allpos.add(PostoIndice(new Position(3, 2)));
        allpos.add(PostoIndice(new Position(3, 0)));

        // recuperation de la liste des pieces presentes dans la montagne
        int noir = 0;
        int bleu = 0;
        int vert = 0;
        int rouge = 0;
        int jaune = 0;
        for (int i = 0; i < 9; i++) {
            Couleurs coul = pm.getPiece(new Position(0, i)).getColor();
            switch (coul) {
                case NOIR:
                    noir++;
                    break;
                case VERT:
                    vert++;
                    break;
                case ROUGE:
                    rouge++;
                    break;
                case JAUNE:
                    jaune++;
                    break;
                case BLEU:
                    bleu++;
                    break;
                default:
                    System.out.println("You never should've come here");
                    break;
            }
        }

        Iterator<Piece> it = piecesIA.iterator();
        Random r = new Random();
        // System.out.println("On parcours les pieces a placer");
        while (it.hasNext()) { // on itere sur les pieces a poser
            Piece piece = it.next();
            if (piece.getColor() == Couleurs.NATUREL || piece.getColor() == Couleurs.BLANC) {
                it.remove();
            } else {
                double proba = r.nextDouble();
                double chance = 0;
                Couleurs coul = piece.getColor();
                switch (coul) {
                    case NOIR:
                        chance = noir / 9;
                        break;
                    case VERT:
                        chance = vert / 9;
                        break;
                    case ROUGE:
                        chance = rouge / 9;
                        break;
                    case JAUNE:
                        chance = jaune / 9;
                        break;
                    case BLEU:
                        chance = bleu / 9;
                        break;
                    default:
                        System.out.println("Stop right there criminal scum ! You violated the law");
                        break;
                }
                if (proba < chance) { // on met en haut
                    boolean estajouter = false;
                    for (int etage = 5; etage >= 0; etage--) {
                        for (int rang = 0; rang < (6 - etage); rang++) {
                            Position pos = new Position(etage, rang);
                            if (!allpos.contains(PostoIndice(pos)) && !estajouter) {
                                aempiler.add(new PiecePyramide(piece, pos));
                                allpos.add(PostoIndice(pos));
                                estajouter = true;
                            }
                        }
                    }
                } else { // on met en bas
                    boolean estajouter = false;
                    for (int etage = 0; etage < 6; etage++) {
                        for (int rang = 0; rang < (6 - etage); rang++) {
                            Position pos = new Position(etage, rang);
                            if (!allpos.contains(PostoIndice(pos)) && !estajouter) {
                                aempiler.add(new PiecePyramide(piece, pos));
                                allpos.add(PostoIndice(pos));
                                estajouter = true;
                            }
                        }
                    }
                }
                ////////////////////////////////
                // if (cpres.contains(piece.getColor()) || nbpib >= 5) { // si la piece est dans
                //////////////////////////////// la montagne on la place
                // // aleatoirement sinon on la place en bas
                // boolean estajouter = false;
                // for (int etage = 5; etage >= 0; etage--) {
                // for (int rang = 0; rang < (6 - etage); rang++) {
                // Position pos = new Position(etage, rang);
                // if (!allpos.contains(PostoIndice(pos)) && !estajouter) {
                // if (etage == 0) {
                // nbpib++;
                // }
                // aempiler.add(new PiecePyramide(piece, pos));
                // allpos.add(PostoIndice(pos));
                // estajouter = true;
                // }
                // }
                // }
                // } else { // pas dans la montagne
                // // revoir
                // Position pos = new Position(0, nbpib);
                // if (allpos.contains(PostoIndice(pos))) {
                // // System.out.println("cette position possede deja une piece");
                // }
                // aempiler.add(new PiecePyramide(piece, pos));
                // allpos.add(PostoIndice(pos));
                // nbpib++;
                // }
                // it.remove();
            }

        }

        ArrayList<PiecePyramide> trier = new ArrayList<>();
        ArrayList<Position> toutespos = new ArrayList<>();
        for (int etage = 0; etage < 6; etage++) {
            for (int rang = 0; rang < (6 - etage); rang++) {
                toutespos.add(new Position(etage, rang));
            }
        }
        for (Position pos : toutespos) {
            for (PiecePyramide pp2 : aempiler) {
                if (pp2.getPos().etage == pos.etage && pp2.getPos().rang == pos.rang) {
                    trier.add(pp2);
                }
            }
        }

        return trier;
    }

    public int PostoIndice(Position pos) {
        if (pos.etage == 0) {
            return pos.rang;
        }
        if (pos.etage == 1) {
            return 6 + pos.rang;
        }
        if (pos.etage == 2) {
            return 11 + pos.rang;
        }
        if (pos.etage == 3) {
            return 15 + pos.rang;
        }
        if (pos.etage == 4) {
            return 18 + pos.rang;
        }
        if (pos.etage == 5) {
            return 21;
        }
        return 0;
    }

}