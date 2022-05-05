import java.util.LinkedList;

import Controleur.*;
import Modeles.*;

public class Main {
	public static void main(String args[]) {
		Jeu j=new Jeu();
		LinkedList<PiecePyramide> coups=j.getBaseMontagne().piecesPosables();
		j.afficherCoups(coups);
		PyramideJoueur camp1=j.joueur1().getCamp();
		Position pos1=new Position(0,5);
		Piece p1=camp1.getPiece(pos1);
		camp1.retirer(new PiecePyramide(p1,pos1));
		camp1.afficher();
		
		pos1=new Position(0,4);
		p1=camp1.getPiece(pos1);
		camp1.retirer(new PiecePyramide(p1,pos1));
		camp1.afficher();
	}
}
